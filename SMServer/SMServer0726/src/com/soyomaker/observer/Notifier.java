package com.soyomaker.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 通知中心类
 *
 * @author cokey
 */
public class Notifier {

    private static Notifier mInstance = new Notifier();
    private Map<String, Collection<Observer>> mObserverMap = new HashMap<String, Collection<Observer>>();

    private Notifier() {
    }

    /**
     *
     * @return
     */
    public static Notifier getInstance() {
        return mInstance;
    }

    /**
     *
     * @param observer
     * @param command
     */
    public synchronized void addObserver(Observer observer, String command) {
        Collection<Observer> c = mObserverMap.get(command);
        if (c == null) {
            c = new HashSet<Observer>(); // 注：此处一定要使用禁止重复元素的Collection子类
            mObserverMap.put(command, c);
        }
        c.add(observer);
    }

    /**
     *
     * @param observer
     * @param commands
     */
    public synchronized void addObserver(Observer observer, String... commands) {
        for (String command : commands) {
            Collection<Observer> c = mObserverMap.get(command);
            if (c == null) {
                c = new HashSet<Observer>(); // 注：此处一定要使用禁止重复元素的Collection子类
                mObserverMap.put(command, c);
            }
            c.add(observer);
        }
    }

    /**
     *
     * @param observer
     * @param commands
     */
    public synchronized void addObserver(Observer observer, ArrayList<String> commands) {
        for (String command : commands) {
            Collection<Observer> c = mObserverMap.get(command);
            if (c == null) {
                c = new HashSet<Observer>(); // 注：此处一定要使用禁止重复元素的Collection子类
                mObserverMap.put(command, c);
            }
            c.add(observer);
        }
    }

    /**
     *
     * @param observer
     * @param commands
     */
    public synchronized void removeObserverForCommand(Observer observer, String... commands) {
        for (String command : commands) {
            Collection<Observer> c = mObserverMap.get(command);
            if (c != null && c.contains(observer)) {
                c.remove(observer);
            }
        }
    }

    /**
     *
     * @param observer
     * @param commands
     */
    public synchronized void removeObserverForCommand(Observer observer, ArrayList<String> commands) {
        for (String command : commands) {
            Collection<Observer> c = mObserverMap.get(command);
            if (c != null && c.contains(observer)) {
                c.remove(observer);
            }
        }
    }

    /**
     *
     * @param observer
     * @param command
     */
    public synchronized void removeObserverForCommand(Observer observer, String command) {
        Collection<Observer> c = mObserverMap.get(command);
        if (c != null && c.contains(observer)) {
            c.remove(observer);
        }
    }

    /**
     *
     * @param observer
     */
    public synchronized void removeObserverForAllCommands(Observer observer) {
        Set<Map.Entry<String, Collection<Observer>>> set = mObserverMap.entrySet();
        for (Iterator<Map.Entry<String, Collection<Observer>>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, Collection<Observer>> entry = (Map.Entry<String, Collection<Observer>>) it.next();
            entry.getValue().remove(observer);
        }
    }

    /**
     *
     * @param command
     */
    public synchronized void removeAllObserversForCommand(String command) {
        Collection<Observer> c = mObserverMap.get(command);
        if (c != null && !c.isEmpty()) {
            c.clear();
        }
    }

    /**
     *
     */
    public synchronized void removeAllObservers() {
        if (!mObserverMap.isEmpty()) {
            mObserverMap.clear();
        }
    }

    /**
     *
     * @param command
     * @param param
     */
    public synchronized void notifyEvent(String command, Object... param) {
        // 注:此方法要考虑线程同步
        Event e = Event.createEvent(command, param);
        Collection<Observer> c = mObserverMap.get(command);
        if (c != null) {
            for (Observer observer : c) {
                observer.handleEvent(e);
            }
        }
    }
}
