using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;

/// <summary>
/// <para>ShareREC示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareREC, drag this script to MainCamera and run)</para>
/// </summary>
public class RECBarTest : MonoBehaviour {
	
	void Start() {
		RECBar recBar = ShareREC.GetRECBar(this);
		if (recBar != null) {
			ShareREC.OnRecorderStoppedHandler = onStopped;
			recBar.ShowRecBar();
		}
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

}
