/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.log;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Administrator
 */
public class SMLogDisplayer implements ILogDisplayer {

    private JTextPane textPane;
    private StyledDocument document;
    private JScrollPane scrollPane;
    private String name = "日志";

    /**
     *
     */
    public SMLogDisplayer() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setOpaque(true);
        textPane.setBackground(Color.WHITE);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        document = textPane.getStyledDocument();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param log
     */
    public void displayLog(Log log) {
        try {
            document.insertString(document.getLength(), log.getContent().toString() + "\n", new SMLogAttribute(log.getAttribute().getLevel()).getAttrSet());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public JScrollPane getJScrollPane() {
        return scrollPane;
    }
}
