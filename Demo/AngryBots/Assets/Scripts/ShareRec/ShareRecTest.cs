using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;

/// <summary>
/// <para>ShareRec示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareRec, drag this script to MainCamera and run)</para>
/// </summary>
public class ShareRecTest : MonoBehaviour {
	bool started;
	int fontSize;
	Rect panel;
	Rect btnStart;
	Rect btnStop;
	Rect btnProfile;

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
		if (ShareRec.IsAvailable()) {
			GUI.skin.box.fontSize = fontSize;
			GUI.skin.button.fontSize = fontSize;
			GUI.Box(panel, "ShareRec Test");

			if (started) {
				if (GUI.Button(btnStop, "Stop")) {
					// 设置停止监听 (add recorder stopped listener)
					ShareRec.OnRecorderStoppedHandler = ShowShare;
					// 启动录制 (start recording)
					started = false;
					ShareRec.StopRecorder();
				}
			} else {
				if (GUI.Button(btnStart, "Start")) {
					// 提高视频质量到4Mbit/s(improve the video quality to 4Mbit/s)
					ShareRec.SetBitRate(4 * 1024 * 1024);
					// 停止录制 (stop recording)
					started = true;
					ShareRec.StartRecorder();
				} else if (GUI.Button(btnProfile, "Profile")) {
					// 打开个人中心 (show user profile page)
					ShareRec.ShowProfile();
				}
			}
		}
	}

	void ShowShare() {
		// 停止录制后启动分享视频 (show sharing page after the recorder is stopped)
		ShareRec.SetText("Angry Rebot");
		ShareRec.AddCustomAttr("score", "5000");
		ShareRec.AddCustomAttr("name", "ShareRec Developer");
		ShareRec.AddCustomAttr("brand", "hehe!");
		ShareRec.AddCustomAttr("level", "10");
		ShareRec.ShowShare();
	}

}
