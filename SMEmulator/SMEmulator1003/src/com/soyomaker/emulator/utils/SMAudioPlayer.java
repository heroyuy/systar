package com.soyomaker.emulator.utils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import saint.media.SimplePlayer;

public class SMAudioPlayer {

	private static SMAudioPlayer instance = new SMAudioPlayer();

	public static SMAudioPlayer getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		final SMAudioPlayer smap = SMAudioPlayer.getInstance();
		smap.play("game/audio/music/my_love.mp3");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				smap.stop();
			}
		}, 5000);
	}

	private SimplePlayer player = new SimplePlayer();

	private SMAudioPlayer() {

	}

	public void pause() {
		player.pause();
	}

	public void play(String path) {
		stop();
		player.open(new File(path));
		player.setLoop(false);
		player.play();
	}

	public void replay() {
		player.resume();
	}

	public void resume() {

	}

	public void stop() {
		player.stop();
	}

}
