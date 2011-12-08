package com.soyomaker.emulator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LuaFileLoader {

	public String loadFile(String path) {
		String content = null;
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			System.out.println(fr.getEncoding());
			content = br.readLine();
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {

		System.out.println(new LuaFileLoader()
				.loadFile("game/luadata/player.gat"));
	}

}
