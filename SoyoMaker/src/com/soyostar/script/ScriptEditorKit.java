/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.script;

import javax.swing.text.*;

/**
 *
 * @author Administrator
 */
public class ScriptEditorKit extends StyledEditorKit {

    @Override
    public ViewFactory getViewFactory() {
        return new ScriptViewFactory();
    }
}