package com.soyomaker.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.core.GUObject;
import com.soyomaker.core.IGUObject;
import com.soyomaker.server.connector.mina.CodecConst;

/**
 * SocketTestClient
 * 
 * @author wp_g4
 * 
 */
public class SocketTest {

	public static void main(String[] args) {
		GUObject msg = new GUObject();
		msg.setType("test");
		msg.putString("hello", "server");
		Collection<IGUObject> c = new ArrayList<IGUObject>();
		c.add(msg);
		GUObject packSend = new GUObject();
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
			int len = dis.readInt();
			byte[] bytes = new byte[len];
			dis.read(bytes);
			IGUObject resMsg = GUObject.createFromBytes(bytes);
			System.out.println(resMsg);
			dis.close();
			is.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
