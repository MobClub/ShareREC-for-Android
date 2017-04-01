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

		//需要的时候调用，不用每次都调用。发生更改后再调用即可。
//		ShareREC.setWontBeBindPlatforms(SinaWeibo.NAME, Facebook.NAME, Wechat.NAME, WechatMoments.NAME);
//		ShareREC.setShowBindPhone(false);
//		User user = new User();
//		user.setUid("120");
//		user.setNickName("5678");
//		user.setAvatarUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489039964042&di=f4cace1be57ddcd1279b7b8e0a2a1c44&imgtype=0&src=http%3A%2F%2Fi2.itc.cn%2F20160819%2F3739_71d6e13a_08ae_351c_84ed_24055b9d84d2_1.jpg");
//
//		ShareREC.updateUserByApp(user);

	}
	
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	
}
