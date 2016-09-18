package org.cocos2dx.cpp_empty_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class StartActivity extends BaseStartActivity {
	private CheckBoxPreference cbpForceGLES30;
	
	protected void onCreatePreferences(PreferenceScreen ps) {
		PreferenceCategory pcRecorder = (PreferenceCategory) ps.getPreference(1);
		cbpForceGLES30 = new CheckBoxPreference(this);
		cbpForceGLES30.setKey("srec_key_forceGles30");
		cbpForceGLES30.setTitle("Use GLES30 API");
		pcRecorder.addPreference(cbpForceGLES30);
	}
	
	protected void onRefreshValues(SharedPreferences sp) {
		cbpForceGLES30.setChecked(sp.getBoolean(cbpForceGLES30.getKey(), false));
	}

	protected void onStartDemo(Intent data) {
		Intent i = new Intent(this, AppActivity.class);
		i.putExtras(data);
		startActivity(i);
	}

}
