package com.soyomaker.emulator.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class GameConfig {

	private static String CONFIG_PATH = "plugin/emulator/config/emulator.xml";

	private static GameConfig instance = new GameConfig();

	public static GameConfig getInstance() {
		return instance;
	}

	private int width = 960;

	private int height = 640;

	private int ratedFPS = 20;

	private int actualFPS = 0;

	private boolean showFPS = true;

	private String host = null;

	private int port = 0;

	private String gamePath = null;

	private GameConfig() {
		try {
			XMLObject configXMLObject = XMLParser.parse(new File(CONFIG_PATH));
			this.width = Integer.parseInt(configXMLObject.getChild("width")
					.getValue());
			this.height = Integer.parseInt(configXMLObject.getChild("height")
					.getValue());
			this.ratedFPS = Integer.parseInt(configXMLObject.getChild("fps")
					.getValue());
			this.showFPS = Boolean.parseBoolean(configXMLObject.getChild(
					"showFPS").getValue());
			XMLObject serversXMLObject = configXMLObject.getChild("servers");
			String userServer = configXMLObject.getChild("userServer")
					.getValue();
			for (XMLObject serverXMLObject : serversXMLObject.getChildList()) {
				String id = serverXMLObject.getChild("id").getValue();
				if (id.equals(userServer)) {
					this.host = serverXMLObject.getChild("host").getValue();
					this.port = Integer.parseInt(serverXMLObject.getChild(
							"port").getValue());
					break;
				}
			}
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

	public String getHost() {
		return host;
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
