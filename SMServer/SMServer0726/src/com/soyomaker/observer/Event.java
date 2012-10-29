package com.soyomaker.observer;

/**
 * 事件类
 *
 * @author cokey
 */
public class Event {

    private String mCommand = null;
    private Object[] mParameters = null;

    /**
     *
     * @param command 
     * @param param 
     * @mParameters command
     * @mParameters mParameters
     * @return
     */
    public static Event createEvent(String command, Object... param) {
        return new Event(command, param);
    }

    private Event(String command, Object... param) {
        this.mCommand = command;
        this.mParameters = param;
    }

    /**
     *
     * @return
     */
    public String getCommand() {
        return mCommand;
    }

    /**
     *
     * @return
     */
    public Object[] getParameters() {
        return mParameters;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Event{command=\"" + mCommand + "\" param=" + mParameters.toString() + "}";
    }
}
