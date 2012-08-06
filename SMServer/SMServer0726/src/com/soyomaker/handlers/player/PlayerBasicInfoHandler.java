package com.soyomaker.handlers.player;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Model;
import com.soyomaker.model.db.TableProxy;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;

public class PlayerBasicInfoHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, GameObject msg) {
		TableProxy playerProxy = Model.getInstance().getTableProxy("player");
		GameObject player = playerProxy.selectWherePrimaryKey(playerSession
				.getPlayerId());
		if (player == null) {
			TableProxy playerDictProxy = Model.getInstance().getTableProxy(
					"dict_player");
			GameObject playerDict = playerDictProxy.selectWherePrimaryKey(1);
			playerDict.putInt("id", playerSession.getPlayerId());
			playerProxy.insert(playerDict);
			player = playerDict;
		}
		GameObject msgSent = new GameObject();
		msgSent.setType(msg.getType());
		msgSent.putBool("status", true);
		msgSent.putObject("player", player);
		NetTransceiver.getInstance().sendMessage(playerSession, msgSent);
	}

}
