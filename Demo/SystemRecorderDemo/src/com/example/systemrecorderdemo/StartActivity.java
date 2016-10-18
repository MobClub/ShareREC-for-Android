package com.example.systemrecorderdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.view.Menu;
import android.view.MenuItem;

import cn.sharerec.recorder.OnRecorderStateListener;
import cn.sharerec.recorder.Recorder;
import cn.sharerec.recorder.Recorder.LevelVideoQuality;
import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
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

	protected void onCreatePreferences(PreferenceScreen ps) {
		ps.removePreference(ps.getPreference(2));
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

	protected void onStartDemo(Intent data) {
		if (item != null) {
			if (item.getTitle().equals(getString(R.string.start))) {
				startRecorder(data);
			} else if (item.getTitle().equals(getString(R.string.video_center))) {
				showVideoCenter(data);
			} else {
				showProfile(data);
			}
			item = null;
		}
	}

	private void startRecorder(Intent data) {
		if (recorder == null) {
			String appkey = data.getStringExtra("srec_key_appKey");
			String appsecret = data.getStringExtra("srec_key_appSecret");
			recorder = new SystemRecorder(this, appkey, appsecret);
		}
		recorder.setMaxFrameSize(LevelMaxFrameSize.valueOf(data.getStringExtra("srec_key_maxFrameSize")));
		recorder.setVideoQuality(LevelVideoQuality.valueOf(data.getStringExtra("srec_key_videoQuality")));
		recorder.setMinDuration(1000 * Long.parseLong(data.getStringExtra("srec_key_minDuration")));
		recorder.setCacheFolder(data.getStringExtra("srec_key_cacheFolder"));
		if (recorder.isAvailable()) {
			recorder.start();
		}

		String msg = getString(R.string.click_to_stop);
		Notification not = new Notification(R.drawable.ic_launcher, msg, System.currentTimeMillis());
		not.flags = Notification.FLAG_NO_CLEAR;
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent i = new Intent();
		i.setComponent(new ComponentName(getPackageName(), getClass().getName()));
		i.putExtra("STOP_RECORDER", true);
		not.contentIntent = PendingIntent.getActivity(this, 0, i, 0);
		nm.notify(0, not);

		finish();
	}

	private void showVideoCenter(Intent data) {
		if (recorder == null) {
			String appkey = data.getStringExtra("srec_key_appKey");
			String appsecret = data.getStringExtra("srec_key_appSecret");
			recorder = new SystemRecorder(this, appkey, appsecret);
		}
		recorder.showVideoCenter();
	}

	private void showProfile(Intent data) {
		if (recorder == null) {
			String appkey = data.getStringExtra("srec_key_appKey");
			String appsecret = data.getStringExtra("srec_key_appSecret");
			recorder = new SystemRecorder(this, appkey, appsecret);
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
