/****************************************************************************
Copyright (c) 2010-2012 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.cocos2dx.cpp_empty_test;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.content.Intent;
import android.os.Bundle;
import cn.sharerec.ShareREC;
import cn.sharerec.recorder.Recorder.LevelMaxFrameSize;
import cn.sharerec.recorder.impl.Cocos2DRecorder;
import cn.sharerec.recorder.media.User;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
public class AppActivity extends Cocos2dxActivity {
	
	public Intent getIntent() {
		return super.getIntent();
	}
	
	public Cocos2dxGLSurfaceView onCreateView() {
		String appkey = getIntent().getStringExtra("srec_key_appKey");
		String appsecret = getIntent().getStringExtra("srec_key_appSecret");
		String srec_maxFrameSize =  getIntent().getStringExtra("srec_key_maxFrameSize");
		Cocos2DRecorder coco2dRecorder = Cocos2DRecorder.getInstance(appkey, appsecret); 
		
		coco2dRecorder.setMaxFrameSize(LevelMaxFrameSize.valueOf(srec_maxFrameSize));
		
		return Cocos2DRecorder.getCocos2dxGLSurfaceView(appkey, appsecret);
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//需要的时候调用，不用每次都调用。发生更改后再调用即可。
		//================================================
		//指定不显示的分享平台
		ShareREC.setWontBeBindPlatforms(SinaWeibo.NAME, Facebook.NAME, Wechat.NAME);
		ShareREC.setShowBindPhone(true);
		User user = new User();
		//app的UserId 测试数据
		user.setUid("120");
		user.setNickName("ShareRecTest");
		user.setAvatarUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=971424552,4028350881&fm=23&gp=0.jpg");
		ShareREC.updateUserByApp(user);
	}
	
}
