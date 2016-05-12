package com.mob.rootrecorder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.sharerec.recorder.OnRecorderStateListener;
import cn.sharerec.recorder.Recorder;
import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
import cn.sharerec.recorder.impl.RootRecorder;

public class MainActivity extends Activity implements OnClickListener, OnRecorderStateListener {
	private static final String APPKEY = "76684bc49b3";
	private static final String APPSECRET = "cc162a0c24a4928e215a4b99ceffb425";
	
	private RootRecorder recorder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		findViewById(R.id.btnstart).setOnClickListener(this);
		findViewById(R.id.btnstop).setOnClickListener(this);
		findViewById(R.id.btncenter).setOnClickListener(this);
		
		recorder = new RootRecorder(this, APPKEY, APPSECRET);
		recorder.setBitRate(4000000);
        recorder.setFrameRate(30);
        recorder.setMaxFrameSize(LevelMaxFrameSize.LEVEL_1280_720);
        recorder.setOnRecorderStateListener(this);
	}

	protected void onDestroy() {
		if (recorder != null) {
			recorder.setOnRecorderStateListener(null);
    		recorder.stop();
    	}
		super.onDestroy();
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnstart: {
				if (recorder != null && recorder.isAvailable()) {
					recorder.start();
				}
			} break;
			case R.id.btnstop: {
				if (recorder != null) {
            		recorder.stop();
            	}
			} break;
			case R.id.btncenter: {
				if (recorder != null) {
					recorder.showVideoCenter();
				}
			} break;
		}
	}

	public void onStateChange(Recorder recorder, int state) {
		switch (state) {
			case Recorder.STATE_STARTED: {
				toast("root recorder started");
			} break;
			case Recorder.STATE_STOPPED: {
				toast("root recorder stopped");
				this.recorder.showShare();
			} break;
		}
	}
	
	private void toast(final String msg) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
