package com.handfree.core;

import playn.core.Keyboard;

public abstract class Play {

    public abstract String name();

    public abstract void init();

    public abstract void shutdown();

    public void update(float delta) {
    }

    public void paint(float alpha) {
    }

    public abstract Keyboard.Listener getKeyboardListener();
}
