/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.script;

import javax.swing.text.*;

/**
 *
 * @author Administrator
 */
public class ScriptViewFactory implements ViewFactory {

    public View create(Element element) {
        return new ScriptEditorView(element);
    }
}