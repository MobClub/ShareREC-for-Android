using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;

/// <summary>
/// <para>ShareREC示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareREC, drag this script to MainCamera and run)</para>
/// </summary>
public class RECBarTest : MonoBehaviour {
#if UNITY_ANDROID	
	const int RECBAR_PROFILE = 1;
	const int RECBAR_START = 2;
	const int RECBAR_STOP = 3;
	const int RECBAR_VIDEOCENTER = 4;

	void Start() {
		RECBar recBar = ShareREC.GetRECBar(this);
		if (recBar != null) {
			ShareREC.OnRecorderStoppedHandler = onStopped;
			recBar.ShowRecBar();
		}
	}

	void onRecBarAction(string action) {
		int iAction = -1;
		if (!Int32.TryParse(action, out iAction)) {
			return;
		}
		
		switch (iAction) {
		case RECBAR_PROFILE: {
			// 打开个人中心 (show user profile page)
			ShareREC.ShowProfile(); 
		} break;
		case RECBAR_START: {
			if (ShareREC.IsAvailable()) {
				// 设置启动监听(add recorder started listener)
				ShareREC.OnRecorderStartedHandler = onStarted;
				// 启动录制 (start recording)
				ShareREC.StartRecorder();
			}
		} break;
		case RECBAR_STOP: {
			// 设置停止监听 (add recorder stopped listener)
			ShareREC.OnRecorderStoppedHandler = onStopped;
			// 停止录制 (stop recording)
			ShareREC.StopRecorder();
		} break;
		case RECBAR_VIDEOCENTER: {
			// 添加返回操作(add callback to handle returning operations)
			ShareREC.OnReturnFromVideoCenterHandler = OnReturned;
			// 打开视频中心 (show video center)
			ShareREC.ShowVideoCenter();
		} break;
		}
	}

	void onStarted() {
	}

	void onStopped() {
		// 停止录制后启动分享视频 (show sharing page after the recorder is stopped)
		ShareREC.SetText("Angry Rebot");
		ShareREC.AddCustomAttr("score", "5000");
		ShareREC.AddCustomAttr("name", "ShareREC Developer");
		ShareREC.AddCustomAttr("brand", "hehe!");
		ShareREC.AddCustomAttr("level", "10");
		// 添加返回操作(add callback to handle returning operations)
		ShareREC.OnReturnFromShareHandler = OnReturned;
		ShareREC.ShowShare();
	}

	void OnReturned() {
		Debug.Log("============== I am BACK!!!!");
	}
#endif
}
