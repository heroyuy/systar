package com.gudigital.test;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.gudigital.core.GUObject;
import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.mina.CodecConst;

/**
 * HttpTestClient
 * 
 * @author wp_g4
 * 
 */
public class HttpTest {

	private static String SERVER_IP = "http://127.0.0.1:8081";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GUObject msg = new GUObject();
		msg.setType("test");
		msg.putString("hello", "server");
		Collection<IGUObject> c = new ArrayList<IGUObject>();
		c.add(msg);
		GUObject packSend = new GUObject();
		packSend.setType(CodecConst.PACKAGE_TYPE_NAME);
		packSend.putObjectArray(CodecConst.PACKAGE_ARRAY_KEY, c);
		System.out.println("HttpTest发出:" + packSend);
		try {
			URL url = new URL(SERVER_IP);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			byte[] data = packSend.toBinary();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeInt(data.length);
			out.write(data);
			out.flush();
			InputStream is = connection.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			int len = dis.readInt();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int tag = -1;
			while ((tag = dis.read()) != -1) {
				bos.write(tag);
			}
			data = bos.toByteArray();
			bos.close();
			IGUObject resMsg = GUObject.createFromBytes(data);
			System.out.println("HttpTest收到包:" + len + " ->" + resMsg);
			connection.disconnect();
			dis.close();
			is.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
