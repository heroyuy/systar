package com.soyomaker.net.mina;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.soyomaker.data.GameObject;

public class GDSCodecFactory extends DemuxingProtocolCodecFactory {
	public GDSCodecFactory() {
		super.addMessageDecoder(GDSDecoder.class);
		super.addMessageEncoder(GameObject.class, GDObjectEncoder.class);
	}
}
