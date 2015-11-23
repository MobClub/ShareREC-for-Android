package com.mob.rootrecorder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.android.gles3jni.GLES3JNILib;

import android.opengl.GLSurfaceView;


public class RootRenderer implements GLSurfaceView.Renderer {
	
    public void onDrawFrame(GL10 gl) {
        GLES3JNILib.step();
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES3JNILib.resize(width, height);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES3JNILib.init();
    }

}
