package com.mob.rootrecorder;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

class RootGLSurfaceView extends GLSurfaceView {

	public RootGLSurfaceView(Context context) {
		super(context);
		init();
	}
	
	public RootGLSurfaceView(Context context, AttributeSet attrs) {
		super(context,attrs);
		init();
	}
	
	private void init() {
		try {
	        setEGLConfigChooser(8, 8, 8, 0, 16, 0);
	        setEGLContextClientVersion(2);
			setRenderer(new RootRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}