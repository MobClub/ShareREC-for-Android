using System;
using UnityEngine;
using UnityEngine.Rendering;

namespace cn.sharerec {
	public class JavaInterface {
	#if UNITY_ANDROID
		private AndroidJavaObject javaRecorder;
		private string onSelected;

		public JavaInterface(string appkey, string appSecret) {
			try {
                
                AndroidJavaClass clzUnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
                
                AndroidJavaObject currentActivity = clzUnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
                
                AndroidJavaClass clzMobSDK = new AndroidJavaClass("com.mob.MobSDK");
                
                clzMobSDK.CallStatic("init", currentActivity, appkey, appSecret);
                
                AndroidJavaClass clz = new AndroidJavaClass("cn.sharerec.recorder.impl.UnityRecorder");
                
                javaRecorder = clz.CallStatic<AndroidJavaObject>("getInstance");
                
                setGraphicsDeviceType(SystemInfo.graphicsDeviceType);
                
			} catch(Exception e) {
				javaRecorder = null;
                
            }
		}

		private void setGraphicsDeviceType(GraphicsDeviceType api) {
			if (javaRecorder != null) {
				if (GraphicsDeviceType.OpenGLES2.Equals (api)) {
					javaRecorder.Call("setGraphicsDeviceType", 2);
				} else if (GraphicsDeviceType.OpenGLES3.Equals (api)) {
					javaRecorder.Call("setGraphicsDeviceType", 3);
				} else {
					javaRecorder.Call("setGraphicsDeviceType", -1);
				}
			}
		}

		public void useGLES30API() {
			if (javaRecorder != null) {
				javaRecorder.Call("setUseES3", true);
			}
		}

		public void setGameObject(string gameObject) {
			if (javaRecorder != null) {
				javaRecorder.Call("setGameObject", gameObject);
			}
		}

		public void setOnRecorderStateListener(string callback) {
			if (javaRecorder != null) {
				javaRecorder.Call("setOnRecorderStateListener", callback);
			}
		}
		public void setSMSSDKAppkey(string appkey,string appSecret)
		{
			if (javaRecorder != null) {
				javaRecorder.Call("setSMSSDKAppkey", appkey,appSecret);
			}
		}
		public void setOnPlatformSelectedCallback(string onSelected) {
			this.onSelected = onSelected;
		}

		public void setSampleRate(int sampleRate) {
			if (javaRecorder != null) {
				javaRecorder.Call("setSampleRate", sampleRate);
			}
		}

		public void setChannelCount(int channelCount) {
			if (javaRecorder != null) {
				javaRecorder.Call("setChannelCount", channelCount);
			}
		}

		public void setForceSoftwareEncoding(bool video, bool audio) {
			if (javaRecorder != null && (video || audio)) {
				javaRecorder.Call("setForceSoftwareEncoding", video, audio);
			}
		}

		public void setCacheFolder(string path) {
			if (javaRecorder != null && path != null) {
				javaRecorder.Call("setCacheFolder", path);
			}
		}

		public void setMinDuration(int duration) {
			if (javaRecorder != null) {
				javaRecorder.Call("setMinDuration", duration < 1 ? 4000L : (duration * 1000L));
			}
		}

		public void setRecordAudioFromMic(bool yes) {
			if (javaRecorder != null) {
				javaRecorder.Call("setRecordAudioFromMic", yes);
			}
		}

		public void onTheBeginningOfTheFrame() {
			if (javaRecorder != null) {
				javaRecorder.Call("onTheBeginningOfTheFrame");
			}
		}

		public void onTheEndOfTheFrame() {
			if (javaRecorder != null) {
				javaRecorder.Call("onTheEndOfTheFrame");
			}
		}

		public bool canStart() {
			if (javaRecorder != null) {
				return javaRecorder.Call<bool>("canStart");
			}
			return false;
		}

		public void start() {
			if (javaRecorder != null) {
				javaRecorder.Call("start");
			}
		}

		public void pause() {
			if (javaRecorder != null) {
				javaRecorder.Call("pause");
			}
		}

		public void resume() {
			if (javaRecorder != null) {
				javaRecorder.Call("resume");
			}
		}

		public bool canStop() {
			if (javaRecorder != null) {
				return javaRecorder.Call<bool>("canStop");
			}
			return false;
		}

		public bool stop() {
			if (javaRecorder != null) {
				return javaRecorder.Call<bool>("tryStop");
			}
			return false;
		}

		public void setText(string text) {
			if (javaRecorder != null) {
				javaRecorder.Call("setText", text);
			}
		}

		public void addAttrData(string key, string value) {
			if (javaRecorder != null) {
				javaRecorder.Call("addCustomAttr", key, value);
			}
		}

		public bool isAvailable() {
			if (javaRecorder != null) {
				return javaRecorder.Call<bool>("isAvailable");
			}
			return false;
		}

		public void showShare(string callback) {
			if (javaRecorder != null) {
				javaRecorder.Call("showShare", callback);
			}
		}

		public void showVideoCenter(string callback) {
			if (javaRecorder != null) {
				javaRecorder.Call("showVideoCenter", callback);
			}
		}

		public void showProfile(string callback) {
			if (javaRecorder != null) {
				javaRecorder.Call("showProfile", callback);
			}
		}

		public void clearCache() {
			if (javaRecorder != null) {
				javaRecorder.Call("clearCache");
			}
		}

		public void setVideoQuality(int videoQuality) {
			if (javaRecorder != null) {
				javaRecorder.Call("setVideoQuality", videoQuality);
			}
		}

		public void onPreRender(int isPlugin) {
			if (javaRecorder != null) {
				javaRecorder.Call("onPreRender",isPlugin);
			}
		}
		
		public void onPostRender(int screenfbo) {
			if (javaRecorder != null) {
				javaRecorder.Call("onPostRender",screenfbo);
			}
		}

		public void onRenderImage(int texture) {
			if (javaRecorder != null) {
				javaRecorder.Call("onRenderImage",texture);
			}
		}

		public void setMaxFrameSize(int level, string callback) {
			if (javaRecorder != null) {
				javaRecorder.Call("setMaxFrameSize", level, callback);
			}
		}

		public long[] listLocalVideos() {
			if (javaRecorder != null) {
				return javaRecorder.Call<long[]>("listLocalVideos");
			}
			return new long[0];
		}

		public string getLocalVideoPath(long videoId) {
			if (javaRecorder != null) {
				return javaRecorder.Call<string>("getLocalVideoPath", videoId);
			}
			return null;
		}

		public void deleteLocalVideo(long videoId) {
			if (javaRecorder != null) {
				javaRecorder.Call("deleteLocalVideo", videoId);
			}
		}
		
		public void setUseES3( bool bES3 ){
			if (javaRecorder != null) {
				javaRecorder.Call("setUseES3", bES3);
			}
		}
		public void PrepareSoundCopying(int channelCount, int sampleRate, int maxBufferSizeInBytes){
			if (javaRecorder != null) {
				javaRecorder.Call("prepareSoundCopying", channelCount, sampleRate, maxBufferSizeInBytes);
			}
		}

		public void OfferSample(byte[] sample, int offset, int len){
			if (javaRecorder != null) {
				javaRecorder.Call("offerSample", sample, offset, len);
			}
		}

		public void addCustomPlatform(string name) {
			if (javaRecorder != null) {
				javaRecorder.Call("addCustomPlatform", name, onSelected);
			}
		}
	#else
		public JavaInterface(string appkey, string appSecret) {

		}
	#endif
	}
}
