package org.cocos2dx.simplegame;

import android.os.Bundle;
import cn.sharerec.gui.RecBar;
import cn.sharerec.gui.Cocs2dxRecBarActivity;

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
	
	protected String getAppSecret() {
		return "cc162a0c24a4928e215a4b99ceffb425";
	}
	
    static {
        System.loadLibrary("game");
    }
    
}