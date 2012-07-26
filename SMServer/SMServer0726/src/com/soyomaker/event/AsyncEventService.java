package com.soyomaker.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;

public class AsyncEventService extends AbstractBean implements IEventService {
	private ArrayBlockingQueue<GObject> msgQueue;
	private ExecutorService executors;
	private boolean dispatchThreadRunFlag = true;
	private Thread dispatchThread;

	private int queueSize = 1000;
	private int dispatchThreadCount = 5;

	private EventHandlerFactory handlerFactory = null;

	@Override
	public void doCommand(IGObject command) {

	}

	public void fireEvent(GObject e) {
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
	public IGObject getStatus() {
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
		msgQueue = new ArrayBlockingQueue<GObject>(queueSize);
		executors = Executors.newFixedThreadPool(dispatchThreadCount);
		startDispathThread();
	}

	@Override
	public void stop() {
		dispatchThreadRunFlag = false;
	}

	private void handleEvent() {
		while (dispatchThreadRunFlag) {
			final GObject e = (GObject) msgQueue.poll();
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
