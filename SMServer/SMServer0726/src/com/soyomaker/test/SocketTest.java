package com.soyomaker.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.data.SMObject;
import com.soyomaker.data.ISMObject;
import com.soyomaker.net.mina.PackageConst;

/**
 * SocketTestClient
 * 
 * @author wp_g4
 * 
 */
public class SocketTest {

	public static void main(String[] args) {
		SMObject msg = new SMObject();
		msg.setType("101001");
		msg.putString("username", "wp_g4");
		msg.putString("password", "2724504");
		Collection<ISMObject> c = new ArrayList<ISMObject>();
		c.add(msg);
		SMObject packSend = new SMObject();
		packSend.setType(PackageConst.PACKAGE_TYPE_NAME);
		packSend.putObjectArray(PackageConst.PACKAGE_ARRAY_KEY, c);
		try {
			Socket socket = new Socket("127.0.0.1", 8081);
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			byte[] dataSent = packSend.toBinary();
			dos.writeInt(dataSent.length);
			dos.write(dataSent);
			dos.flush();
			System.out.println("socket发出:" + packSend);
			int len = dis.readInt();
			byte[] bytes = new byte[len];
			dis.read(bytes);
			ISMObject resMsg = SMObject.createFromBytes(bytes);
			System.out.println("socket收到:" + resMsg);
			dis.close();
			is.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
