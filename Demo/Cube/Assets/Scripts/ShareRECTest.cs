using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;

/// <summary>
/// <para>ShareREC示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareREC, drag this script to MainCamera and run)</para>
/// </summary>
public class ShareRECTest : MonoBehaviour {
#if UNITY_ANDROID
	private bool started;
	private int fontSize;
	private Rect panel;
	private Rect btnStart;
	private Rect btnStop;
	private Rect btnProfile;

	void Start() {
		// 初始化控制按钮 (initializing button positions)
		float factor = Screen.width / 1920f;
		fontSize = (int) (40 * factor);
		panel = new Rect(30 * factor, 30 * factor, 310 * factor, 360 * factor);
		btnStart = new Rect (60 * factor, 120 * factor, 250 * factor, 60 * factor);
		btnStop = new Rect (60 * factor, 210 * factor, 250 * factor, 60 * factor);
		btnProfile = new Rect (60 * factor, 300 * factor, 250 * factor, 60 * factor);
	}

	void OnGUI() {
		if (ShareREC.IsAvailable()) {
			GUI.skin.box.fontSize = fontSize;
			GUI.skin.button.fontSize = fontSize;
			GUI.Box(panel, "ShareREC Test");

			if (started) {
				if (GUI.Button(btnStop, "Stop")) {
					// 设置停止监听 (add recorder stopped listener)
					ShareREC.OnRecorderStoppedHandler = onStopped;
					// 停止录制 (stop recording)
					ShareREC.StopRecorder();
				}
			} else {
				if (GUI.Button(btnStart, "Start")) {
					// 设置启动监听(add recorder started listener)
					ShareREC.OnRecorderStartedHandler = onStarted;
					// 启动录制 (start recording)
					ShareREC.StartRecorder();
				} else if (GUI.Button(btnProfile, "Profile")) {
					// 添加返回操作(add callback to handle returning operations)
					ShareREC.OnReturnFromProfileHandler = OnReturned;
					ShareREC.OnPlatformSelectedHandler = OnPlatformSelected;
					ShareREC.AddCustomPlatform("CustomPlatform");
					// 打开个人中心 (show user profile page)
					ShareREC.ShowProfile();
				}
			}
		}
	}

	void OnApplicationPause(bool pauseStatus) {
		if (pauseStatus) {
			ShareREC.PauseRecorder();
		} else {
			ShareREC.ResumeRecorder();
		}
	}

	void onStarted() {
		started = true;
	}

	void onStopped() {
		started = false;

		// 停止录制后启动分享视频 (show sharing page after the recorder is stopped)
		ShareREC.SetText("Angry Rebot");
		ShareREC.AddCustomAttr("score", "5000");
		ShareREC.AddCustomAttr("name", "ShareREC Developer");
		ShareREC.AddCustomAttr("brand", "hehe!");
		ShareREC.AddCustomAttr("level", "10");
		// 添加返回操作(add callback to handle returning operations)
		ShareREC.OnReturnFromShareHandler = OnReturned;
		ShareREC.OnPlatformSelectedHandler = OnPlatformSelected;
		ShareREC.AddCustomPlatform("CustomPlatform");
		ShareREC.ShowShare();
	}

	void OnReturned() {
		Debug.Log("============== I am BACK!!!!");
	}

	void OnPlatformSelected(string name, MP4 mp4) {
		Debug.Log ("menu \"" + name + "\" is selected");
		Debug.Log ("video path:" + mp4.getLocalPath());
	}
#endif
}
