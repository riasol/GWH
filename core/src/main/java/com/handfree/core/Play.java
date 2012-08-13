package com.handfree.core;


public abstract class Play {

    public abstract String name();

    public abstract void init();

    public abstract void shutdown();

    public void update(float delta) {
    }

    public void paint(float alpha) {
    }

}
