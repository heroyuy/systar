package com.soyomaker.emulator.app;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.ImageFactory;
import com.soyomaker.emulator.utils.SMAudioPlayer;
import com.soyomaker.emulator.utils.SMLog;
import com.soyomaker.emulator.utils.SMString;

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

	private LuaObject luaFunctionOnInput = null;

	private LuaObject luaFunctionOnUpdate = null;

	private LuaObject luaFunctionOnPaint = null;

	private LuaObject luaFunctionOnStop = null;

	private LuaObject luaFunctionOnLowMemory = null;

	private static String MAIN_FILE = "/smscript/Game.smlua";

	private static String GAME_NAME = "Game";

	private PainterTest pt = new PainterTest();

	public LuaAdapter(Game game) {
		try {
			// 设置LuaState
			luaState = LuaStateFactory.newLuaState();
			luaState.openLibs();
			luaState.LdoFile(GameInfo.getInstance().getGamePath() + MAIN_FILE);
			// 1、注册JAVA提供给lua的API
			// --注册GameEngine
			luaState.pushObjectValue(game);
			luaState.setGlobal("smGame");
			// --注册SMLog
			luaState.pushObjectValue(SMLog.getInstance());
			luaState.setGlobal("smLog");
			// --注册ImageFactory
			luaState.pushObjectValue(ImageFactory.getInstance());
			luaState.setGlobal("smImageFactory");
			// --注册SMAudioPlayer
			luaState.pushObjectValue(SMAudioPlayer.getInstance());
			luaState.setGlobal("smAudioPlayer");
			// --注册SMString
			luaState.pushObjectValue(SMString.getInstance());
			luaState.setGlobal("smString");
			// 2、转换lua实现的接口
			luaGame = luaState.getLuaObject(GAME_NAME);
			luaFunctionOnStart = luaGame.getField("onStart");
			luaFunctionOnTouch = luaGame.getField("onTouch");
			luaFunctionOnInput = luaGame.getField("onInput");
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
	 * onInput方法的转换
	 */
	public void onInput(String value) {
		// try {
		// luaFunctionOnInput.call(new Object[] { luaGame, value });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * onLowMemory方法的转换
	 */
	public void onLowMemory() {
		// try {
		// luaFunctionOnLowMemory.call(new Object[] { luaGame });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * onStart方法的转换
	 */
	public void onStart() {
		// try {
		// luaFunctionOnStart.call(new Object[] { luaGame });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * onStop方法的转换
	 */
	public void onStop() {
		// try {
		// luaFunctionOnStop.call(new Object[] { luaGame });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * onTouch方法的转换
	 */
	public void onTouch(Event event) {
		// try {
		// luaFunctionOnTouch.call(new Object[] { luaGame, event });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * paint方法的转换
	 */
	public void paint(Painter painter) {
		pt.test(painter);
		// try {
		// luaFunctionOnPaint.call(new Object[] { luaGame, painter });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * update方法的转换
	 */
	public void update() {
		// try {
		// luaFunctionOnUpdate.call(new Object[] { luaGame });
		// } catch (LuaException e) {
		// e.printStackTrace();
		// }
	}
}
