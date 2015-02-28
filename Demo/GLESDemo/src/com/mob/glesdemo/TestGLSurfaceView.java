package com.mob.glesdemo;

import android.content.Context;
import android.util.AttributeSet;
import cn.sharerec.recorder.SrecGLSurfaceView;

public class TestGLSurfaceView extends SrecGLSurfaceView {
	private static final int INTERVAL = 25;
	private CubeRenderer renderer;
	private boolean running;
	private float angleInDegrees;
	private float speed;

	public TestGLSurfaceView(Context context) {
		super(context);
		init();
	}
	
	public TestGLSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public void setSpeed(int degreesPreSecond) {
		speed = ((float) degreesPreSecond) * INTERVAL / 1000;
	}
	
	private void init() {
		renderer = new CubeRenderer();
		setRenderer(renderer);
		
		post(new Runnable() {
			public void run() {
				if (running) {
					angleInDegrees += speed;
					if (angleInDegrees > 360) {
						angleInDegrees -= 360;
					}
					renderer.setAngleInDegrees(angleInDegrees);
				}
				postDelayed(this, INTERVAL);
			}
		});
	}

	protected String getShareRecAppkey() {
		return "76684bc49b3";
	}

	public void onPause() {
		running = false;
	}
	
	public void onResume() {
		running = true;
	}
	
}
