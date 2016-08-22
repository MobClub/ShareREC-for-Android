#include "AppDelegate.h"

#include <vector>
#include <string>

#include "HelloWorldScene.h"
#include "AppMacros.h"
#include "ShareRec/Android/sharerec_for_cocos2d.h"

USING_NS_CC;
using namespace std;
using namespace cn::sharerec;

AppDelegate::AppDelegate() {

}

AppDelegate::~AppDelegate() 
{
}

void AppDelegate::initGLContextAttrs()
{
    GLContextAttrs glContextAttrs = {8, 8, 8, 8, 24, 8};

    GLView::setGLContextAttrs(glContextAttrs);
}

jstring getConfig(const char* key, JNIEnv** env) {
	JniMethodInfo getContext;
	JniHelper::getStaticMethodInfo(getContext, "org/cocos2dx/lib/Cocos2dxActivity", "getContext", "()Landroid/content/Context;");
	jobject context = getContext.env->CallStaticObjectMethod(getContext.classID, getContext.methodID);

	JniMethodInfo getIntent;
	JniHelper::getMethodInfo(getIntent, "android/app/Activity", "getIntent", "()Landroid/content/Intent;");
	jobject intent = getIntent.env->CallObjectMethod(context, getIntent.methodID);

	JniMethodInfo getStringExtra;
	JniHelper::getMethodInfo(getStringExtra, "android/content/Intent", "getStringExtra", "(Ljava/lang/String;)Ljava/lang/String;");
	jstring jkey = getStringExtra.env->NewStringUTF(key);
	jstring jvalue = (jstring) getStringExtra.env->CallObjectMethod(intent, getStringExtra.methodID, jkey);
	getStringExtra.env->DeleteLocalRef(jkey);

	getIntent.env->DeleteLocalRef(intent);
	getContext.env->DeleteLocalRef(context);

	*env = getStringExtra.env;
	return jvalue;
}

bool getBoolConfig(const char* key) {
	JNIEnv* env;
	jstring jvalue = getConfig(key, &env);
	const char* cValue = (const char*) env->GetStringUTFChars(jvalue, JNI_FALSE);
	
	bool value = (strcmp("true", cValue) == 0);
	env->ReleaseStringChars(jvalue, (const jchar*) cValue);
	env->DeleteLocalRef(jvalue);

	return value;
}

ShareRec::LevelMaxFrameSize getMaxFrameSize(const char* key) {
	JNIEnv* env;
	jstring jvalue = getConfig(key, &env);
	const char* cValue = (const char*) env->GetStringUTFChars(jvalue, JNI_FALSE);

	ShareRec::LevelMaxFrameSize value = ShareRec::LEVEL_480_360;
	if (strcmp("LEVEL_1280_720", cValue) == 0) {
		value = ShareRec::LEVEL_1280_720;
	} else if (strcmp("LEVEL_1920_1080", cValue) == 0) {
		value = ShareRec::LEVEL_1920_1080;
	}
	
	env->ReleaseStringChars(jvalue, (const jchar*) cValue);
	env->DeleteLocalRef(jvalue);
	return value;
}

ShareRec::LevelVideoQuality getVideoQuality(const char* key) {
	JNIEnv* env;
	jstring jvalue = getConfig(key, &env);
	const char* cValue = (const char*) env->GetStringUTFChars(jvalue, JNI_FALSE);

	ShareRec::LevelVideoQuality value = ShareRec::LEVEL_LOW;
	if (strcmp("LEVEL_MEDIUN", cValue) == 0) {
		value = ShareRec::LEVEL_MEDIUN;
	} else if (strcmp("LEVEL_HIGH", cValue) == 0) {
		value = ShareRec::LEVEL_HIGH;
	}

	env->ReleaseStringChars(jvalue, (const jchar*) cValue);
	env->DeleteLocalRef(jvalue);
	return value;
}

long getLongConfig(const char* key) {
	JNIEnv* env;
	jstring jvalue = getConfig(key, &env);

	JniMethodInfo parseLong;
	JniHelper::getStaticMethodInfo(parseLong, "java/lang/Long", "parseLong", "(Ljava/lang/String;)J");
	long value = parseLong.env->CallStaticLongMethod(parseLong.classID, parseLong.methodID, jvalue);

	env->DeleteLocalRef(jvalue);
	return value;
}

const char* getStringConfig(const char* key) {
	JNIEnv* env;
	jstring jvalue = getConfig("srec_key_cacheFolder", &env);
	
	const char* value = (const char*) env->GetStringUTFChars(jvalue, JNI_FALSE);
	ShareRec::setCacheFolder(value);

	env->ReleaseStringChars(jvalue, (const jchar*) value);
	env->DeleteLocalRef(jvalue);
	return value;
}

bool AppDelegate::applicationDidFinishLaunching() {
    // initialize director
    auto director = Director::getInstance();
    auto glview = director->getOpenGLView();
    if(!glview) {
        glview = GLViewImpl::create("Cpp Empty Test");
        director->setOpenGLView(glview);
    }

    director->setOpenGLView(glview);

    // Set the design resolution
    glview->setDesignResolutionSize(designResolutionSize.width, designResolutionSize.height, ResolutionPolicy::NO_BORDER);

	Size frameSize = glview->getFrameSize();
    
    vector<string> searchPath;

    // In this demo, we select resource according to the frame's height.
    // If the resource size is different from design resolution size, you need to set contentScaleFactor.
    // We use the ratio of resource's height to the height of design resolution,
    // this can make sure that the resource's height could fit for the height of design resolution.

    // if the frame's height is larger than the height of medium resource size, select large resource.
	if (frameSize.height > mediumResource.size.height)
	{
        searchPath.push_back(largeResource.directory);

        director->setContentScaleFactor(MIN(largeResource.size.height/designResolutionSize.height, largeResource.size.width/designResolutionSize.width));
	}
    // if the frame's height is larger than the height of small resource size, select medium resource.
    else if (frameSize.height > smallResource.size.height)
    {
        searchPath.push_back(mediumResource.directory);
        
        director->setContentScaleFactor(MIN(mediumResource.size.height/designResolutionSize.height, mediumResource.size.width/designResolutionSize.width));
    }
    // if the frame's height is smaller than the height of medium resource size, select small resource.
	else
    {
        searchPath.push_back(smallResource.directory);

        director->setContentScaleFactor(MIN(smallResource.size.height/designResolutionSize.height, smallResource.size.width/designResolutionSize.width));
    }
    
    // set searching path
    FileUtils::getInstance()->setSearchPaths(searchPath);
	
    // turn on display FPS
    director->setDisplayStats(true);

    // set FPS. the default value is 1.0/60 if you don't call this
    director->setAnimationInterval(1.0 / 60);

    // create a scene. it's an autorelease object
    auto scene = HelloWorld::scene();

    // run
    director->runWithScene(scene);

	// init ShareREC
	ShareRec::setMaxFrameSize(getMaxFrameSize("srec_key_maxFrameSize"));
	ShareRec::setVideoQuality(getVideoQuality("srec_key_videoQuality"));
	ShareRec::setMinDuration(1000 * getLongConfig("srec_key_minDuration"));
	ShareRec::setCacheFolder(getStringConfig("srec_key_cacheFolder"));

	// Force ShareREC to use the software video / audio encoder, which will be better compatibility, but cost more CPU utilization
 	bool sWAudioEnc = getBoolConfig("srec_key_softwareAudioEncoder");
	bool sWVideoEnc = getBoolConfig("srec_key_softwareVideoEncoder");
	ShareRec::setForceSoftwareEncoding(sWVideoEnc, sWAudioEnc);
	
    return true;
}

// This function will be called when the app is inactive. When comes a phone call,it's be invoked too
void AppDelegate::applicationDidEnterBackground() {
    Director::getInstance()->stopAnimation();
	ShareRec::pauseRecorder();

    // if you use SimpleAudioEngine, it must be pause
    // SimpleAudioEngine::sharedEngine()->pauseBackgroundMusic();
}

// this function will be called when the app is active again
void AppDelegate::applicationWillEnterForeground() {
    Director::getInstance()->startAnimation();
	ShareRec::resumeRecorder();

    // if you use SimpleAudioEngine, it must resume here
    // SimpleAudioEngine::sharedEngine()->resumeBackgroundMusic();
}
