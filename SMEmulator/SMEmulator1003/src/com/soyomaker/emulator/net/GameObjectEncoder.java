package com.soyomaker.emulator.net;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.soyomaker.lang.GameObject;

/**
 * 编码器。包结构：包长(int) 包体(byte[])
 * 
 * @author wp_g4
 * 
 */
public class GameObjectEncoder implements MessageEncoder<GameObject> {
	@Override
	public void encode(IoSession session, GameObject obj, ProtocolEncoderOutput out) throws Exception {
		byte[] bs = obj.toBinary();
		IoBuffer buf = IoBuffer.allocate(bs.length + 12);
		buf.setAutoExpand(true);
		buf.putInt(bs.length);
		buf.put(bs);
		buf.flip();
		out.write(buf);
		out.flush();
	}

}
