package com.soyostar.emulator;

import com.soyostar.ui.Component;

public class Display {

    private Component currentComponent = null;

    public Component getCurrentCanvas() {
        return currentComponent;
    }

    public void setCurrentCanvas(Component currentCanvas) {
        this.currentComponent = currentCanvas;
    }
    private static Display display = new Display();

    public static Display getDefaultDisplay() {
        return display;
    }

    private Display() {
    }
}
