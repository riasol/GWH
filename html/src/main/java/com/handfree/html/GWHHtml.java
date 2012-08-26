package com.handfree.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.handfree.core.GWH;
import com.handfree.core.GWHConstans;

public class GWHHtml extends HtmlGame {

    @Override
    public void start() {
	HtmlPlatform platform = HtmlPlatform.register();
	platform.graphics().setSize(GWHConstans.WIDTH, GWHConstans.HEIGHT);
	platform.assets().setPathPrefix("GWH/");
	PlayN.run(new GWH());
    }
}
