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

	private int ratedFps = 20;

	private int actualFps = 0;

	private String gamePath = null;

	private GameInfo() {
		try {
			XMLObject emulatorXMLObject = XMLParser
					.parse(new File(CONFIG_PATH));
			this.width = Integer.parseInt(emulatorXMLObject.getChild(0)
					.getValue());
			this.height = Integer.parseInt(emulatorXMLObject.getChild(1)
					.getValue());
			this.ratedFps = Integer.parseInt(emulatorXMLObject.getChild(2)
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
		return actualFps;
	}

	public String getGamePath() {
		return gamePath;
	}

	public int getHeight() {
		return height;
	}

	public int getRatedFps() {
		return ratedFps;
	}

	public int getWidth() {
		return width;
	}

	public void setActualFps(int actualFps) {
		this.actualFps = actualFps;
	}

	public void setGamePath(String gamePath) {
		this.gamePath = gamePath;
	}

	public void setRatedFps(int fps) {
		this.ratedFps = fps;
	}

}
