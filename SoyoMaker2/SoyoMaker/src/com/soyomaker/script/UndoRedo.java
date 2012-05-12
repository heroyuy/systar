/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.script;

import javax.swing.undo.UndoManager;

/**
 *
 * @author Administrator
 */
public class UndoRedo {

    UndoManager undoManager;

    /**
     *
     * @param undoManager
     */
    public UndoRedo(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    /**
     *
     */
    public void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    /**
     *
     */
    public void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }
}