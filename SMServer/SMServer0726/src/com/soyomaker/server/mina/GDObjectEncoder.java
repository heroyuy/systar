package com.soyomaker.server.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.soyomaker.data.GObject;

/**
 * 编码器。包结构：包长(int) 包体(byte[])
 * 
 * @author zhangjun
 * 
 */
public class GDObjectEncoder implements MessageEncoder<GObject> {
	@Override
	public void encode(IoSession arg0, GObject arg1, ProtocolEncoderOutput arg2) throws Exception {
		byte[] bs = arg1.toBinary();
		IoBuffer buf = IoBuffer.allocate(bs.length + 12);
		buf.setAutoExpand(true);
		buf.putInt(bs.length);
		buf.put(bs);
		buf.flip();
		arg2.write(buf);
		arg2.flush();
	}

}
