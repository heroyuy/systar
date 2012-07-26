package com.gudigital.schedule;

public class ScheduleTask implements Runnable {
	private int period;
	private Runnable exec;
	private int delay;

	private long nextRuntime;

	public ScheduleTask(int delay, int period, Runnable exec) {
		this.delay = delay;
		this.period = period;
		this.exec = exec;

		nextRuntime = System.currentTimeMillis() + delay;
	}

	public int getDelay() {
		return delay;
	}

	public Runnable getExec() {
		return exec;
	}

	public long getNextRuntime() {
		return nextRuntime;
	}

	public int getPeriod() {
		return period;
	}

	public void resetNextRuntime(long now) {
		nextRuntime = now + period;
	}

	public void run() {

	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
}
