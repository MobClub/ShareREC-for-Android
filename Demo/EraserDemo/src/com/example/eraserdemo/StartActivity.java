package com.example.eraserdemo;

import android.content.Intent;

public class StartActivity extends BaseStartActivity {

	protected void onStartDemo(Intent data) {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtras(data);
		startActivity(i);
	}

}
