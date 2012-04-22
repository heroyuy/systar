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

	private SMAudioPlayer() {

	}

	private SimplePlayer player = new SimplePlayer();

	public void play(String path) {
		stop();
		player.open(new File(path));
		player.setLoop(false);
		player.play();
	}

	public void pause() {
		player.pause();
	}

	public void resume() {

	}

	public void replay() {
		player.resume();
	}

	public void stop() {
		player.stop();
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

}
