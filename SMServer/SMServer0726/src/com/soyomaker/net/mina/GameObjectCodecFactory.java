package com.soyomaker.net.mina;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;

@Component
public class GameObjectCodecFactory extends DemuxingProtocolCodecFactory {
	public GameObjectCodecFactory() {
		super.addMessageDecoder(GameObjectDecoder.class);
		super.addMessageEncoder(GameObject.class, GameObjectEncoder.class);
	}
}
