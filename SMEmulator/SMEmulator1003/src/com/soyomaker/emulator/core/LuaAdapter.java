package com.soyomaker.emulator.core;

import java.util.Random;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import com.soyomaker.emulator.utils.ColorFactory;
import com.soyomaker.emulator.utils.ImageFactory;
import com.soyomaker.emulator.utils.SMAudioPlayer;

/**
 * Lua适配器，功能：<br>
 * 1、向lua环境注册java 接口 <br>
 * 2、提供转换lua实现的接口供java调用
 * 
 * @author wp_g4
 * 
 */
public class LuaAdapter {

	public static LuaAdapter newInstance(String luaFilePath, String globalGame) {
		return new LuaAdapter(luaFilePath, globalGame);
	}

	private LuaState luaState = null;

	private LuaObject luaGlobalGame = null;

	private LuaObject luaFunctionOnStart = null;

	private LuaObject luaFunctionOnTouch = null;

	private LuaObject luaFunctionUpdate = null;

	private LuaObject luaFunctionPaint = null;

	private LuaObject luaFunctionOnStop = null;

	private LuaAdapter(String luaFilePath, String globalGame) {
		try {
			// 设置LuaState
			luaState = LuaStateFactory.newLuaState();
			luaState.openLibs();
			luaState.LdoFile(luaFilePath);
			// 1、注册JAVA提供给lua的API
			// 注册SMFunction
			new SMFunction(luaState).register("smFunction");
			// --注册GameEngine
			luaState.pushObjectValue(GameEngine.getInstance());
			luaState.setGlobal("smGameEngine");
			// --注册Random
			luaState.pushObjectValue(new Random());
			luaState.setGlobal("smRandom");
			// --注册ImageFactory
			luaState.pushObjectValue(ImageFactory.getInstance());
			luaState.setGlobal("smImageFactory");
			// --注册SMAudioPlayer
			luaState.pushObjectValue(SMAudioPlayer.getInstance());
			luaState.setGlobal("smAudioPlayer");
			// --注册ColorFactory
			luaState.pushObjectValue(ColorFactory.getInstance());
			luaState.setGlobal("smColorFactory");
			// 2、转换lua实现的接口
			luaGlobalGame = luaState.getLuaObject(globalGame);
			luaFunctionOnStart = luaGlobalGame.getField("onStart");
			luaFunctionOnTouch = luaGlobalGame.getField("onTouch");
			luaFunctionUpdate = luaGlobalGame.getField("update");
			luaFunctionPaint = luaGlobalGame.getField("paint");
			luaFunctionOnStop = luaGlobalGame.getField("onStop");
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onStart方法的转换
	 */
	public void onStart() {
		try {
			luaFunctionOnStart.call(new Object[] { luaGlobalGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onTouch方法的转换
	 */
	public void onTouch(int keyX, int keyY, int type) {
		try {
			luaFunctionOnTouch.call(new Object[] { luaGlobalGame, keyX, keyY,
					type });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update方法的转换
	 */
	public void update() {
		try {
			luaFunctionUpdate.call(new Object[] { luaGlobalGame });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * paint方法的转换
	 */
	public void paint(Painter painter) {
		try {
			luaFunctionPaint.call(new Object[] { luaGlobalGame, painter });
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onStop方法的转换
	 */
	public void onStop() {
		try {
			luaFunctionOnStop.call(new Object[] { luaGlobalGame });
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

	public void callLuaGC() {
		luaState.getGlobal("collectgarbage");
		try {
			luaState.pushObjectValue("collect");
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(1, 0);
	}

	public void runScrpit(final int scriptId) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				luaState.LdoFile(GameEngine.getInstance().getGamePath()
						+ "/data/script/script" + scriptId + ".gat");
				System.out.println("script thread end");
			}
		}).start();
	}

	public void setGamePath(String gamePath) {
		try {
			luaState.pushObjectValue(gamePath);
			luaState.setGlobal("smGamePath");
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

}
