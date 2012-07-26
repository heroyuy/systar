package com.soyomaker.server.connector.jetty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.HashSessionIdManager;
import org.mortbay.jetty.servlet.ServletHolder;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.application.IService;
import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;
import com.soyomaker.server.connector.mina.CodecConst;

/**
 * JettyServer
 * 
 * @author wp_g4
 * 
 */
public class JettyServer extends AbstractBean implements IService {

	/**
	 * 处理所有http请求的servlet
	 * 
	 * @author wp_g4
	 * 
	 */
	class MessageServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			processRequest(request, response);
		}

		@Override
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			processRequest(request, response);
		}
	}

	/** jetty服务 */
	private Server server = null;
	/** 监听端口 */
	private int port = 9091;
	/** 运行标识 */
	private boolean isRunning = false;

	/** 消息处理器 */
	private JettyHandler jettyHandler = null;

	private Logger log = Logger.getLogger(JettyServer.class);

	@Override
	public void doCommand(IGObject command) {
	}

	@Override
	public IGObject getStatus() {
		return null;
	}

	@Override
	/**
	 * 初始化
	 */
	public void initialize() {
		port = getIntParam("port", 9091);
		jettyHandler = (JettyHandler) this.getBeanFactory().getBean(this.getParam("handler"));
	}

	/**
	 * 启动服务
	 */
	public void start() {
		// 实例化Jetty服务
		server = new Server(this.port);
		// 不使用SecureRandom，因为在linux上可能会遇到问题
		server.setSessionIdManager(new HashSessionIdManager(new Random()));
		// 配置Servlet
		Context root = new Context(server, "/", Context.SESSIONS);
		root.addServlet(new ServletHolder(new MessageServlet()), "/");
		// 启动服务
		if (!isRunning) {
			try {
				isRunning = true;
				server.setStopAtShutdown(true);
				server.start();
			} catch (Exception e) {
				isRunning = false;
				e.printStackTrace();
			}
		}
	}

	/**
	 * 停止服务
	 */
	public void stop() {
		if (isRunning) {
			try {
				isRunning = false;
				if (server != null) {
					server.stop();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理所有http请求
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// (1) 检查list是否存在
		@SuppressWarnings("unchecked")
		List<IGObject> list = (List<IGObject>) request.getSession().getAttribute("messages");
		if (list == null) {
			list = new ArrayList<IGObject>();
			request.getSession().setAttribute("messages", list);
		}
		// (2) 取收到的消息
		InputStream in = request.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		int len = dis.readInt();
		byte data[] = new byte[len];
		dis.read(data);
		dis.close();
		in.close();
		// (3) 转换为GUObject并处理
		if (data.length > 2) {
			jettyHandler.messageReceived(request.getSession(), GObject.createFromBytes(data));
		}
		// (4) 反馈消息给客户端
		GObject packSend = new GObject();
		packSend.setType(CodecConst.PACKAGE_TYPE_NAME);
		packSend.putObjectArray(CodecConst.PACKAGE_ARRAY_KEY, list);
		OutputStream os = response.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		data = packSend.toBinary();
		dos.writeInt(data.length);
		dos.write(data);
		log.debug("Jetty发出包:" + packSend);
		dos.flush();
		dos.close();
		os.close();
		list.clear();
	}

}
