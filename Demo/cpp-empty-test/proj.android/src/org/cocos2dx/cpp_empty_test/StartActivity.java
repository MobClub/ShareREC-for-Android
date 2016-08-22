package org.cocos2dx.cpp_empty_test;

import android.content.Intent;

public class StartActivity extends BaseStartActivity {

	protected void onStartDemo(Intent data) {
		Intent i = new Intent(this, AppActivity.class);
		i.putExtras(data);
		startActivity(i);
	}

}
