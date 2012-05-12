/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.script;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Administrator
 */
public class HighLightWord {

    ScriptEditorKit kit = new ScriptEditorKit();

    /**
     *
     */
    public HighLightWord() {
    }

    /**
     *
     * @param tp
     */
    public void setHightLight(JTextPane tp) {
        tp.setFont(new Font("DialogInput", Font.PLAIN, 16));
        tp.setEditorKit(kit);
        tp.setEditorKitForContentType("text/java", kit);
        tp.setContentType("text/java");
    }
}