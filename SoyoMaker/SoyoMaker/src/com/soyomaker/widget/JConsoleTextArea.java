/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.widget;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.soyomaker.util.LoopedStreams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JTextArea;
import javax.swing.text.Document;

/**
 *
 * @author Administrator
 */
public class JConsoleTextArea extends JTextArea {

    /**
     *
     * @param inStreams
     */
    public JConsoleTextArea(InputStream[] inStreams) {
        for (int i = 0; i < inStreams.length; ++i) {
            startConsoleReaderThread(inStreams[i]);
        }
    }

    /**
     *
     */
    public JConsoleTextArea() {
        try {
            final LoopedStreams ls = new LoopedStreams();
            PrintStream ps = new PrintStream(ls.getOutputStream());
            System.setOut(ps);
            System.setErr(ps);
            startConsoleReaderThread(ls.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // ConsoleTextArea()

    private void startConsoleReaderThread(
            InputStream inStream) {
        final BufferedReader br =
                new BufferedReader(new InputStreamReader(inStream));
        new Thread(new Runnable() {

            public void run() {
                StringBuilder sb = new StringBuilder();
                try {
                    String s;
                    Document doc = getDocument();
                    while ((s = br.readLine()) != null) {
                        boolean caretAtEnd = false;
                        caretAtEnd = getCaretPosition() == doc.getLength()
                                ? true : false;
                        sb.setLength(0);
                        append(sb.append(s).append("\n").toString());
                        if (caretAtEnd) {
                            setCaretPosition(doc.getLength());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    } // startConsoleReaderThread()
} // ConsoleTextArea

