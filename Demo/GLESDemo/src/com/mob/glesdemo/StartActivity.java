package com.mob.glesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.text.TextUtils;

public class StartActivity extends BaseStartActivity {
	private ListPreference lpOpenGLVersion;

	protected void onCreatePreferences(PreferenceScreen ps) {
		PreferenceCategory pcApp = (PreferenceCategory) ps.getPreference(0);
		lpOpenGLVersion = new ListPreference(this);
		lpOpenGLVersion.setKey("srec_key_openglVersion");
		lpOpenGLVersion.setTitle("OpenGL Version");
		lpOpenGLVersion.setEntries(new String[] {
				"OPENGL_ES_11",
				"OPENGL_ES_20"
		});
		lpOpenGLVersion.setEntryValues(lpOpenGLVersion.getEntries());
		lpOpenGLVersion.setDialogTitle(lpOpenGLVersion.getTitle());
		pcApp.addPreference(lpOpenGLVersion);
	}

	protected void onRefreshValues(SharedPreferences sp) {
		String glesVersion = sp.getString(lpOpenGLVersion.getKey(), null);
		if (TextUtils.isEmpty(glesVersion)) {
			lpOpenGLVersion.setValueIndex(1);
		} else {
			lpOpenGLVersion.setValue(glesVersion);
		}
		lpOpenGLVersion.setSummary(lpOpenGLVersion.getValue());
	}

	protected void onStartDemo(Intent data) {
		Intent i = new Intent(this, MainActivity.class);
		if ("OPENGL_ES_11".equals(data.getStringExtra("srec_key_openglVersion"))) {
			i.addFlags(11);
		}
		i.putExtras(data);
		startActivity(i);
	}

}
