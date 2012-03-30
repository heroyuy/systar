package com.soyomaker.emulator.core;

import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

import com.soyomaker.emulator.utils.ImageFactory;
import com.soyomaker.emulator.utils.SMLog;

public class SMFunction extends JavaFunction {

	public static final String SMLOG_SETDEBUG = "smLog:setDebug";
	public static final String SMLOG_INFO = "smLog:info";
	public static final String SMLOG_ERROR = "smLog:error";

	public static final String SMGAMEENGINE_GETACTUALFPS = "smGameEngine:getActualFps";
	public static final String SMGAMEENGINE_GETHEIGHT = "smGameEngine:getHeight";
	public static final String SMGAMEENGINE_GETRATEDFPS = "smGameEngine:getRatedFps";
	public static final String SMGAMEENGINE_GETSYSTEMMILLITIME = "smGameEngine:getSystemMilliTime";
	public static final String SMGAMEENGINE_GETWIDTH = "smGameEngine:getWidth";
	public static final String SMGAMEENGINE_ISSHOWFPS = "smGameEngine:isShowFps";
	public static final String SMGAMEENGINE_SETRATEDFPS = "smGameEngine:setRatedFps";
	public static final String SMGAMEENGINE_SETSHOWFPS = "smGameEngine:setShowFps";
	public static final String SMGAMEENGINE_STOP = "smGameEngine:stop";

	public static final String SMIMAGEFACTORY_CREATEIMAGE = "smImageFactory:createImage";

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
		// ===========smGameEngine===========
		else if (name.equals(SMGAMEENGINE_GETACTUALFPS)) {
			int fps = GameEngine.getInstance().getActualFps();
			this.setResult(fps);
		} else if (name.equals(SMGAMEENGINE_GETHEIGHT)) {
			int height = GameEngine.getInstance().getHeight();
			this.setResult(height);
		} else if (name.equals(SMGAMEENGINE_GETRATEDFPS)) {
			int fps = GameEngine.getInstance().getRatedFps();
			this.setResult(fps);
		} else if (name.equals(SMGAMEENGINE_GETSYSTEMMILLITIME)) {
			int milliTime = GameEngine.getInstance().getSystemMilliTime();
			this.setResult(milliTime);
		} else if (name.equals(SMGAMEENGINE_GETWIDTH)) {
			int width = GameEngine.getInstance().getWidth();
			this.setResult(width);
		} else if (name.equals(SMGAMEENGINE_ISSHOWFPS)) {
			boolean showFps = GameEngine.getInstance().isShowFps();
			this.setResult(showFps);
		} else if (name.equals(SMGAMEENGINE_SETRATEDFPS)) {
			LuaObject lo = this.getArgument();
			GameEngine.getInstance().setRatedFps((int) lo.getNumber());
		} else if (name.equals(SMGAMEENGINE_SETSHOWFPS)) {
			LuaObject lo = this.getArgument();
			GameEngine.getInstance().setShowFps(lo.getBoolean());
		} else if (name.equals(SMGAMEENGINE_STOP)) {
			GameEngine.getInstance().stop();
		}
		// ===========smImageFactory===========
		else if (name.equals(SMIMAGEFACTORY_CREATEIMAGE)) {
			LuaObject lo = this.getArgument();
			Image image = null;
			if (lo.isTable()) {
				int width = (int) lo.getField("width").getNumber();
				int height = (int) lo.getField("height").getNumber();
				System.out.println("image->w:" + width + " h:" + height);
				image = ImageFactory.getInstance().createImage(width, height);
			} else {
				image = ImageFactory.getInstance().createImage(lo.getString());
			}
			this.setResult(image);
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
		try {
			L.pushObjectValue(o);
			L.setGlobal("smFunctionResult");
		} catch (LuaException e) {
			e.printStackTrace();
		}
	}
}
