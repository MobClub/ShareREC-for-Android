using System;
using UnityEngine;

namespace cn.sharerec {
	public class MP4 {
		#if UNITY_ANDROID
			private AndroidJavaObject mp4;

			public MP4(string appkey, string appSecret, string name) {
				try {
					AndroidJavaClass clz = null;
					clz = new AndroidJavaClass("cn.sharerec.recorder.impl.UnityRecorder");
					AndroidJavaObject javaRecorder = clz.CallStatic<AndroidJavaObject>("getInstance", appkey, appSecret);
					mp4 = javaRecorder.Call<AndroidJavaObject>("getMp4Buffer", name);
				} catch(Exception e) {
					mp4 = null;
				}
			}

			public long getCreateTime() {
				return mp4.Call<long> ("getCreateTime");
			}

			public string getLocalPath() {
				return mp4.Call<string> ("getLocalPath");
			}

			public AndroidJavaObject getThumb(float progress) {
				return mp4.Call<AndroidJavaObject> ("getThumb", progress);
			}

			public long getDuration() {
				return mp4.Call<long> ("getDuration");
			}

			public void remove() {
				mp4.Call ("remove");
			}

			public string getText() {
				return mp4.Call<string> ("getText");
			}

			public AndroidJavaObject getCustomAttrs() {
				return mp4.Call<AndroidJavaObject> ("getCustomAttrs");
			}
		#endif
	}
}