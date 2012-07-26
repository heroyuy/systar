package com.gudigital.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class BinarySerializer {
	private static int BUFFER_SIZE = 1024;

	public static GUObject binary2object(byte[] bs) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bs));
		return readGUObject(dis);
	}

	public static byte[] object2binary(IGUObject object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
		DataOutputStream dos = new DataOutputStream(baos);
		writeIGUObject(dos, object);
		return baos.toByteArray();
	}

	private static GUDataWrapper readGUDataWrapper(DataInputStream dis) throws IOException {
		GUDataWrapper dataWrapper = null;
		// (1) 类型
		byte typeId = dis.readByte();
		GUDataType dataType = GUDataType.fromTypeId(typeId);
		// (2) 值
		switch (dataType) {
		case BOOL:
			boolean v1 = dis.readBoolean();
			dataWrapper = new GUDataWrapper(dataType, v1);
			break;
		case BYTE:
			byte v2 = dis.readByte();
			dataWrapper = new GUDataWrapper(dataType, v2);
			break;
		case SHORT:
			short v3 = dis.readShort();
			dataWrapper = new GUDataWrapper(dataType, v3);
			break;
		case INT:
			int v4 = dis.readInt();
			dataWrapper = new GUDataWrapper(dataType, v4);
			break;
		case LONG:
			long v5 = dis.readLong();
			dataWrapper = new GUDataWrapper(dataType, v5);
			break;
		case FLOAT:
			float v6 = dis.readFloat();
			dataWrapper = new GUDataWrapper(dataType, v6);
			break;
		case DOUBLE:
			double v7 = dis.readDouble();
			dataWrapper = new GUDataWrapper(dataType, v7);
			break;
		case STRING:
			String v8 = dis.readUTF();
			dataWrapper = new GUDataWrapper(dataType, v8);
			break;
		case BOOL_ARRAY:
			int s1 = dis.readShort();
			Collection v9 = new ArrayList();
			for (int i1 = 0; i1 < s1; i1++) {
				v9.add(dis.readBoolean());
			}
			dataWrapper = new GUDataWrapper(dataType, v9);
			break;
		case BYTE_ARRAY:
			int s2 = dis.readShort();
			Collection v10 = new ArrayList();
			for (int i1 = 0; i1 < s2; i1++) {
				v10.add(dis.readByte());
			}
			dataWrapper = new GUDataWrapper(dataType, v10);
			break;
		case SHORT_ARRAY:
			int s3 = dis.readShort();
			Collection v11 = new ArrayList();
			for (int i1 = 0; i1 < s3; i1++) {
				v11.add(dis.readShort());
			}
			dataWrapper = new GUDataWrapper(dataType, v11);
			break;
		case INT_ARRAY:
			int s4 = dis.readShort();
			Collection v12 = new ArrayList();
			for (int i1 = 0; i1 < s4; i1++) {
				v12.add(dis.readInt());
			}
			dataWrapper = new GUDataWrapper(dataType, v12);
			break;
		case LONG_ARRAY:
			int s5 = dis.readShort();
			Collection v13 = new ArrayList();
			for (int i1 = 0; i1 < s5; i1++) {
				v13.add(dis.readShort());
			}
			dataWrapper = new GUDataWrapper(dataType, v13);
			break;
		case FLOAT_ARRAY:
			int s6 = dis.readShort();
			Collection v15 = new ArrayList();
			for (int i1 = 0; i1 < s6; i1++) {
				v15.add(dis.readFloat());
			}
			dataWrapper = new GUDataWrapper(dataType, v15);
			break;
		case DOUBLE_ARRAY:
			int s7 = dis.readShort();
			Collection v16 = new ArrayList();
			for (int i1 = 0; i1 < s7; i1++) {
				v16.add(dis.readDouble());
			}
			dataWrapper = new GUDataWrapper(dataType, v16);
			break;
		case STRING_ARRAY:
			int s8 = dis.readShort();
			Collection v14 = new ArrayList();
			for (int i1 = 0; i1 < s8; i1++) {
				v14.add(dis.readUTF());
			}
			dataWrapper = new GUDataWrapper(dataType, v14);
			break;
		case OBJECT:
			GUObject v17 = readGUObject(dis);
			dataWrapper = new GUDataWrapper(dataType, v17);
			break;
		case OBJECT_ARRAY:
			int s10 = dis.readShort();
			Collection v18 = new ArrayList();
			for (int i2 = 0; i2 < s10; i2++) {
				GUObject v19 = readGUObject(dis);
				v18.add(v19);
			}
			dataWrapper = new GUDataWrapper(dataType, v18);
			break;
		}
		return dataWrapper;
	}

	private static GUObject readGUObject(DataInputStream dis) throws IOException {
		GUObject object = new GUObject();
		// (1) 类型
		String type = dis.readUTF();
		object.setType(type);
		// (2) 数量
		int size = dis.readShort();
		// (3)内容
		for (int i = 0; i < size; i++) {
			// (a) key
			String key = dis.readUTF();
			// (b) data
			object.put(key, readGUDataWrapper(dis));
		}
		return object;
	}

	private static void writeGUDataWrapper(GUDataWrapper dataWrapper, DataOutputStream dos) throws IOException {

		// (1) 类型
		dos.writeByte(dataWrapper.getTypeId().getTypeID());
		// (2) 值
		Object v = dataWrapper.getObject();
		switch (dataWrapper.getTypeId()) {
		case BOOL:
			dos.writeBoolean((Boolean) v);
			break;
		case BYTE:
			dos.writeByte((Byte) v);
			break;
		case SHORT:
			dos.writeShort((Short) v);
			break;
		case INT:
			dos.writeInt((Integer) v);
			break;
		case LONG:
			dos.writeLong((Long) v);
			break;
		case FLOAT:
			dos.writeFloat((Float) v);
			break;
		case DOUBLE:
			dos.writeDouble((Double) v);
			break;
		case STRING:
			dos.writeUTF((String) v);
			break;
		case BOOL_ARRAY:
			Collection<Boolean> v1 = (Collection<Boolean>) v;
			dos.writeShort(v1.size());
			for (Boolean b : v1) {
				dos.writeBoolean(b);
			}
			break;
		case BYTE_ARRAY:
			Collection<Byte> v2 = (Collection<Byte>) v;
			dos.writeShort(v2.size());
			for (Byte b : v2) {
				dos.writeByte(b);
			}
			break;
		case SHORT_ARRAY:
			Collection<Short> v3 = (Collection<Short>) v;
			dos.writeShort(v3.size());
			for (Short s : v3) {
				dos.writeShort(s);
			}
			break;
		case INT_ARRAY:
			Collection<Integer> v4 = (Collection<Integer>) v;
			dos.writeShort(v4.size());
			for (Integer s : v4) {
				dos.writeInt(s);
			}
			break;
		case LONG_ARRAY:
			Collection<Long> v5 = (Collection<Long>) v;
			dos.writeShort(v5.size());
			for (Long s : v5) {
				dos.writeLong(s);
			}
			break;
		case FLOAT_ARRAY:
			Collection<Float> v6 = (Collection<Float>) v;
			dos.writeShort(v6.size());
			for (Float s : v6) {
				dos.writeFloat(s);
			}
			break;
		case DOUBLE_ARRAY:
			Collection<Double> v7 = (Collection<Double>) v;
			dos.writeShort(v7.size());
			for (Double s : v7) {
				dos.writeDouble(s);
			}
			break;
		case STRING_ARRAY:
			Collection<String> v8 = (Collection<String>) v;
			dos.writeShort(v8.size());
			for (String s : v8) {
				dos.writeUTF(s);
			}
			break;
		case OBJECT:
			IGUObject obj = (IGUObject) v;
			writeIGUObject(dos, obj);
			break;
		case OBJECT_ARRAY:
			Collection<IGUObject> v9 = (Collection<IGUObject>) v;
			dos.writeShort(v9.size());
			for (IGUObject obj2 : v9) {
				writeIGUObject(dos, obj2);
			}
			break;
		}
	}

	private static void writeIGUObject(DataOutputStream dos, IGUObject object) throws IOException {
		// (1)类型
		dos.writeUTF(object.getType());
		// (2)数量
		dos.writeShort(object.size());
		// (3)内容
		for (String key : object.getKeys()) {
			// (a) key
			dos.writeUTF(key);
			// (b) data
			writeGUDataWrapper(object.get(key), dos);
		}
	}

}
