package com.soyomaker.emulator.utils;

import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

public class SMFunction extends JavaFunction {

	public static final String SMLOG_SETDEBUG = "smLog:setDebug";
	public static final String SMLOG_INFO = "smLog:info";
	public static final String SMLOG_ERROR = "smLog:error";

	public SMFunction(LuaState arg0) {
		super(arg0);
	}

	@Override
	public int execute() throws LuaException {
		String name = this.getName();
		// ===========smLog===========
		if (name.equals(SMLOG_SETDEBUG)) {
			LuaObject lo = this.getArgument();
			SMLog.getInstance().setDebug(lo.getBoolean());
		} else if (name.equals(SMLOG_INFO)) {
			LuaObject lo = this.getArgument();
			SMLog.getInstance().info(lo);
		} else if (name.equals(SMLOG_ERROR)) {
			LuaObject lo = this.getArgument();
			SMLog.getInstance().error(lo);
		}
		return 0;
	}

	private String getName() {
		LuaObject lo = L.getLuaObject("smFunctionName");
		return lo.getString();
	}

	private LuaObject getArgument() {
		return L.getLuaObject("smFunctionArgument");
	}

	private void setResult(Object o) {

	}
}
