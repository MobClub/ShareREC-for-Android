using UnityEngine;
using System.Collections;
using System.Runtime.InteropServices;

namespace cn.sharerec {
	public class ShareRECImpl {
	#if UNITY_ANDROID
		private const int RENDER_EVENTID = 9898;

		private static JavaInterface javaInter;
		private static int g_screenfbo = 0;
		private static bool recordGUILayer = false;

		#if (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER)
        private static RenderTexture g_RenderTexture;
		#endif
		
		public static void Init(string appkey, string appSecret, string gameObject, int maxFrameSize) {
			if (javaInter == null) {
				javaInter = new JavaInterface(appkey, appSecret);
				javaInter.setGameObject(gameObject);
				javaInter.setMaxFrameSize(maxFrameSize, "setUnityRenderEvent");
				javaInter.setOnRecorderStateListener("onStateChange");
				javaInter.setOnPlatformSelectedCallback("onPlatformSelected");
				javaInter.setSampleRate(AudioSettings.outputSampleRate);
				int channelCount = AudioSettings.speakerMode == AudioSpeakerMode.Mono ? 1 : 2;
				javaInter.setChannelCount(channelCount > 0 ? channelCount : 2);
				#if (UNITY_4_5 || UNITY_4_6)
				setRenderEventID(RENDER_EVENTID);
				#endif
			} else {
				javaInter.setGameObject(gameObject);
			}
		}

		public static void SetForceSoftwareEncoding(bool video, bool audio) {
			javaInter.setForceSoftwareEncoding(video, audio);
		}

		public static void SetCacheFolder(string path) {
			javaInter.setCacheFolder(path);
		}

		public static void SetMinDuration(int duration) {
			javaInter.setMinDuration(duration);
		}

		public static void InitRenderTexture(){
		#if (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER) 
			if(g_RenderTexture == null){
				g_RenderTexture = RenderTexture.GetTemporary(Screen.width, Screen.height);
			}
        #endif
        }

		public static void ReleaseRenderTexture(){
		#if (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER)
            if (g_RenderTexture != null) {
				RenderTexture.ReleaseTemporary (g_RenderTexture);
			}
        #endif
		}

		public static void AddCameraRecord(RenderTexture src) {
		#if (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER)
			if (src != null) {
				Graphics.SetRenderTarget(g_RenderTexture);
				Graphics.Blit(src, g_RenderTexture);
			}
		#endif
		}

		public static void useGLES30API() {
			javaInter.useGLES30API();
		}

		public static void SetText(string text) {
			javaInter.setText(text);
		}
		
		public static void AddCustomAttr(string key, string value) {
			javaInter.addAttrData(key, value);
		}

		public static void AddCustomPlatform(string name) {
			javaInter.addCustomPlatform(name);
		}

		public static void SetVideoQuality(int videoQuality) {
			javaInter.setVideoQuality(videoQuality);
		}
		
		public static void SetRecordAudioFromMic() {
			javaInter.setRecordAudioFromMic(true);
		}

		public static void SetRecordGUILayer() {
			recordGUILayer = true;
		}

		public static bool IsRecordGUILayer() {
			return recordGUILayer;
		}

		public static bool IsAvailable() {
			return javaInter.isAvailable();
		}

		public static bool CanStart() {
			return javaInter.canStart();
		}

		public static void Start() {
			javaInter.start();
		}

		public static void Pause() {
			javaInter.pause();
		}

		public static void Resume() {
			javaInter.resume();
		}

		public static void Stop() {
			javaInter.stop();
		}

		public static void ShowShare() {
			javaInter.showShare("onReturnGame");
		}

		public static void ShowProfile() {
			javaInter.showProfile("onReturnGame");
		}

		public static void ShowVideoCenter() {
			javaInter.showVideoCenter("onReturnGame");
		}

		public static void ClearCache() {
			javaInter.clearCache();
		}

		public static long[] ListLocalVideos() {
			return javaInter.listLocalVideos();
		}

		public static string GetLocalVideoPath(long videoId) {
			return javaInter.getLocalVideoPath(videoId);
		}

		public static void DeleteLocalVideo(long videoId) {
			javaInter.deleteLocalVideo(videoId);
		}

		public static void PrepareSoundCopying(int channelCount, int sampleRate, int maxBufferSizeInBytes){
			javaInter.PrepareSoundCopying(channelCount, sampleRate,maxBufferSizeInBytes);
		}

		public static void OfferSample(byte[] sample,int offset,int len){
			javaInter.OfferSample(sample,offset,len);
		}
		// =======================================

		public static void OnPreRender() {
		#if (UNITY_4_5 || UNITY_4_6)
			javaInter.onPreRender(1);
			GL.IssuePluginEvent(RENDER_EVENTID);
		#elif (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER)
            javaInter.onPreRender(1);
		#else
			javaInter.onPreRender(0);
		#endif
		}

		public static void OnPostRender() {
		#if (UNITY_4_5 || UNITY_4_6)
			javaInter.onPostRender(getScreenFbo());
		#elif (UNITY_5_0 || UNITY_5_1 || UNITY_5_2 || UNITY_5_3 || UNITY_5_4 || UNITY_5_4_OR_NEWER)
			Graphics.Blit(g_RenderTexture, (RenderTexture) null);
			javaInter.onRenderImage(g_RenderTexture.GetNativeTexturePtr().ToInt32());
		#else
			javaInter.onPostRender(g_screenfbo);
		#endif
		}


		#if (UNITY_4_5 || UNITY_4_6)
		[DllImport("ShareRecUnity")]
		public static extern void setFBOInfo( int screenfbo );
		
		[DllImport("ShareRecUnity")]
		private static extern int getScreenFbo();
		
		[DllImport("ShareRecUnity")]
		public static extern void setRenderEventID( int id );
		#endif
	#endif
	}
}
