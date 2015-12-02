package com.mob.flogjump;

import cn.sharerec.gui.Cocs2dxRecBarActivity;
import cn.sharerec.gui.RecBar;
import android.os.Bundle;

public class RECBarDemo extends Cocs2dxRecBarActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showRecBar();
		setRecBarAnchor(RecBar.ANCHOR_RIGHT);
		setShareText("Create by ShareREC for Cocos2d-x with RecBar");
	}
	
	protected String getAppkey() {
		return "76684bc49b3";
	}
	
}