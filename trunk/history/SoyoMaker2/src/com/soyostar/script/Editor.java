/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.script;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Administrator
 */
public class Editor {

    private Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private StringSelection stringSelection;
    private DataFlavor dataFlavor;
    private StyledDocument styledDocument;
    private JTextPane textPane;
    private String selectText;
    private int selectStart;
    private int selectEnd;
    private static Editor editor;
    //单例模式

    private Editor() {
    }

    /**
     *
     * @return
     */
    public static Editor getInstance() {
        if (editor == null) {
            editor = new Editor();
        }
        return editor;
    }

    /**
     *
     * @param tp
     * @param sd
     */
    public void link(JTextPane tp, StyledDocument sd) {
        textPane = tp;
        styledDocument = sd;
        selectStart = textPane.getSelectionStart();
        selectEnd = textPane.getSelectionEnd();

    }

    /**
     *
     * @param tp
     * @param sd
     * @throws BadLocationException
     */
    public void cut(JTextPane tp, StyledDocument sd) throws BadLocationException {
        link(tp, sd);
        selectText = styledDocument.getText(selectStart, selectEnd - selectStart);
        stringSelection = new StringSelection(selectText);
        styledDocument.remove(selectStart, selectEnd - selectStart);
        clipBoard.setContents(stringSelection, null);

    }

    /**
     *
     * @param tp
     * @param sd
     * @throws BadLocationException
     */
    public void copy(JTextPane tp, StyledDocument sd) throws BadLocationException {
        link(tp, sd);
        selectText = styledDocument.getText(selectStart, selectEnd - selectStart);
        stringSelection = new StringSelection(selectText);
        clipBoard.setContents(stringSelection, null);
    }

    /**
     *
     * @param tp
     * @param sd
     * @throws BadLocationException
     */
    public void paste(JTextPane tp, StyledDocument sd) throws BadLocationException {
        link(tp, sd);
        JDialog jd = ScriptManagerDialog.getInstance();
        Transferable contents = clipBoard.getContents(jd);
        if (contents == null) {
            return;
        }
        try {
            selectText = (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jd, "Error file transfer");
        }
        textPane.replaceSelection(selectText);

    }

    /**
     *
     * @param tp
     * @param sd
     * @throws BadLocationException
     */
    public void delete(JTextPane tp, StyledDocument sd) throws BadLocationException {
        link(tp, sd);
        styledDocument.remove(selectStart, selectEnd - selectStart);

    }

    /**
     *
     * @param tp
     * @param sd
     * @throws BadLocationException
     */
    public void selectAll(JTextPane tp, StyledDocument sd) throws BadLocationException {
        link(tp, sd);
        textPane.selectAll();

    }
}