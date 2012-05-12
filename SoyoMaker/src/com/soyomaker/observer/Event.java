package com.soyomaker.observer;

/**
 *
 * @author Administrator
 */
public class Event {

    /**
     *
     * @param command
     * @param param
     * @return
     */
    public static Event createEvent(String command, Object... param) {
        return new Event(command, param);
    }
    private String command = null;
    private Object[] param = null;

    private Event(String command, Object... param) {
        this.command = command;
        this.param = param;
    }

    /**
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @return
     */
    public Object[] getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "Event{command=\"" + command + "\" param=" + param.toString() + "}";
    }
}
