package com.handfree.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

public class GWH implements Game {
  @Override
  public void init() {
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
  }

  @Override
  public void update(float delta) {
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
