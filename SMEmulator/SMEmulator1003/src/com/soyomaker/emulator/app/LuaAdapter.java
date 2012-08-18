package com.soyomaker.emulator.app;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.util.CollectionFactory;
import com.soyomaker.emulator.util.ColorFactory;
import com.soyomaker.emulator.util.ImageFactory;
import com.soyomaker.emulator.util.Net;
import com.soyomaker.emulator.util.SMAudioPlayer;
import com.soyomaker.emulator.util.SMLog;
import com.soyomaker.emulator.util.SMString;
import com.soyomaker.lang.GameObject;

/**
 * Lua适配器，功能：<br>
 * 1、向lua环境注册java 接口 <br>
 * 2、提供转换lua实现的接口供java调用
 * 
 * @author wp_g4
 * 
 */
public class LuaAdapter {

	private LuaState luaState = null;

	private LuaObject luaGame = null;

	private LuaObject luaFunctionOnStart = null;

	private LuaObject luaFunctionOnTouch = null;

	private LuaObject luaFunctionOnKey = null;

	private LuaObject luaFunctionOnMessage = null;

	private LuaObject luaFunctionOnUpdate = null;

	private LuaObject luaFunctionOnPaint = null;

	private LuaObject luaFunctionOnStop = null;

	private LuaObject luaFunctionOnLowMemory = null;

	private static String MAIN_FILE = "/smscript/Game.smlua";

	private static String GAME_NAME = "Game";

	public LuaAdapter(IGame game) {
		try {
			// 设置LuaState
			luaState = LuaStateFactory.newLuaState();
			luaState.openLibs();
			luaState.LdoFile(GameConfig.getInstance().getGamePath() + MAIN_FILE);
			// 1、注册JAVA提供给lua的API
			// --注册Game
			luaState.pushObjectValue(game);
			luaState.setGlobal("smGame");
			// --注册Net
			luaState.pushObjectValue(Net.getInstance());
			luaState.setGlobal("smNet");
			// --注册SMLog
			luaState.pushObjectValue(SMLog.getInstance());
			luaState.setGlobal("smLog");
			// --注册CollectionFactory
			luaState.pushObjectValue(CollectionFactory.getInstance());
			luaState.setGlobal("smCollectionFactory");
			// --注册ImageFactory
			luaState.pushObjectValue(ImageFactory.getInstance());
			luaState.setGlobal("smImageFactory");
			// --注册SMAudioPlayer
			luaState.pushObjectValue(SMAudioPlayer.getInstance());
			luaState.setGlobal("smAudioPlayer");
			// --注册ColorFactory
			luaState.pushObjectValue(ColorFactory.getInstance());
			luaState.setGlobal("smColorFactory");
			// --注册SMString
			luaState.pushObjectValue(SMString.getInstance());
			luaState.setGlobal("smString");
			// 2、转换lua实现的接口
			luaGame = luaState.getLuaObject(GAME_NAME);
			luaFunctionOnStart = luaGame.getField("onStart");
			luaFunctionOnTouch = luaGame.getField("onTouch");
			luaFunctionOnKey = luaGame.getField("onKey");
			luaFunctionOnMessage = luaGame.getField("onMessage");
			luaFunctionOnUpdate = luaGame.getField("onUpdate");
			luaFunctionOnPaint = luaGame.getField("onPaint");
			luaFunctionOnStop = luaGame.getField("onStop");
			luaFunctionOnLowMemory = luaGame.getField("onLowMemory");
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	public float getLuaMemory() {
		luaState.getGlobal("collectgarbage");
		try {
			luaState.pushObjectValue("count");
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(1, 1);
		luaState.setField(LuaState.LUA_GLOBALSINDEX, "result");
		LuaObject lobj = luaState.getLuaObject("result");
		return (float) (lobj.getNumber() * 1024);
	}

	/**
	 * onLowMemory方法的转换
	 */
	public void onLowMemory() {
		try {
			luaFunctionOnLowMemory.call(new Object[] { luaGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onStart方法的转换
	 */
	public void onStart() {
		try {
			luaFunctionOnStart.call(new Object[] { luaGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onStop方法的转换
	 */
	public void onStop() {
		try {
			luaFunctionOnStop.call(new Object[] { luaGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onTouch方法的转换
	 */
	public void onTouch(Event event) {
		try {
			luaFunctionOnTouch.call(new Object[] { luaGame, event });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onKey方法的转换
	 */
	public void onKey(String key) {
		try {
			luaFunctionOnKey.call(new Object[] { luaGame, key });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onMessage方法的转换
	 */
	public void onMessage(GameObject msg) {
		try {
			luaFunctionOnMessage.call(new Object[] { luaGame, msgToLua(msg) });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * paint方法的转换
	 */
	public void paint(Painter painter) {
		try {
			luaFunctionOnPaint.call(new Object[] { luaGame, painter });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update方法的转换
	 */
	public void update() {
		try {
			luaFunctionOnUpdate.call(new Object[] { luaGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	private String msgToLua(GameObject msg) {
		return "Msg={id=\""+msg.getType()+"\",content=" + msg.toLua() + "}";
	}
}
