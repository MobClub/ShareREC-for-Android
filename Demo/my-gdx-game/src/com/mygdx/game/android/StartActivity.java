package com.mygdx.game.android;

import android.content.Intent;

public class StartActivity extends BaseStartActivity {

	protected void onStartDemo(Intent data) {
		Intent i = new Intent(this, AndroidLauncher.class);
		i.putExtras(data);
		startActivity(i);
	}

}
