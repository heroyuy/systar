package com.soyomaker.emulator.api;

import java.util.Random;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class ApiManager {

	private static class Inatance {
		private static ApiManager instance = new ApiManager();
	}

	public static ApiManager getInstance() {
		return Inatance.instance;
	}

	private LuaState luaState = null;

	private ApiManager() {
		luaState = LuaStateFactory.newLuaState();
		luaState.openLibs();
		registerMethods();
	}

	public LuaState getLuaState() {
		return luaState;
	}

	private void registerMethods() {
		try {
			// --注册Random
			luaState.pushObjectValue(new Random());
			luaState.setGlobal("smRandom");

			// --注册SMLog
			luaState.pushObjectValue(new SMLog());
			luaState.setGlobal("smLog");

		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

}
