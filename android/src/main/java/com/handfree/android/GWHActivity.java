package com.handfree.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.handfree.core.GWH;

public class GWHActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/handfree/resources");
    PlayN.run(new GWH());
  }
}
