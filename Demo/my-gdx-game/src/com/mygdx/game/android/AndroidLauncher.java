package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
import cn.sharerec.recorder.Recorder.LevelVideoQuality;

public class AndroidLauncher extends AndroidApplication {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();	
		MyGdxGame game = new MyGdxGame();
		game.getRecorder().setUseES3("true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_forceGles30")));
		game.getRecorder().setMaxFrameSize(LevelMaxFrameSize.valueOf(getIntent().getStringExtra("srec_key_maxFrameSize")));
		game.getRecorder().setVideoQuality(LevelVideoQuality.valueOf(getIntent().getStringExtra("srec_key_videoQuality")));
		game.getRecorder().setMinDuration(1000 * Long.parseLong(getIntent().getStringExtra("srec_key_minDuration")));
		game.getRecorder().setCacheFolder(getIntent().getStringExtra("srec_key_cacheFolder"));
		boolean sWAudioEnc = "true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_softwareAudioEncoder"));
		boolean sWVideoEnc = "true".equalsIgnoreCase(getIntent().getStringExtra("srec_key_softwareVideoEncoder"));
		game.getRecorder().setForceSoftwareEncoding(sWVideoEnc, sWAudioEnc);
		//需要的时候调用，不用每次都调用。发生更改后再调用即可。
//		ShareREC.setWontBeBindPlatforms(SinaWeibo.NAME, Facebook.NAME, Wechat.NAME, WechatMoments.NAME);
//		ShareREC.setShowBindPhone(false);
//		User user = new User();
//		user.setUid("120");
//		user.setNickName("大宝宝5678");
//		user.setAvatarUrl("https://timgsa.baidu.com/timg?image&.jpg");
//
//		ShareREC.updateUserByApp(user);
		initialize(game, config);
	}
	
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	
}
