package com.mygdx.game.android;

import android.os.Bundle;
import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
import cn.sharerec.recorder.Recorder.LevelVideoQuality;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		String appkey = getIntent().getStringExtra("srec_key_appKey");
		String appsecret = getIntent().getStringExtra("srec_key_appSecret");
		MyGdxGame game = new MyGdxGame(this, appkey, appsecret);
		game.getRecorder().setUseES3("true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_forceGles30")));
		game.getRecorder().setMaxFrameSize(LevelMaxFrameSize.valueOf(getIntent().getStringExtra("srec_key_maxFrameSize")));
		game.getRecorder().setVideoQuality(LevelVideoQuality.valueOf(getIntent().getStringExtra("srec_key_videoQuality")));
		game.getRecorder().setMinDuration(1000 * Long.parseLong(getIntent().getStringExtra("srec_key_minDuration")));
		game.getRecorder().setCacheFolder(getIntent().getStringExtra("srec_key_cacheFolder"));
		boolean sWAudioEnc = "true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_softwareAudioEncoder"));
		boolean sWVideoEnc = "true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_softwareVideoEncoder"));
		game.getRecorder().setForceSoftwareEncoding(sWVideoEnc, sWAudioEnc);
		
		initialize(game, config);
	}
	
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	
}
