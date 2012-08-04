package com.soyomaker.emulator.net;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.soyomaker.lang.GameObject;

public class GameObjectCodecFactory extends DemuxingProtocolCodecFactory {
	public GameObjectCodecFactory() {
		super.addMessageDecoder(GameObjectDecoder.class);
		super.addMessageEncoder(GameObject.class, GameObjectEncoder.class);
	}
}
