package com.gudigital.server.connector.mina;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.gudigital.core.GUObject;

public class GDSCodecFactory extends DemuxingProtocolCodecFactory {
	public GDSCodecFactory() {
		super.addMessageDecoder(GDSDecoder.class);
		super.addMessageEncoder(GUObject.class, GDObjectEncoder.class);
	}
}
