package com.soyomaker.net.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.soyomaker.data.GameObject;
import com.soyomaker.data.GameObject;

/**
 * 解码器。包结构：包长(int) 包体(byte[])
 * 
 * @author wangpeng
 * 
 */
public class GDSDecoder extends MessageDecoderAdapter {

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		int len = in.remaining();
		if (len < 12) {
			return MessageDecoderResult.NEED_DATA;
		}
		int size = in.getInt();
		int remain = in.remaining();
		if (remain < size) {
			return MessageDecoderResult.NEED_DATA;
		} else {
			return MessageDecoderResult.OK;
		}
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer buf, ProtocolDecoderOutput out) throws Exception {
		int size = buf.getInt();
		byte[] bs = new byte[size];
		buf.get(bs);
		GameObject msg = GameObject.createFromBytes(bs);
		out.write(msg);
		return MessageDecoderResult.OK;

	}

}
