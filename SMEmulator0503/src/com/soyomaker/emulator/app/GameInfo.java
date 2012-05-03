package com.soyomaker.emulator.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class GameInfo {

	private static String CONFIG_PATH = "plugin/emulator/config/emulator.xml";

	private static GameInfo instance = new GameInfo();

	public static GameInfo getInstance() {
		return instance;
	}

	private int width = 960;

	private int height = 640;

	private int ratedFPS = 20;

	private int actualFPS = 0;

	private boolean showFPS = true;

	private String gamePath = null;

	private GameInfo() {
		try {
			XMLObject emulatorXMLObject = XMLParser
					.parse(new File(CONFIG_PATH));
			this.width = Integer.parseInt(emulatorXMLObject.getChild(0)
					.getValue());
			this.height = Integer.parseInt(emulatorXMLObject.getChild(1)
					.getValue());
			this.ratedFPS = Integer.parseInt(emulatorXMLObject.getChild(2)
					.getValue());
			this.showFPS=Boolean.parseBoolean(emulatorXMLObject.getChild(3)
					.getValue());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getActualFps() {
		return actualFPS;
	}

	public String getGamePath() {
		return gamePath;
	}

	public int getHeight() {
		return height;
	}

	public int getRatedFPS() {
		return ratedFPS;
	}

	public int getWidth() {
		return width;
	}

	public boolean isShowFPS() {
		return showFPS;
	}

	public void setActualFPS(int actualFps) {
		this.actualFPS = actualFps;
	}

	public void setGamePath(String gamePath) {
		this.gamePath = gamePath;
	}

	public void setRatedFPS(int fps) {
		this.ratedFPS = fps;
	}

	public void setShowFPS(boolean showFPS) {
		this.showFPS = showFPS;
	}

}
