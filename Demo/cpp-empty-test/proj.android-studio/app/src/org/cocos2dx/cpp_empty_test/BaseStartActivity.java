package org.cocos2dx.cpp_empty_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.mob.tools.utils.ResHelper;

import java.util.Map.Entry;

public abstract class BaseStartActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	private ListPreference lpMaxFrameSize;
	private ListPreference lpVideoQuality;
	private EditTextPreference etpMinDura;
	private EditTextPreference etpCacheFolder;
	private CheckBoxPreference cbpSWAudioEnc;
	private CheckBoxPreference cbpSWVideoEnc;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createPreferences();
	}
	
	@SuppressWarnings("deprecation")
	private void createPreferences() {
		PreferenceScreen ps = getPreferenceManager().createPreferenceScreen(this);
		setPreferenceScreen(ps);
		
		PreferenceCategory pcRecorder = new PreferenceCategory(this);
		pcRecorder.setTitle("Recorder");
		ps.addPreference(pcRecorder);
		
		lpMaxFrameSize = new ListPreference(this);
		lpMaxFrameSize.setKey("srec_key_maxFrameSize");
		lpMaxFrameSize.setTitle("Max Frame Size");
		lpMaxFrameSize.setEntries(new String[] {
				"LEVEL_480_360" ,//4:3   (Half-size VGA)
				"LEVEL_1280_720",//16:9  720p 1280*720 标清
				"LEVEL_1920_1080",//16:9  1080p 1920*1080 高清
				"LEVEL_320_240",//
				"LEVEL_400_240",
				"LEVEL_432_240",
				"LEVEL_480_320",
				"LEVEL_800_480",//5:3 WVGA 800*480 (Wide VGA)
				"LEVEL_800_600",//4:3 SVGA 800*600 (Super VGA)
				"LEVEL_854_480",
				"LEVEL_1280_768",//
				"LEVEL_2048_1152",
				"LEVEL_2560_1440", 
				
		});
		lpMaxFrameSize.setEntryValues(lpMaxFrameSize.getEntries());
		lpMaxFrameSize.setDialogTitle(lpMaxFrameSize.getTitle());
		lpMaxFrameSize.setDefaultValue("LEVEL_1280_720");
		pcRecorder.addPreference(lpMaxFrameSize);
		
		lpVideoQuality = new ListPreference(this);
		lpVideoQuality.setKey("srec_key_videoQuality");
		lpVideoQuality.setTitle("Video Quality");
		lpVideoQuality.setEntries(new String[] {
				"LEVEL_SUPER_LOW",
				"LEVEL_VERY_LOW",
				"LEVEL_LOW",
				"LEVEL_MEDIUN",
				"LEVEL_HIGH",
				"LEVEL_VERY_HIGH",
				"LEVEL_SUPER_HIGH"
		});
		lpVideoQuality.setEntryValues(lpVideoQuality.getEntries());
		lpVideoQuality.setDialogTitle(lpVideoQuality.getTitle());
		pcRecorder.addPreference(lpVideoQuality);
		
		etpMinDura = new EditTextPreference(this);
		etpMinDura.setKey("srec_key_minDuration");
		etpMinDura.setTitle("Min Duration");
		etpMinDura.setDialogTitle(etpMinDura.getTitle());
		pcRecorder.addPreference(etpMinDura);
		
		etpCacheFolder = new EditTextPreference(this);
		etpCacheFolder.setKey("srec_key_cacheFolder");
		etpCacheFolder.setTitle("Cache Folder");
		etpCacheFolder.setDialogTitle(etpCacheFolder.getTitle());
		pcRecorder.addPreference(etpCacheFolder);
		
		PreferenceCategory pcEncoder = new PreferenceCategory(this);
		pcEncoder.setTitle("Encoder");
		ps.addPreference(pcEncoder);
		
		cbpSWAudioEnc = new CheckBoxPreference(this);
		cbpSWAudioEnc.setKey("srec_key_softwareAudioEncoder");
		cbpSWAudioEnc.setTitle("Software Audio Encoder");
		pcEncoder.addPreference(cbpSWAudioEnc);
		
		cbpSWVideoEnc = new CheckBoxPreference(this);
		cbpSWVideoEnc.setKey("srec_key_softwareVideoEncoder");
		cbpSWVideoEnc.setTitle("Software Video Encoder");
		pcEncoder.addPreference(cbpSWVideoEnc);
		
		onCreatePreferences(ps);
	}
	
	protected void onCreatePreferences(PreferenceScreen ps) {
		
	}
	
	@SuppressWarnings("deprecation")
	protected void onResume() {
		super.onResume();
		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
		refreshValues(sp);
		sp.registerOnSharedPreferenceChangeListener(this);
	}
	
	private void refreshValues(SharedPreferences sp) {
		String maxFrameSize = sp.getString(lpMaxFrameSize.getKey(), null);
		if (TextUtils.isEmpty(maxFrameSize)) {
			lpMaxFrameSize.setValueIndex(1);
		} else {
			lpMaxFrameSize.setValue(maxFrameSize);
		}
		lpMaxFrameSize.setSummary(lpMaxFrameSize.getValue());
		
		String videoQuality = sp.getString(lpVideoQuality.getKey(), null);
		if (TextUtils.isEmpty(videoQuality)) {
			lpVideoQuality.setValueIndex(4);
		} else {
			lpVideoQuality.setValue(videoQuality);
		}
		lpVideoQuality.setSummary(lpVideoQuality.getValue());
		
		int minDura = 4;
		try {
			String dura = sp.getString(etpMinDura.getKey(), null);
			minDura = Integer.parseInt(dura);
		} catch (Throwable t) {}
		etpMinDura.setText(String.valueOf(minDura < 0 ? 4 : minDura));
		etpMinDura.setSummary(minDura + (minDura > 1 ?  " Seconds" : " Second"));
		
		String cacheFolder = sp.getString(etpCacheFolder.getKey(), null);
		etpCacheFolder.setText(TextUtils.isEmpty(cacheFolder) ? ResHelper.getCachePath(this, "videoes") : cacheFolder);
		etpCacheFolder.setSummary(etpCacheFolder.getText());
		
		cbpSWAudioEnc.setChecked(sp.getBoolean(cbpSWAudioEnc.getKey(), true));
		cbpSWVideoEnc.setChecked(sp.getBoolean(cbpSWVideoEnc.getKey(), true));
		
		onRefreshValues(sp);
	}
	
	protected void onRefreshValues(SharedPreferences sp) {
		
	}
	
	@SuppressWarnings("deprecation")
	protected void onPause() {
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onPause();
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		refreshValues(sharedPreferences);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add("Start");
		mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return super.onCreateOptionsMenu(menu);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onOptionsItemSelected(MenuItem item) {
		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
		Intent data = new Intent();
		for (Entry<String, ?> ent : sp.getAll().entrySet()) {
			Object value = ent.getValue();
			data.putExtra(ent.getKey(), value == null ? null : String.valueOf(value));
		}
		onStartDemo(data);
		finish();
		return super.onOptionsItemSelected(item);
	}

	protected abstract void onStartDemo(Intent sp);
	
}
