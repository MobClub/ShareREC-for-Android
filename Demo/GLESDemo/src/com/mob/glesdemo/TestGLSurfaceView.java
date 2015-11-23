package com.mob.glesdemo;

import android.content.Context;
import android.util.AttributeSet;
import cn.sharerec.recorder.impl.SrecGLSurfaceView;

public class TestGLSurfaceView extends SrecGLSurfaceView {
	private static final int INTERVAL = 25;
	private CubeRenderer renderer;
	private CubeRendererES1 rendereres1;
	private boolean running;
	private float angleInDegrees;
	private float speed;
	private static boolean isOpeng_1 = false;

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
	
	public static void setOpenglVer( boolean isOpengl1 ){
		isOpeng_1 = isOpengl1;
	}
	
	private void init() {
		if( isOpeng_1 ){
			setEGLContextClientVersion(1);
			rendereres1 = new CubeRendererES1();
			setRenderer(rendereres1);
		}else{
			setEGLContextClientVersion(2);
			renderer = new CubeRenderer();
			setRenderer(renderer);
		}

		post(new Runnable() {
			public void run() {
				if (running) {
					angleInDegrees += speed;
					if (angleInDegrees > 360) {
						angleInDegrees -= 360;
					}
					if (isOpeng_1) {
						rendereres1.setAngleInDegrees(angleInDegrees);
					} else {
						renderer.setAngleInDegrees(angleInDegrees);
					}
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
	
	public void setFboEnable(boolean enable) {
		if (renderer != null) {
			renderer.setFboEnable(enable);
		}
	}
	
}
