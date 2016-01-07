#include "sharerec_for_cocos2d.h"

USING_NS_CC;

namespace cn {
	namespace sharerec {
		JNIEXPORT void JNICALL Java_cn_sharerec_recorder_impl_Cocos2DRecorder_onStateChange
				(JNIEnv* env, jobject thiz, jint callback, jint state) {
			ShareRec::OnRecorderStateListener listener = (ShareRec::OnRecorderStateListener) callback;
			listener(state);
		}
		
		jobject ShareRec::getRecorder() {
			JniMethodInfo miGetInstance;
			const char* className = "cn/sharerec/recorder/impl/Cocos2DRecorder";
			const char* sig = "(Ljava/lang/String;Ljava/lang/String;)Lcn/sharerec/recorder/impl/Cocos2DRecorder;";
			bool res = JniHelper::getStaticMethodInfo(miGetInstance, className, "getInstance", sig);
			if (res) {
				jobject javaRecorder = miGetInstance.env->CallStaticObjectMethod(miGetInstance.classID, miGetInstance.methodID, NULL, NULL);
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
		
		void ShareRec::setDesignResolution() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo miSetFrameRate;
				bool res = getMethod(miSetFrameRate, "setFrameRate", "(I)V");
				if (res) {
					miSetFrameRate.env->CallVoidMethod(javaRecorder, miSetFrameRate.methodID, 30);
					releaseMethod(miSetFrameRate);
				}
			}
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
				bool res = getMethod(mi, "addAttrData", "(Ljava/lang/String;Ljava/lang/String;)V");
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

		void ShareRec::setDebuggable() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo mi;
				bool res = getMethod(mi, "setDebuggable", "()V");
				if (res) {
					mi.env->CallVoidMethod(javaRecorder, mi.methodID);
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
	
	}
}

