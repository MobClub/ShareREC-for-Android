using UnityEngine;
using System;
using System.Collections;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class ShareRec : MonoBehaviour {
		private const int STATE_IDLE = 0;
		private const int STATE_STARTING = 1;
		private const int STATE_STARTED = 2;
		private const int STATE_PAUSING = 3;
		private const int STATE_PAUSED = 4;
		private const int STATE_RESUMING = 5;
		private const int STATE_RESUMED = STATE_STARTED;
		private const int STATE_STOPPING = 7;
		private const int STATE_STOPPED = STATE_IDLE;

		private static ShareRec instance;
		public string AppKey = "76684bc49b3";
		private JavaInterface javaInter;
		
		private int curAction;
		public static OnRecorderStarting OnRecorderStartingHandler;
		public static OnRecorderStarted OnRecorderStartedHandler;
		public static OnRecorderPausing OnRecorderPausingHandler;
		public static OnRecorderPaused OnRecorderPausedHandler;
		public static OnRecorderResuming OnRecorderResumingHandler;
		public static OnRecorderResumed OnRecorderResumedHandler;
		public static OnRecorderStopping OnRecorderStoppingHandler;
		public static OnRecorderStopped OnRecorderStoppedHandler;

		void Awake() {
			if (instance != null) {
				Destroy(gameObject);
				return;
			}

			DontDestroyOnLoad(this);
			instance = this;

			javaInter = new JavaInterface(AppKey);
			javaInter.setOnRecorderStateListener(gameObject.name, "onStateChange");
			javaInter.setSampleRate(AudioSettings.outputSampleRate);
			int channelCount = AudioSettings.speakerMode == AudioSpeakerMode.Mono ? 1 : 2;
			javaInter.setChannelCount(channelCount > 0 ? channelCount : 2);
			javaInter.setFrameRate(25);
			javaInter.setFrameSize(Screen.width, Screen.height);
		}

		// =======================================

		/// <summary>
		/// 判断ShareRec是否支持当前的设备(Determines whether ShareRec is available for the current device.)
		/// </summary>
		public static bool IsAvailable() {
			return instance.javaInter.isAvailable();
		}

		/// <summary>
		/// 启动录制模块 (Start the recorder module)
		/// </summary>
		public static void StartRecorder() {
			instance.javaInter.start();
		}

		/// <summary>
		/// 暂停录制模块(Pauses the recorder module)
		/// </summary>
		public static void PauseRecorder() {
			instance.javaInter.pause();
		}

		/// <summary>
		/// 恢复录制(Resumes the recorder module)
		/// </summary>
		public static void ResumeRecorder() {
			instance.javaInter.resume();
		}

		/// <summary>
		/// 停止录制模块 (Stop the recorder module)
		/// </summary>
		public static void StopRecorder() {
			instance.javaInter.stop();
		}

		/// <summary>
		/// 打开视频中心(Shows the video center.)
		/// </summary>
		public static void ShowVideoCenter() {
			instance.javaInter.showVideoCenter();
		}

		/// <summary>
		/// 显示用户资料(Shows the user profile.)
		/// </summary>
		public static void ShowProfile() {
			instance.javaInter.showProfile();
		}

		/// <summary>
		/// 设置视频描述文本(Sets the description of the video.)
		/// </summary>
		public static void SetText(string text) {
			instance.javaInter.setText(text);
		}

		/// <summary>
		/// 添加视频的自定义属性(Adds the custom attributes of the video.)
		/// </summary>
		public static void AddCustomAttr(string key, string value) {
			instance.javaInter.addAttrData(key, value);
		}

		/// <summary>
		/// 显示分享页面(Shows the share page.)
		/// </summary>
		public static void ShowShare() {
			instance.javaInter.showShare();
		}

		/// <summary>
		/// 清除ShareRec的缓存目录(Clears the cache folder of ShareRec.)
		/// </summary>
		public static void ClearCache() {
			instance.javaInter.clearCache();
		}

		/// <summary>
		/// 设置视频的码率(Sets the bit rate of the video.)
		/// </summary>
		/// <param name="bitRate">Bit rate.</param>
		public static void SetBitRate(int bitRate) {
			instance.javaInter.setBitRate(bitRate);
		}

		// =======================================

		private void onStateChange(string action) {
			int iAction = -1;
			if (!Int32.TryParse(action, out iAction)) {
				return;
			}

			switch (iAction) {
				case STATE_STARTING: {
					if (OnRecorderStartingHandler != null) {
						OnRecorderStartingHandler();
					}
				} break;
				case STATE_STARTED: {
					if (curAction == STATE_RESUMING) {
						if (OnRecorderResumedHandler != null) {
							OnRecorderResumedHandler();
						}
					} else if (OnRecorderStartedHandler != null) {
						OnRecorderStartedHandler();
					}
				} break;
				case STATE_PAUSING: {
					if (OnRecorderPausingHandler != null) {
						OnRecorderPausingHandler();
					}
				} break;
				case STATE_PAUSED: {
					if (OnRecorderPausedHandler != null) {
						OnRecorderPausedHandler();
					}
				} break;
				case STATE_RESUMING: {
					if (OnRecorderResumingHandler != null) {
						OnRecorderResumingHandler();
					}
				} break;
				case STATE_STOPPING: {
					if (OnRecorderStoppingHandler != null) {
						OnRecorderStoppingHandler();
					}
				} break;
				case STATE_STOPPED: {
					if (OnRecorderStoppedHandler != null) {
						OnRecorderStoppedHandler();
					}
				} break;
			}

			curAction = iAction;
		}

		/// <summary>
		/// 此方法在录制模块启动时被调用(This method will be called when the recorder module is starting.)
		/// </summary>
		public delegate void OnRecorderStarting();

		/// <summary>
		/// 此方法在录制模块启动后被调用(This method will be called when the recorder module is started.)
		/// </summary>
		public delegate void OnRecorderStarted();

		/// <summary>
		/// 此方法在录制模块暂停时被调用(This method will be called when the recorder module is pausing.)
		/// </summary>
		public delegate void OnRecorderPausing();

		/// <summary>
		/// 此方法在录制模块暂停后被调用(This method will be called when the recorder module is paused.)
		/// </summary>
		public delegate void OnRecorderPaused();

		/// <summary>
		/// 此方法在录制模块恢复时被调用(This method will be called when the recorder module is resuming.)
		/// </summary>
		public delegate void OnRecorderResuming();

		/// <summary>
		/// 此方法在录制模块恢复后被调用(This method will be called when the recorder module is resumed.)
		/// </summary>
		public delegate void OnRecorderResumed();

		/// <summary>
		/// 此方法在录制模块停止时被调用(This method will be called when the recorder module is stopping.)
		/// </summary>
		public delegate void OnRecorderStopping();

		/// <summary>
		/// 此方法在录制模块停止后被调用(This method will be called when the recorder module is stopped.)
		/// </summary>
		public delegate void OnRecorderStopped();
		
	}
	
#endif
}
