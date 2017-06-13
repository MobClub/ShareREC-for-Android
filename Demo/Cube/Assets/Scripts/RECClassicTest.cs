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
	float start ;
	void Start() {
		// 初始化控制按钮 (initializing button positions)
		float factor = Screen.width / 1920f;
		fontSize = (int) (40 * factor);
		panel = new Rect(30 * factor, 30 * factor, 310 * factor, 360 * factor);
		btnStart = new Rect (60 * factor, 120 * factor, 250 * factor, 60 * factor);
		btnStop = new Rect (60 * factor, 210 * factor, 250 * factor, 60 * factor);
		btnProfile = new Rect (60 * factor, 300 * factor, 250 * factor, 60 * factor);
		//清除缓存区里video
		long[] listVideo = ShareREC.ListLocalVideos ();
		foreach(long vid in listVideo){
			ShareREC.DeleteLocalVideo (vid);
		}
		start = Time.time;

		//执行Rest操作
		bool recorder = false;
		end = false;
		end_now =false;
		recorderstart = 0;
	}
	private bool recorder = false;
	private bool end = false;//false
	private bool end_now =false;
	private float recorderstart = 0;
	private float RECORDER_TIME = 3;
	private bool check =false;
	void OnGUI() {

		if(!recorder&&!end){
			float dif = Time.time - start;

			if(dif>5&&!recorder){

				recorder = true;
				//ShareREC.StartRecorder();

				recorderstart = Time.time;

			}	
		}



	//	Debug.LogError (" ========= 1 recorder >> "+recorder+" end >> "+end);
		//开始录 3秒 
		if(recorder&&!end){
			float dif =  Time.time - recorderstart;
		
			if(dif>RECORDER_TIME){
				
				if(!end_now){
					end_now = true;
					//ShareREC.StopRecorder ();	
				}
			}
		}
		//录3秒，再等5秒,等视频合并完成后再对视频进行分析
		if(end_now==true&&!check){
			float dif =  Time.time - recorderstart;
			if(dif>(RECORDER_TIME+5)){
				check = true;
				long[] listVideo = ShareREC.ListLocalVideos ();

				if (listVideo == null) {
			//		Debug.LogError (" ========= path 33 listVideo :: null ");	
				} else {
			//		Debug.LogError (" ========= path 33 length :: "+listVideo.Length);	
				}

				if (listVideo.Length > 0) {
					end = true;
					recorder = false;

					string path = ShareREC.GetLocalVideoPath (listVideo [0]);
			//		Debug.LogError (" ========= path >> " + path);
					//ShareREC.videoFileToBase64 (path);
					//处理path的
			//		Debug.LogError (" ========= app quit 11 ");	
					//Application.Quit ();
			//		Debug.LogError (" ========= app quit 22 ");	
				} else {
				//	ShareREC.videoFileToBase64 ("/sdcard/file/");
					//处理path的
			//		Debug.LogError (" ========= app quit 11 ");	
				//	Application.Quit ();
				}
			}

		}
		Debug.LogError (" ========= 2 recorder >> "+recorder+" end >> "+end);
        Debug.LogError(" ========= 3 ShareREC.IsAvailable () >> " + ShareREC.IsAvailable());
        if (ShareREC.IsAvailable ())
		//if (false) 
		{ 
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
