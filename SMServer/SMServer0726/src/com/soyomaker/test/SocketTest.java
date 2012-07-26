package com.soyomaker.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;
import com.soyomaker.server.mina.CodecConst;

/**
 * SocketTestClient
 * 
 * @author wp_g4
 * 
 */
public class SocketTest {

	public static void main(String[] args) {
		GObject msg = new GObject();
		msg.setType("test");
		msg.putString("hello", "server");
		Collection<IGObject> c = new ArrayList<IGObject>();
		c.add(msg);
		GObject packSend = new GObject();
		packSend.setType(CodecConst.PACKAGE_TYPE_NAME);
		packSend.putObjectArray(CodecConst.PACKAGE_ARRAY_KEY, c);
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			byte[] dataSent = packSend.toBinary();
			dos.writeInt(dataSent.length);
			dos.write(dataSent);
			dos.flush();
			System.out.println("socket发出:"+dataSent);
			int len = dis.readInt();
			byte[] bytes = new byte[len];
			dis.read(bytes);
			IGObject resMsg = GObject.createFromBytes(bytes);
			System.out.println("socket收到:"+resMsg);
			dis.close();
			is.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
