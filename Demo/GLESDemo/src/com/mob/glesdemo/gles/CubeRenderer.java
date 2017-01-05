package com.mob.glesdemo.gles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

public class CubeRenderer implements Renderer {
	private static final float INSIDE_CUBE_LEN = 0.7f;
	private static final float INSIDE_CUBE_OUT = 1.0f;
	
	private FloatBuffer cubePositions;
	private FloatBuffer cubeColors;
	private float[] viewMatrix;
	private float[] projectionMatrix;
	private float[] modelMatrix;
	private float[] mvpMatrix;
	private int programHandle;
	private int mvpMatrixHandle;
	private int positionHandle;
	private int colorHandle;
	private float angleInDegrees;
	private FBO fbo;
	private int width;
	private int height;
	private boolean fboEnable;
	
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		fbo = new FBO();
		initPositions();
		initColors();
		initMatrix();
		initProgram();
	}
	
	private void initPositions() {
		float[] cubePosition = {
				// Front face
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_OUT,	
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_OUT,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, 
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_OUT,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_OUT,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_OUT,
				
				// Right face
				INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				
				// Back face
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT,
				
				// Left face
				-INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				
				// Top face
				-INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, 
				INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, INSIDE_CUBE_LEN,	
				INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, 
				INSIDE_CUBE_LEN, INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN,
				
				// Bottom face
				INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, INSIDE_CUBE_LEN,	
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_OUT, -INSIDE_CUBE_LEN,
		};
		
		float[] cubePositionOut = {
				// Front face
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				
				// Right face
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				
				// Back face
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				
				// Left face
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				
				// Top face
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				
				// Bottom face
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN, 
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
				INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, INSIDE_CUBE_LEN,
				-INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN, -INSIDE_CUBE_LEN,
		};
		
		ByteBuffer bb = ByteBuffer.allocateDirect(cubePosition.length * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		cubePositions = bb.asFloatBuffer();
		cubePositions.put(cubePosition);
		cubePositions.put(cubePositionOut);
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
		
		float[] cubeColorOut = {
				// Front face (red)
				0.7f, 0.0f, 0.0f, 1.0f,
				0.7f, 0.0f, 0.0f, 1.0f,
				0.7f, 0.0f, 0.0f, 1.0f,
				0.7f, 0.0f, 0.0f, 1.0f,
				0.7f, 0.0f, 0.0f, 1.0f,
				0.7f, 0.0f, 0.0f, 1.0f,
				
				// Right face (green)
				0.0f, 0.7f, 0.0f, 1.0f,
				0.0f, 0.7f, 0.0f, 1.0f,
				0.0f, 0.7f, 0.0f, 1.0f,
				0.0f, 0.7f, 0.0f, 1.0f,
				0.0f, 0.7f, 0.0f, 1.0f,
				0.0f, 0.7f, 0.0f, 1.0f,
				
				// Back face (blue)
				0.0f, 0.0f, 0.7f, 1.0f,
				0.0f, 0.0f, 0.7f, 1.0f,
				0.0f, 0.0f, 0.7f, 1.0f,
				0.0f, 0.0f, 0.7f, 1.0f,
				0.0f, 0.0f, 0.7f, 1.0f,
				0.0f, 0.0f, 0.7f, 1.0f,
				
				// Left face (yellow)
				0.7f, 0.7f, 0.0f, 1.0f,
				0.7f, 0.7f, 0.0f, 1.0f,
				0.7f, 0.7f, 0.0f, 1.0f,
				0.7f, 0.7f, 0.0f, 1.0f,
				0.7f, 0.7f, 0.0f, 1.0f,
				0.7f, 0.7f, 0.0f, 1.0f,
				
				// Top face (cyan)
				0.0f, 0.7f, 0.7f, 1.0f,
				0.0f, 0.7f, 0.7f, 1.0f,
				0.0f, 0.7f, 0.7f, 1.0f,
				0.0f, 0.7f, 0.7f, 1.0f,
				0.0f, 0.7f, 0.7f, 1.0f,
				0.0f, 0.7f, 0.7f, 1.0f,
				
				// Bottom face (magenta)
				0.7f, 0.0f, 0.7f, 1.0f,
				0.7f, 0.0f, 0.7f, 1.0f,
				0.7f, 0.0f, 0.7f, 1.0f,
				0.7f, 0.0f, 0.7f, 1.0f,
				0.7f, 0.0f, 0.7f, 1.0f,
				0.7f, 0.0f, 0.7f, 1.0f
		};
		
		ByteBuffer bb = ByteBuffer.allocateDirect(cubeColor.length * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		cubeColors = bb.asFloatBuffer();
		cubeColors.put(cubeColor);
		cubeColors.put(cubeColorOut);
		cubeColors.position(0);
	}

	private void initMatrix() {
		viewMatrix = new float[16];
		projectionMatrix = new float[16];
		modelMatrix = new float[16];
		mvpMatrix = new float[16];
		
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES20.glDisable(GLES20.GL_CULL_FACE);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		// Position the eye behind the origin.
		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = -1.0f;

		// We are looking toward the distance
		final float lookX = 0.0f;
		final float lookY = 0.0f;
		final float lookZ = -5.0f;

		// Set our up vector. This is where our head would be pointing were we holding the camera.
		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;

		// Set the view matrix. This matrix can be said to represent the camera position.
		// NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
		// view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
		Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
	}
	
	private void initProgram() {
		int vertexShaderHandle = initVertexShader();
		int fragmentShaderHandle = initFragmentShader();
		programHandle = GLES20.glCreateProgram();
		if(programHandle != 0) {
			GLES20.glAttachShader(programHandle, vertexShaderHandle);
			GLES20.glAttachShader(programHandle, fragmentShaderHandle);
			GLES20.glBindAttribLocation(programHandle, 0, "a_Position");
			GLES20.glBindAttribLocation(programHandle, 1, "a_Color");
			GLES20.glLinkProgram(programHandle);
			
			int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);
			if(linkStatus[0] == 0) {
				GLES20.glDeleteProgram(programHandle);
				programHandle = 0;
			}
		}
		
		if(programHandle == 0) {
			throw new RuntimeException("failed to create program");
		}
		
		mvpMatrixHandle = GLES20.glGetUniformLocation(programHandle, "u_MVPMatrix");
		positionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
		colorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");
	}
	
	private int initVertexShader() {
		String vertexShader =
				"uniform mat4 u_MVPMatrix;						\n" // A constant representing the combined model/view/projection matrix.
				+ "attribute vec4 a_Position;					\n"	// Per-vertex position information we will pass in.
				+ "attribute vec4 a_Color;						\n"	// Per-vertex color information we will pass in.
				+ "varying vec4 v_Color;						\n"	// This will be passed into the fragment shader.
				+ "void main() {								\n"	// The entry point for our vertex shader.
				+ "   v_Color = a_Color;						\n"	// Pass the color through to the fragment shader.
				+ "   gl_Position = u_MVPMatrix * a_Position;	\n"	// Multiply the vertex by the matrix to get the final point in
				+ "}											\n";// normalized screen coordinates.
			
		int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		if(vertexShaderHandle != 0) {
			GLES20.glShaderSource(vertexShaderHandle, vertexShader);
			GLES20.glCompileShader(vertexShaderHandle);
			int[] compileStatus = new int[1];
			GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
			if(compileStatus[0] == 0) {
				GLES20.glDeleteShader(vertexShaderHandle);
				vertexShaderHandle = 0;
			}
		}
		
		if(vertexShaderHandle == 0) {
			throw new RuntimeException("failed to creating vertex shader");
		}
		return vertexShaderHandle;
	}
	
	private int initFragmentShader() {
		String fragmentShader =
				"precision mediump float;	\n" // Set the default precision to medium. We don't need as high of a precision in the fragment shader.
				+ "varying vec4 v_Color;	\n" // This is the color from the vertex shader interpolated across the triangle per fragment.
				+ "void main() {			\n" // The entry point for our fragment shader.
				+ "	gl_FragColor = v_Color;	\n" // Pass the color directly through the pipeline.
				+ "}						\n";	
		int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		if(fragmentShaderHandle != 0) {
			GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);
			GLES20.glCompileShader(fragmentShaderHandle);
			int[] compileStatus = new int[1];
			GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
			if(compileStatus[0] == 0) {
				GLES20.glDeleteShader(fragmentShaderHandle);
				fragmentShaderHandle = 0;
			}
		}
		
		if(fragmentShaderHandle == 0) {
			throw new RuntimeException("failed to create fragment shader");
		}
		return fragmentShaderHandle;
	}
	
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		this.width = width;
		this.height = height;
		GLES20.glViewport(0, 0, width, height);
		
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 10.0f;
		
		Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
	}
	
	public void onDrawFrame(GL10 glUnused) {
		if (fboEnable) {
			if (!fbo.isReady()) {
				fbo.prepareFBO(width, height);
			}
			
			if (fbo.isReady()) {
				fbo.bineFBO();
				drawFrame();
				fbo.unbineFBO();
			}
		} else {
			drawFrame();
		}
	}
	
	private void drawFrame() {
		int[] id = new int[1];
		GLES20.glGetIntegerv(GLES20.GL_CURRENT_PROGRAM, id, 0);
		GLES20.glUseProgram(programHandle);
		
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, 0.0f, 0.0f, -5.0f);
		Matrix.rotateM(modelMatrix, 0, angleInDegrees, 1.0f, 1.0f, 0.0f);   
		drawCube(cubePositions, cubeColors);
		
		GLES20.glUseProgram(id[0]);
	}
	
	private void drawCube(FloatBuffer cubePositionsBuffer, FloatBuffer cubeColorsBuffer) {
		cubePositionsBuffer.position(0);
		GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, cubePositionsBuffer);
		GLES20.glEnableVertexAttribArray(positionHandle);
		
		cubeColorsBuffer.position(0);
		GLES20.glVertexAttribPointer(colorHandle, 4, GLES20.GL_FLOAT, false, 0, cubeColorsBuffer);
		GLES20.glEnableVertexAttribArray(colorHandle);
		Matrix.multiplyMM(mvpMatrix, 0, viewMatrix, 0, modelMatrix, 0);   
		Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, mvpMatrix, 0);
		
		GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, cubePositionsBuffer.limit() / 3);
	}

	public void setAngleInDegrees(float angleInDegrees) {
		this.angleInDegrees = angleInDegrees;
	}
	
	public void setFboEnable(boolean enable) {
		fboEnable = enable;
	}
	
}
