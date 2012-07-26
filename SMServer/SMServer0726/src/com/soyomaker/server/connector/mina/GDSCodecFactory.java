package com.soyomaker.server.connector.mina;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.soyomaker.data.GObject;

public class GDSCodecFactory extends DemuxingProtocolCodecFactory {
	public GDSCodecFactory() {
		super.addMessageDecoder(GDSDecoder.class);
		super.addMessageEncoder(GObject.class, GDObjectEncoder.class);
	}
}
