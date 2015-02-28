#include "sharerec_for_cocos2d.h"
USING_NS_CC;

namespace cn {
	namespace sharerec {
		
		JNIEXPORT void JNICALL Java_cn_sharerec_recorder_Cocos2DRecorder_onStateChange
				(JNIEnv* env, jobject thiz, jint callback, jint state) {
			ShareRec::OnRecorderStateListener listener = (ShareRec::OnRecorderStateListener) callback;
			listener(state);
		}

		jobject ShareRec::getRecorder() {
			JniMethodInfo miGetInstance;
			const char* className = "cn/sharerec/recorder/Cocos2DRecorder";
			const char* sig = "(Ljava/lang/String;)Lcn/sharerec/recorder/Cocos2DRecorder;";
			bool res = JniHelper::getStaticMethodInfo(miGetInstance, className, "getInstance", sig);
			if (res) {
				jobject javaRecorder = miGetInstance.env->CallStaticObjectMethod(miGetInstance.classID, miGetInstance.methodID, NULL);
				// miGetInstance.env->DeleteGlobalRef(miGetInstance.classID);
				return javaRecorder;
			}
			return NULL;
		}
		
		bool ShareRec::getMethod(JniMethodInfo& mi, const char* name, const char* sig) {
			const char* className = "cn/sharerec/recorder/Cocos2DRecorder";
			return JniHelper::getMethodInfo(mi, className, name, sig);
		}

		void ShareRec::releaseMethod(JniMethodInfo& mi) {
			// mi.env->DeleteGlobalRef(mi.classID);
		}
		
		void ShareRec::setDesignResolution() {
			jobject javaRecorder = getRecorder();
			if (javaRecorder != NULL) {
				JniMethodInfo miSetWinScale;
				bool res = getMethod(miSetWinScale, "setWinScale", "(FF)V");
				if (res) {
					#if COCOS2D_VERSION >= 0x00030000
						float scaleX = Director::getInstance()->getOpenGLView()->getScaleX();
						float scaleY = Director::getInstance()->getOpenGLView()->getScaleY();
					#else 
						float scaleX = CCEGLView::sharedOpenGLView()->getScaleX();
						float scaleY = CCEGLView::sharedOpenGLView()->getScaleY();
					#endif
					miSetWinScale.env->CallVoidMethod(javaRecorder, miSetWinScale.methodID, scaleX, scaleY);
					releaseMethod(miSetWinScale);
				}
				
				JniMethodInfo miSetWinSize;
				res = getMethod(miSetWinSize, "setWinSize", "(II)V");
				if (res) {
					#if COCOS2D_VERSION >= 0x00030000
						int width = (int) (Director::getInstance()->getWinSize()).width;
						int height = (int) (Director::getInstance()->getWinSize()).height;
					#else 
						int width = (int) (CCDirector::sharedDirector()->getWinSize()).width;
						int height = (int) (CCDirector::sharedDirector()->getWinSize()).height;
					#endif
					miSetWinSize.env->CallVoidMethod(javaRecorder, miSetWinSize.methodID, width, height);
					releaseMethod(miSetWinSize);
				}
				
				JniMethodInfo miSetFrameRate;
				res = getMethod(miSetFrameRate, "setFrameRate", "(I)V");
				if (res) {
					miSetFrameRate.env->CallVoidMethod(javaRecorder, miSetFrameRate.methodID, 25);
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
	}
}

