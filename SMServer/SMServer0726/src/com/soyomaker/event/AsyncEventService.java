package com.soyomaker.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.GUObject;
import com.soyomaker.data.IGUObject;

public class AsyncEventService extends AbstractBean implements IEventService {
	private ArrayBlockingQueue<GUObject> msgQueue;
	private ExecutorService executors;
	private boolean dispatchThreadRunFlag = true;
	private Thread dispatchThread;

	private int queueSize = 1000;
	private int dispatchThreadCount = 5;

	private EventHandlerFactory handlerFactory = null;

	@Override
	public void doCommand(IGUObject command) {

	}

	public void fireEvent(GUObject e) {
		try {
			msgQueue.put(e);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public int getDispatchThreadCount() {
		return dispatchThreadCount;
	}

	public EventHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	public int getQueueSize() {
		return queueSize;
	};

	@Override
	public IGUObject getStatus() {
		return null;
	}

	@Override
	public void initialize() {

	}

	public void setDispatchThreadCount(int dispatchThreadCount) {
		this.dispatchThreadCount = dispatchThreadCount;
	}

	public void setHandlerFactory(EventHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	@Override
	public void start() {
		msgQueue = new ArrayBlockingQueue<GUObject>(queueSize);
		executors = Executors.newFixedThreadPool(dispatchThreadCount);
		startDispathThread();
	}

	@Override
	public void stop() {
		dispatchThreadRunFlag = false;
	}

	private void handleEvent() {
		while (dispatchThreadRunFlag) {
			final GUObject e = (GUObject) msgQueue.poll();
			executors.execute(new Runnable() {
				@Override
				public void run() {
					handlerFactory.handleMessage(e);
				}
			});
		}
	}

	private void startDispathThread() {
		dispatchThread = new Thread(new Runnable() {
			@Override
			public void run() {
				handleEvent();
			}
		});
		dispatchThread.start();
	}
}
