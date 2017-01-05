package com.mob.glesdemo.gloes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView.Renderer;

public class CubeRendererES1 implements Renderer  {
	private FloatBuffer cubePositions;
	private FloatBuffer cubeColors;
	private float angleInDegrees;
	private float xScale;
	
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		initPositions();
		initColors();
		initMatrix();
	}
	
	private void initPositions() {
		float[] cubePosition = {
				// Front face
				-1.0f, 1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 
				-1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				
				// Right face
				1.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, -1.0f,
				1.0f, 1.0f, -1.0f,
				
				// Back face
				1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,
				
				// Left face
				-1.0f, 1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, 1.0f, 
				-1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, 1.0f, 
				-1.0f, 1.0f, 1.0f, 
				
				// Top face
				-1.0f, 1.0f, -1.0f,
				-1.0f, 1.0f, 1.0f, 
				1.0f, 1.0f, -1.0f, 
				-1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 
				1.0f, 1.0f, -1.0f,
				
				// Bottom face
				1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, 1.0f, 
				-1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,
				-1.0f, -1.0f, -1.0f,
		};
		ByteBuffer bb = ByteBuffer.allocateDirect(cubePosition.length * 4);
		bb.order(ByteOrder.nativeOrder());
		cubePositions = bb.asFloatBuffer();
		cubePositions.put(cubePosition);
		cubePositions.position(0);
	}

	private void initColors() {
		float[] cubeColor = {
				// Front face (red)
				1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f,
				
				// Right face (green)
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				
				// Back face (blue)
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				
				// Left face (yellow)
				1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f,
				
				// Top face (cyan)
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				
				// Bottom face (magenta)
				1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f
		};
		ByteBuffer bb = ByteBuffer.allocateDirect(cubeColor.length * 4);
		bb.order(ByteOrder.nativeOrder());
		cubeColors = bb.asFloatBuffer();
		cubeColors.put(cubeColor);
		cubeColors.position(0);
	}

	private void initMatrix() {
		GLES10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES10.glEnable(GLES10.GL_CULL_FACE);
	}
	
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		xScale = 0.25f * width / height;
		GLES10.glViewport(0, 0, width, height);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		GLES10.glPushMatrix();
		GLES10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT);
		drawCube(cubePositions, cubeColors);
		GLES10.glPopMatrix();
	}
	
	private void drawCube(FloatBuffer cubePositionsBuffer, FloatBuffer cubeColorsBuffer) {
		cubePositionsBuffer.position(0);
		GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);
		GLES10.glVertexPointer(3, GLES10.GL_FLOAT, 0, cubePositionsBuffer);
 
		cubeColorsBuffer.position(0);
		GLES10.glEnableClientState(GL10.GL_COLOR_ARRAY); 
		GLES10.glColorPointer(4, GLES10.GL_FLOAT, 0, cubeColorsBuffer);
		
		GLES10.glLoadIdentity();
		GLES10.glScalef(xScale, 0.25f, 0.5f);
		GLES10.glRotatef(angleInDegrees, 1.0f, 1.0f, 0.0f);
		GLES10.glDrawArrays(GLES10.GL_TRIANGLES, 0, 36);
	}

	public void setAngleInDegrees(float angleInDegrees) {
		this.angleInDegrees = angleInDegrees;
	}
	
}
