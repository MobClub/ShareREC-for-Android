package com.example.eraserdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import cn.sharerec.recorder.OnRecorderStateListener;
import cn.sharerec.recorder.Recorder;
import cn.sharerec.recorder.ViewRecorder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnRecorderStateListener {
	private ViewRecorder recorder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 初始化UI (init UI)
		setContentView(R.layout.main_activity);
		findViewById(R.id.btnStart).setOnClickListener(this);
		findViewById(R.id.btnStop).setOnClickListener(this);
		findViewById(R.id.btnProfile).setOnClickListener(this);
		
		// 在页面左下角显示时间 (display current time at the left-bottom of the page)
		final TextView tvTime = (TextView) findViewById(R.id.tvTime);
		tvTime.post(new Runnable() {
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss aa");
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				tvTime.setText(sdf.format(cal.getTime()));
				tvTime.postDelayed(this, 300);
			}
		});
		
		// 初始化ShareRec (init ShareRec)
		View view = findViewById(R.id.evCan);
		recorder = new ViewRecorder(view, "76684bc49b3");
		recorder.setDebuggable();
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnStart: startRecorder(); break;
			case R.id.btnStop: stopRecorder(); break;
			case R.id.btnProfile: showProfile(); break;
		}
	}
	
	protected void onPause() {
		// 暂停录像 (pause ShareRec)
		recorder.pauseRecorder();
		super.onPause();
	}
	
	protected void onResume() {
		super.onResume();
		// 恢复录像 (resume ShareRec)
		recorder.resumeRecorder();
	}
	
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	
	// 启动录像 (start ShareRec)
	private void startRecorder() {
		if (recorder.isAvailable()) {
			recorder.setOnRecorderStateListener(this);
			recorder.startRecorder();
			
			// 如果您不知道什么时候您的画面会刷新，则可以使用下面的方式，让录像模块自动抓图，
			// 否则可以调用onTheEndOfTheFrame()方法来手动驱动录像模块抓图 (If you don't know
			// when your view will refresh, you can use the following way, let ShareRec
			// capture the frame automatically. or you can call onTheEndOfTheFrame() 
			// manually when your frame refreshing)
			recorder.startAuotRefreshRate(10);
		} else {
			Toast.makeText(this, R.string.not_availiable, Toast.LENGTH_SHORT).show();
		}
	}

	// 停止录像 (stop ShareRec)
	private void stopRecorder() {
		recorder.stopRecorder();
	}

	// 显示用户资料 (show user profile)
	private void showProfile() {
		recorder.showProfile();
	}
	
	public void onStateChange(Recorder recorder, int state) {
		if (state == Recorder.STATE_STOPPED) {
			// show share page
			this.recorder.setText("Eraser Demo");
			this.recorder.addCustomAttr("score", "5000");
			this.recorder.addCustomAttr("name", "ShareRec Developer");
			this.recorder.addCustomAttr("brand", "hehe!");
			this.recorder.addCustomAttr("level", "10");
			this.recorder.showShare();
		}
	}
	
}