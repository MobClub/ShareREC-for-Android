#ifndef _Included_cn_sharerec_sharerec_for_cocos2d_h
#define _Included_cn_sharerec_sharerec_for_cocos2d_h

#include <jni.h>
#include "platform/android/jni/JniHelper.h"
#include "cocos2d.h"
USING_NS_CC;

#if 1
#define  LOG_TAG    "sharerec"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#else
#define  LOGD(...) 
#endif

namespace cn {
	namespace sharerec {

		#define STATE_IDLE 0
		#define STATE_STARTING 1
		#define STATE_STARTED 2
		#define STATE_PAUSING 3
		#define STATE_PAUSED 4
		#define STATE_RESUMING 5
		#define STATE_RESUMED STATE_STARTED
		#define STATE_STOPING 7
		#define STATE_STOPPED STATE_IDLE
		
		#ifdef __cplusplus
		extern "C" {	
		#endif

		JNIEXPORT void JNICALL Java_cn_sharerec_recorder_impl_Cocos2DRecorder_onStateChange
				(JNIEnv* env, jobject thiz, jint listener, jint state);

		#ifdef __cplusplus
		}
		#endif

		class ShareRec {
		public:
			
			enum LevelMaxFrameSize {
				LEVEL_480_360 = 0,
				LEVEL_1280_720 = 1,
				LEVEL_1920_1080 = 2
			};
			
			enum LevelVideoQuality {
				LEVEL_SUPER_LOW = 0,
				LEVEL_VERY_LOW = 1,
				LEVEL_LOW = 2,
				LEVEL_MEDIUN = 3,
				LEVEL_HIGH = 4,
				LEVEL_VERY_HIGH = 5,
				LEVEL_SUPER_HIGH = 6
			};
			
			typedef void(*OnRecorderStateListener) (int state);
		
			/**
			  * 判断ShareRec是否支持当前的设备(Determines whether ShareRec is available for the current device.)
			  */
			static bool isAvailable();
			
			/**
			  * 启动录制模块 (Start the recorder module)
			  */
			static void startRecorder();
			
			/**
			  * 暂停录制模块 (Pause the recorder module)
			  */
			static void pauseRecorder();
			
			/**
			  * 恢复录制模块 (Resume the recorder module)
			  */
			static void resumeRecorder();
		
			/**
			  * 停止录制模块 (Stop the recorder module)
			  */
			static void stopRecorder();
		
			/**
			  * 打开视频中心(Shows the video center.)
			  */
			static void showVideoCenter();
			
			/**
			  * 显示用户资料(Shows the user profile.)
			  */
			static void showProfile();
		
			/**
			  * 设置视频描述(Sets the description of the video.)
			  */
			static void setText(const char* text);
		
			/**
			  * 添加视频的自定义属性(Adds the custom attributes of the video.)
			  */
			static void addCustomAttr(const char* key, const char* value);
		
			/**
			  * 显示分享页面(Shows the share page.)
			  */
			static void showShare();
		
			/**
			  * 清除ShareRec的缓存目录(Clears the cache folder of ShareRec.)
			  */
			static void clearCache();

			/**
			  * 设置状态回调 (Add a recording state listener)
			  */
			static void setOnRecorderStateListener(OnRecorderStateListener listener);

			/**
			  * 显示异常日志(Show exception loggs)
			  */
			static void setDebuggable();
			
			/**
			  * 设置最大录屏分辨率（0--（480*360）1--（1280*720）2--（1920*1080）
			  */
			static void setMaxFrameSize(LevelMaxFrameSize level);

			/**
			  * 列出本地已经缓存的视频(Lists the local videos.)
			  */
			static void listLocalVideos(jlong** list, int* len);

			/**
			  * 通过缓存的视频ID获取本地路径(Gets the local video path by its ID.)
			  */
			static bool getLocalVideoPath(jlong videoId, char** path);

			/**
			  * 删除缓存视频(Deletes the local video by its ID.)
			  */
			static void deleteLocalVideo(jlong videoId);

			/**
			  * 设置是否使用opengl3.0 的方式录屏(Forces to use GLES 3.0 features.)
			  */
			static void setUseES3(jboolean bES3);
			
			/**
			  * 设置是否需要录制声音 (Sets to recording audio or not.)
			  */
			static void setRecordAudio(jboolean isRecordAudio);
			
			/**
			  * 设置需要录制的最短时间(Sets the minimum duration of the video.)
			  */
			static void setMinDuration(jlong duration);
			
			/**
			  * 设置强制采用音视频软件编码器(Forces to use software video/audio encoder.)
			  */
			static void setForceSoftwareEncoding(bool video, bool audio);
			
			/**
			  * 设置视频画质(Sets the video quality)
			  */
			static void setVideoQuality(LevelVideoQuality quality);

			/**
			  * 设置录制完毕后视频的缓存目录(Sets cache folder of output video)
			  */
			static void setCacheFolder(const char* path);
		private:

			static jobject getRecorder();
			
			static bool getMethod(JniMethodInfo& mi, const char* name, const char* sig);

			static void releaseMethod(JniMethodInfo& mi);

		};
	}
}

#endif

