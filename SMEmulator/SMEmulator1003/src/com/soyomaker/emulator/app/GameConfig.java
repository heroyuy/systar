package com.soyomaker.emulator.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class GameConfig {

	private static String CONFIG_PATH = "plugin/emulator/config/game.xml";

	private static GameConfig instance = new GameConfig();

	public static GameConfig getInstance() {
		return instance;
	}

	private int width = 960;

	private int height = 640;

	private int ratedFPS = 20;

	private int actualFPS = 0;

	private boolean showFPS = true;

	private String ip = null;

	private int port = 0;

	private String gamePath = null;

	private GameConfig() {
		try {
			XMLObject configXMLObject = XMLParser.parse(new File(CONFIG_PATH));
			this.width = Integer.parseInt(configXMLObject.getChild(0).getValue());
			this.height = Integer.parseInt(configXMLObject.getChild(1).getValue());
			this.ratedFPS = Integer.parseInt(configXMLObject.getChild(2).getValue());
			this.showFPS = Boolean.parseBoolean(configXMLObject.getChild(3).getValue());
			this.ip = configXMLObject.getChild(4).getValue();
			this.port = Integer.parseInt(configXMLObject.getChild(5).getValue());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getActualFPS() {
		return actualFPS;
	}

	public String getGamePath() {
		return gamePath;
	}

	public int getHeight() {
		return height;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
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

}
