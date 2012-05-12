/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Administrator
 */
public class DefaultLoggerUI implements ILoggerUI {

    private JTextPane textPane;
    private StyledDocument document;
    private JScrollPane scrollPane;

    /**
     *
     */
    public DefaultLoggerUI() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        textPane.setOpaque(true);
        textPane.setBackground(Color.WHITE);
        document = textPane.getStyledDocument();
    }

    /**
     *
     * @param content
     */
    public void d(Object content) {
        System.out.println("<d> " + content);
        try {
            document.insertString(document.getLength(), "<d> " + content + "\n", new LogFontAttribute(LogFontAttribute.D_ATTRIBUTE).getAttrSet());
        } catch (BadLocationException ex) {
            Logger.getLogger(DefaultLoggerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param content
     */
    public void v(Object content) {
        System.out.println("<v> " + content);
        try {
            document.insertString(document.getLength(), "<v> " + content + "\n", new LogFontAttribute(LogFontAttribute.V_ATTRIBUTE).getAttrSet());
        } catch (BadLocationException ex) {
            Logger.getLogger(DefaultLoggerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param content
     */
    public void w(Object content) {
        System.out.println("<w> " + content);
        try {
            document.insertString(document.getLength(), "<w> " + content + "\n", new LogFontAttribute(LogFontAttribute.W_ATTRIBUTE).getAttrSet());
        } catch (BadLocationException ex) {
            Logger.getLogger(DefaultLoggerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param content
     */
    public void e(Object content) {
        System.out.println("<e> " + content);
        try {
            document.insertString(document.getLength(), "<e> " + content + "\n", new LogFontAttribute(LogFontAttribute.E_ATTRIBUTE).getAttrSet());
        } catch (BadLocationException ex) {
            Logger.getLogger(DefaultLoggerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String log(String str) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String className = ste.getClassName();
        String methodName = ste.getMethodName();
        int lineNumber = ste.getLineNumber();
        String ret = className + "." + methodName + "()." + lineNumber + " says:" + str;
        return ret;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return "软件日志";
    }

    /**
     *
     * @return
     */
    public JComponent getTextComponentContainer() {
        return scrollPane;
    }
}
