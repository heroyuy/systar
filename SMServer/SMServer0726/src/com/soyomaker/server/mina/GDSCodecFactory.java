package com.soyomaker.server.mina;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.soyomaker.data.SMObject;

public class GDSCodecFactory extends DemuxingProtocolCodecFactory {
	public GDSCodecFactory() {
		super.addMessageDecoder(GDSDecoder.class);
		super.addMessageEncoder(SMObject.class, GDObjectEncoder.class);
	}
}
