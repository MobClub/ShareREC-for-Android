package com.mob.flogjump;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.os.Bundle;
import cn.sharerec.recorder.impl.Cocos2DRecorder;

public class AppActivity extends Cocos2dxActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public Cocos2dxGLSurfaceView onCreateView() {
		return Cocos2DRecorder.getCocos2dxGLSurfaceView("76684bc49b3", "cc162a0c24a4928e215a4b99ceffb425");
	}
}
