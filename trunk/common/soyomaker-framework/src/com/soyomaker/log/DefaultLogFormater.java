/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import java.util.Calendar;

/**
 *
 * @author like
 */
public class DefaultLogFormater extends AbLogFormater {

    @Override
    public Log format(Log log) {
        StringBuilder sb = new StringBuilder();
        if (this.printTime) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            sb.append(hour).append(":").append(minute).append(":").append(second).append(" ");
        }
        if (this.printHead) {
            LogAttribute logAttribute = log.getAttribute();
            switch (logAttribute.getLevel()) {
                case LogAttribute.D:
                    sb.append("<D> ");
                    break;
                case LogAttribute.E:
                    sb.append("<E> ");
                    break;
                case LogAttribute.V:
                    sb.append("<V> ");
                    break;
                case LogAttribute.W:
                    sb.append("<W> ");
                    break;
            }
        }
        sb.append(log.getContent().toString());
        log.setContent(sb.toString());
        return log;
    }
}
