/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

/**
 * 打印机工厂类
 * @author like
 */
public class LogPrinterFactory {

    private LogPrinterFactory() {
    }

    private static class LogPrinterCache {

        public static final LogPrinter logPrinter = new LogPrinter();
        public static final AbLogFormater logFormat = new DefaultLogFormater();
        public static final DefaultLogDisplayer dld = new DefaultLogDisplayer();

        static {
            logFormat.setPrintTime(false);
            logPrinter.setLogFormater(LogPrinterCache.logFormat);
            logPrinter.addLogDisplayer(LogPrinterCache.dld);
        }
    }

    /**
     * 返回一个默认的单例日志打印机
     * @return
     */
    public static LogPrinter getDefaultLogPrinter() {
        return LogPrinterCache.logPrinter;
    }
}
