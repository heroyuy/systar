package com.soyomaker.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.soyomaker.common.AbstractBean;
import com.soyomaker.common.IService;
import com.soyomaker.data.SMObject;

public class ScheduleService extends AbstractBean implements IService {
	List<ScheduleTask> tasks = new ArrayList<ScheduleTask>();

	private ExecutorService executors;
	private int threadPoolSize = 10;
	private boolean runFlag = true;

	public void addTask(int delay, int period, Runnable run) {
		ScheduleTask t = new ScheduleTask(delay, period, run);
		tasks.add(t);
	}

	@Override
	public void initialize() {
		threadPoolSize = this.getIntParam("threadPoolSize", threadPoolSize);
	}

	@Override
	public void start() {
		runFlag = true;
		executors = Executors.newFixedThreadPool(threadPoolSize);
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (runFlag) {
					runTasks();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		});
	}

	@Override
	public void stop() {
		runFlag = false;
	}

	protected void runTasks() {
		for (ScheduleTask t : tasks) {
			long now = System.currentTimeMillis();
			if (t.getNextRuntime() < now) {
				executors.execute(t.getExec());
				t.resetNextRuntime(now);
			}
		}
	}

}
