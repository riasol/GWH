package com.handfree.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.handfree.core.GWH;

public class GWHJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/handfree/resources");
    PlayN.run(new GWH());
  }
}
