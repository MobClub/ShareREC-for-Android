#include "sharerec_for_cocos2d.h"

USING_NS_CC;

namespace cn {
	namespace sharerec {
		JNIEXPORT void JNICALL Java_cn_sharerec_recorder_impl_Cocos2DRecorder_onStateChange
				(JNIEnv* env, jobject thiz, jint callback, jint state) {
			ShareRec::OnRecorderStateListener listener = (ShareRec::OnRecorderStateListener) callback;
			listener(state);
		}

		JNIEXPORT void JNICALL Java_cn_sharerec_recorder_impl_Cocos2DRecorder_onPlatformSelected
				(JNIEnv* env, jobject thiz, jint callback, jstring name, jobject mp4) {
			ShareRec::OnPlatformSelected customPlatform = (ShareRec::OnPlatformSelected) callback;
			MP4 cMp4(mp4);
			const char* cName = env->GetStringUTFChars(name, JNI_FALSE);
			customPlatform(cName, &cMp4);
			env->ReleaseStringChars(name, (const jchar*) cName);
		}

		// =======================================
		
		MP4::MP4(jobject mp4) {
			this->mp4 = mp4;
		}

		MP4::~MP4 () {
			this->mp4 = NULL;
		}

		long long MP4::getCreateTime() {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "setCacheFolder", "()J");
				if (res) {
					return mi.env->CallLongMethod(mp4, mi.methodID);
				}
			}
			return 0;
		}

		int MP4::getLocalPath(char** path) {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "getLocalPath", "()Ljava/lang/String;");
				if (res) {
					jstring jPath = (jstring) mi.env->CallObjectMethod(mp4, mi.methodID);
					const char* cPath = mi.env->GetStringUTFChars(jPath, JNI_FALSE);
					int len = strlen(cPath);
					char tmp[len];
					strcpy(tmp, cPath);
					*path = tmp;
					mi.env->ReleaseStringChars(jPath, (const jchar*) cPath);
					return len;
				}
			}
			return 0;
		}

		jobject MP4::getThumb(float progress) {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "getThumb", "()Landroid/graphics/Bitmap;");
				if (res) {
					return mi.env->CallObjectMethod(mp4, mi.methodID);
				}
			}
			return NULL;
		}

		long long MP4::getDuration() {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "getDuration", "()J");
				if (res) {
					return mi.env->CallLongMethod(mp4, mi.methodID);
				}
			}
			return 0;
		}

		void MP4::remove() {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "remove", "()V");
				if (res) {
					mi.env->CallVoidMethod(mp4, mi.methodID);
				}
			}
		}

		int MP4::getText(char** text) {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "getText", "()Ljava/lang/String;");
				if (res) {
					jstring jText = (jstring) mi.env->CallObjectMethod(mp4, mi.methodID);
					const char* cText = mi.env->GetStringUTFChars(jText, JNI_FALSE);
					int len = strlen(cText);
					char tmp[len];
					strcpy(tmp, cText);
					*text = tmp;
					mi.env->ReleaseStringChars(jText, (const jchar*) cText);
					return 1;
				}
			}
			return 0;
		}

		jobject MP4::getCustomAttrs() {
			if (mp4 != NULL) {
				JniMethodInfo mi;
				const char* className = "cn/sharerec/recorder/media/MP4";
				bool res = JniHelper::getMethodInfo(mi, className, "getCustomAttrs", "()Ljava/util/HashMap;");
				if (res) {
					return mi.env->CallObjectMethod(mp4, mi.methodID);
				}
			}
			return NULL;
		}
		
		// =======================================
		
		jobject ShareRec::getRecorder() {
			JniMethodInfo miGetInstance;
			const char* className = "cn/sharerec/recorder/impl/Cocos2DRecorder";
			const char* sig = "()Lcn/sharerec/recorder/impl/Cocos2DRecorder;";
			bool res = JniHelper::getStaticMethodInfo(miGetInstance, className, "getInstance", sig);
			if (res) {
				jobject javaRecorder = miGetInstance.env->CallStaticObjectMethod(miGetInstance.classID, miGetInstance.methodID);
				// miGetInstance.env->DeleteGlobalRef(miGetInstance.classID);
				return javaRecorder;
			}
			return NULL;
		}
		
		bool ShareRec::getMethod(JniMethodInfo& mi, const char* name, const char* sig) {
			const char* className = "cn/sharerec/recorder/impl/Cocos2DRecorder";
			return JniHelper::getMethodInfo(mi, className, name, sig);
		}

		void ShareRec::releaseMethod(JniMethodInfo& mi) {
			// mi.env->DeleteGlobalRef(mi.classID);
		}

		bool ShareRec::isAvailable() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo miIsAvailable;
				bool res = getMethod(miIsAvailable, "isAvailable", "()Z");
				if (res) {
					jboolean jAvailable = miIsAvailable.env->CallBooleanMethod(javaRecorder, miIsAvailable.methodID);
					releaseMethod(miIsAvailable);
					return (JNI_TRUE == jAvailable);
				}
			}
			return false;
		}
		
		void ShareRec::startRecorder() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "start", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::pauseRecorder() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "pause", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::resumeRecorder() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "resume", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::stopRecorder() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "stop", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::showVideoCenter() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "showVideoCenter", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::showProfile() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "showProfile", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::setText(const char* text) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setText", "(Ljava/lang/String;)V");
				if (res) {
					jstring jText = mi.env->NewStringUTF(text);
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, jText);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::addCustomAttr(const char* key, const char* value) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "addCustomAttr", "(Ljava/lang/String;Ljava/lang/String;)V");
				if (!res) {
					res = getMethod(mi, "addAttrData", "(Ljava/lang/String;Ljava/lang/String;)V");
				}
				if (res) {
					jstring jKey = mi.env->NewStringUTF(key);
					jstring jValue = mi.env->NewStringUTF(value);
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, jKey, jValue);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::showShare() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "showShare", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::clearCache() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "clearCache", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::setOnRecorderStateListener(OnRecorderStateListener listener) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setOnRecorderStateListener", "(I)V");
				if (res) {
					// retain(listener);
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, (jint) listener);
					releaseMethod(mi);
				}
			}
		}
		
		void ShareRec::setMaxFrameSize(ShareRec::LevelMaxFrameSize level) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setMaxFrameSize", "(I)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, (jint)level);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::listLocalVideos(jlong** list, int* len) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "listLocalVideos", "()[J");
				if (res) {
					jlongArray ids = (jlongArray) mi.env->CallObjectMethod(javaRecorder, mi.methodID);
					releaseMethod(mi);
				
					len[0] = mi.env->GetArrayLength(ids);
					jlong* clist = mi.env->GetLongArrayElements(ids, JNI_FALSE);
					jlong* tmp = (jlong*) malloc(sizeof(jlong) * len[0]);
					for (int i = 0; i < len[0]; i++) {
						tmp[i] = clist[i];
					}
					mi.env->ReleaseLongArrayElements(ids, clist, JNI_FALSE);
					list[0] = tmp;
				}
			}
		}
		
		bool ShareRec::getLocalVideoPath(jlong videoId, char** path){
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "getLocalVideoPath", "(J)Ljava/lang/String;");
				if (res) {
					jstring jpath = (jstring) mi.env->CallObjectMethod(javaRecorder, mi.methodID, videoId);
					releaseMethod(mi);
					
					if (jpath != NULL) {
						const char* cpath = mi.env->GetStringUTFChars(jpath, JNI_FALSE);
						int len = mi.env->GetStringUTFLength(jpath);
						char* tmp = (char*) malloc(sizeof(char) * len);
						for (int i = 0; i < len; i++) {
							tmp[i] = cpath[i];
						}
						path[0] = tmp;
						mi.env->ReleaseStringUTFChars(jpath, cpath);
						return true;
					}
				}
			}

			return false;
		}

		void ShareRec::deleteLocalVideo(jlong videoId) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "deleteLocalVideo", "(J)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, videoId);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::setUseES3(jboolean bES3) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setUseES3", "(Z)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, bES3);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::setRecordAudio( jboolean isRecordAudio ) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setRecordAudio", "(Z)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, isRecordAudio);
					releaseMethod(mi);
				}
			}
		}
	
		void ShareRec::setMinDuration( jlong duration ) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setMinDuration", "(J)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, duration);
					releaseMethod(mi);
				}
			}
		}
		
		void ShareRec::setForceSoftwareEncoding(bool video, bool audio) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setForceSoftwareEncoding", "(ZZ)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, video, audio);
					releaseMethod(mi);
				}
			}
		}
		
		void ShareRec::setVideoQuality(ShareRec::LevelVideoQuality quality) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setVideoQuality", "(I)V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, (jint)quality);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::setCacheFolder(const char* path) {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setCacheFolder", "(Ljava/lang/String;)V");
				if (res) {
					jstring jpath = mi.env->NewStringUTF(path);
					mi.env->CallVoidMethod(javaRecorder, mi.methodID, jpath);
					mi.env->DeleteLocalRef(jpath);
					releaseMethod(mi);
				}
			}
		}

		void ShareRec::addCustomPlatform(const char* name, OnPlatformSelected customPlatform) {
			const char* className = "cn/sharerec/recorder/impl/Cocos2DRecorder";
			const char* sig = "(Ljava/lang/String;I)V";
			JniMethodInfo mi;
			bool res = JniHelper::getStaticMethodInfo(mi, className, "addCustomPlatform", sig);
			if (res) {
				jstring jName = mi.env->NewStringUTF(name);
				mi.env->CallStaticVoidMethod(mi.classID, mi.methodID, jName, (jint) customPlatform);
				mi.env->DeleteLocalRef(jName);
			}
		}
	}
}

