package com.handfree.core;

import org.jbox2d.dynamics.World;

import playn.core.GroupLayer;

public interface Box2dWorld {

    World getWorld();

    GroupLayer getDynamicLayer();

}
