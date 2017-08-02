using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;
using System.Threading;
using System.Text;

/// <summary>
/// <para>ShareREC示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareREC, drag this script to MainCamera and run)</para>
/// </summary>
public class RECClassicTest : MonoBehaviour {
#if UNITY_ANDROID
	private bool started;
	private int fontSize;
	private Rect panel;
	private Rect btnStart;
	private Rect btnStop;
	private Rect btnProfile;
    private Rect btnStopPreview;
    private Rect btnShowLocalVideos;

    float start ;
	void Start() {
		// 初始化控制按钮 (initializing button positions)
		float factor = Screen.width / 1920f;
		fontSize = (int) (40 * factor);
		panel = new Rect(30 * factor, 30 * factor, 310 * factor, 540 * factor);
		btnStart = new Rect (60 * factor, 120 * factor, 250 * factor, 60 * factor);
		btnStop = new Rect (60 * factor, 210 * factor, 250 * factor, 60 * factor);

        btnStopPreview = new Rect(60 * factor, 300 * factor, 250 * factor, 60 * factor);

        btnProfile = new Rect (60 * factor, 390 * factor, 250 * factor, 60 * factor);

        btnShowLocalVideos = new Rect(60 * factor, 480 * factor, 250 * factor, 60 * factor);

    }
    private bool share = false;
	void OnGUI() {
		
        if (ShareREC.IsAvailable ())
		//if (false) 
		{ 
			GUI.skin.box.fontSize = fontSize;
			GUI.skin.button.fontSize = fontSize;
			GUI.Box(panel, "ShareREC Test");

			if (started) {
                if (GUI.Button(btnStop, "Stop Share")) {
                    share = true;
                    // 设置停止监听 (add recorder stopped listener)
                    ShareREC.OnRecorderStoppedHandler = onStopped;
                    // 停止录制 (stop recording)
                    ShareREC.StopRecorder();
                } else if (GUI.Button(btnStopPreview,"Stop Preview")) {
                    share = false;
                    // 设置停止监听 (add recorder stopped listener)
                    ShareREC.OnRecorderStoppedHandler = onStopped;
                    // 停止录制 (stop recording)
                    ShareREC.StopRecorder();
                }
			} else {
                if (GUI.Button(btnStart, "Start")) {
                    // 设置启动监听(add recorder started listener)
                    ShareREC.OnRecorderStartedHandler = onStarted;

                    //===get the audio data real time======
                    //Camera camera = GetComponent<Camera>();      
                    //ShareREC.PrepareSoundCopying (1, 44100, 2048,camera.name);
                    //======================================

                    ShareREC.StartRecorder();

                } else if (GUI.Button(btnProfile, "Profile")) {
                    // 添加返回操作(add callback to handle returning operations)
                    ShareREC.OnReturnFromProfileHandler = OnReturned;
                    ShareREC.OnPlatformSelectedHandler = OnPlatformSelected;
                    ShareREC.AddCustomPlatform("CustomPlatform");
                    // 打开个人中心 (show user profile page)
                    ShareREC.ShowProfile();
                } else if (GUI.Button(btnShowLocalVideos,"LocalVideos")) {

                    ShareREC.ShowLocalVideos();
                }
            }
		}
	}
	/// <summary>
	/// get the audio data real time 
	/// </summary>
	/// <param name="buffer">Buffer.</param>
	public void GetAudioBuffer(String buffer)
	{
		
		//Debug.LogError (" ===================== GetAudioBuffer buffer unity >>>" + buffer);
		sbyte[] bb =  HexByteConvert.HexStringToByteArray(buffer);
		byte[] audiodata = HexByteConvert.convert (bb);

		//do with audiodata something ...

		//Debug.LogError (" ===================== OfferSample GetAudioBuffer bb length >>>" + bb.Length);
		ShareREC.OfferSample (audiodata,0,audiodata.Length);
		//Debug.LogError (" ===================== OfferSample end classictext ");	

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
        if (share)
        {
            ShareREC.ShowShare();
        }
        else {
            ShareREC.ShowLastVideo();
        }
        
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
