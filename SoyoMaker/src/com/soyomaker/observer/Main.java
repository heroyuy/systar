package com.soyomaker.observer;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestObserver to = new TestObserver();
        TestObserver2 to2 = new TestObserver2();
        Notifier.getInstance().addObserver(to, "GameBuildSuccessful");
        Notifier.getInstance().addObserver(to, "GameBuildFailure");
        Notifier.getInstance().addObserver(to2, "GameBuildFailure");
        System.out.println("发通知:GameBuildSuccessful");
        Notifier.getInstance().notifyEvent("GameBuildSuccessful");
        System.out.println("发通知:GameBuildFailure");
        Notifier.getInstance().notifyEvent("GameBuildFailure", new Main());
    }

    @Override
    public String toString() {
        return super.toString() + ":[My name is \"Main\"]";
    }
}

class TestObserver2 implements Observer {

    @Override
    public void handleEvent(Event e) {
        System.out.println("TestObserver2收到通知，事件是:" + e);
    }
}

class TestObserver implements Observer {

    @Override
    public void handleEvent(Event e) {
        System.out.println("TestObserver收到通知，事件是:" + e);
    }
}
