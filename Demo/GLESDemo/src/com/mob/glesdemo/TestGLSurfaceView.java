package com.mob.glesdemo;

import com.mob.glesdemo.gles.CubeRenderer;
import com.mob.glesdemo.gloes.CubeRendererES1;

import android.content.Context;
import android.util.AttributeSet;
import cn.sharerec.recorder.impl.SrecGLSurfaceView;

public class TestGLSurfaceView extends SrecGLSurfaceView {
	private static final int INTERVAL = 25;
	
	static boolean oesType;
	static String appkey;
	static String appsecret;
	
	private CubeRenderer renderer;
	private CubeRendererES1 rendereres1;
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
		speed = degreesPreSecond * INTERVAL / 1000f;
	}
	
	private void init() {
		if(oesType) {
			setEGLContextClientVersion(1);
			rendereres1 = new CubeRendererES1();
			setRenderer(rendereres1);
		} else {
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
					if (oesType) {
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
		return appkey;
	}

	protected String getShareRecAppSecret() {
		return appsecret;
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
