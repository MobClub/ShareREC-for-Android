package com.example.systemrecorderdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.sharerec.recorder.OnRecorderStateListener;
import cn.sharerec.recorder.Recorder;
import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
import cn.sharerec.recorder.Recorder.LevelVideoQuality;
import cn.sharerec.recorder.impl.SystemRecorder;

public class StartActivity extends BaseStartActivity implements OnRecorderStateListener {
	private MenuItem item;
	private static SystemRecorder recorder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		if (i != null && i.getBooleanExtra("STOP_RECORDER", false)) {
			finish();
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			nm.cancel(0);
			stopRecorder();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		boolean res = super.onCreateOptionsMenu(menu);
		menu.removeItem(0);
		menu.add(R.string.start);
		menu.add(R.string.video_center);
		menu.add(R.string.profile);
		return res;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		this.item = item;
		return super.onOptionsItemSelected(item);
	}

	protected boolean onStartDemo(Intent data) {
		if (item != null) {
			String title = item.getTitle().toString();
			item = null;
			if (title.equals(getString(R.string.start))) {
				startRecorder(data);
				return true;
			} else if (title.equals(getString(R.string.video_center))) {
				showVideoCenter(data);
			} else {
				showProfile(data);
			}
		}
		return false;
	}

	private void startRecorder(Intent data) {
		if (recorder == null) {
			recorder = new SystemRecorder();
		}
		recorder.setMaxFrameSize(LevelMaxFrameSize.valueOf(data.getStringExtra("srec_key_maxFrameSize")));
		recorder.setVideoQuality(LevelVideoQuality.valueOf(data.getStringExtra("srec_key_videoQuality")));
		if ("true".equalsIgnoreCase(data.getStringExtra("srec_key_force_landscape"))) {
			recorder.setForceLandscape();
		}
		recorder.setMinDuration(1000 * Long.parseLong(data.getStringExtra("srec_key_minDuration")));
		recorder.setCacheFolder(data.getStringExtra("srec_key_cacheFolder"));
		boolean sWAudioEnc = "true".equalsIgnoreCase(data.getStringExtra("srec_key_softwareAudioEncoder"));
		boolean sWVideoEnc = "true".equalsIgnoreCase(data.getStringExtra("srec_key_softwareVideoEncoder"));
		recorder.setForceSoftwareEncoding(sWVideoEnc, sWAudioEnc);
		if (recorder.isAvailable()) {
			recorder.start();
		}

		String title = getString(R.string.app_name);
		Notification not = new Notification(R.drawable.ic_launcher, title, System.currentTimeMillis());
		not.flags = Notification.FLAG_NO_CLEAR;
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent i = new Intent();
		i.setComponent(new ComponentName(getPackageName(), getClass().getName()));
		i.putExtra("STOP_RECORDER", true);
		PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
		String msg = getString(R.string.click_to_stop);
		not.setLatestEventInfo(getApplicationContext(), title, msg, pi);
		nm.notify(0, not);
	}

	private void showVideoCenter(Intent data) {
		if (recorder == null) {
			recorder = new SystemRecorder();
		}
		recorder.showVideoCenter();
	}

	private void showProfile(Intent data) {
		if (recorder == null) {
			recorder = new SystemRecorder();
		}
		recorder.showProfile();
	}

	private void stopRecorder() {
		if (recorder != null) {
			recorder.setOnRecorderStateListener(this);
			recorder.stop();
			recorder = null;
		}
	}

	public void onStateChange(Recorder recorder, int state) {
		if (state == Recorder.STATE_STOPPED) {
			// show share page
			((SystemRecorder) recorder).setText("SystemRecorder Demo");
			((SystemRecorder) recorder).addCustomAttr("score", "5000");
			((SystemRecorder) recorder).addCustomAttr("name", "ShareRec Developer");
			((SystemRecorder) recorder).addCustomAttr("brand", "hehe!");
			((SystemRecorder) recorder).addCustomAttr("level", "10");
			((SystemRecorder) recorder).showShare();
		}
	}

}
