package com.mob.glesdemo.gles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;

public class FBO {
	private int oldFbo;
	private int fbo;
	private int texture;
	private int rbo;
	private boolean fboReady;
	private int program;
	private int attribPosition;
	private int attribcoord;
	private int uniformMatrix;
	private int uniformTexture;
	private FloatBuffer vertex;
	private FloatBuffer coord;
	private ByteBuffer index;
	private FloatBuffer matrix;
	
	public boolean isReady() {
		return fboReady;
	}
	
	public void prepareFBO(int width, int height) {
		int[] id = new int[1];
		GLES20.glGetIntegerv(GLES20.GL_FRAMEBUFFER_BINDING, id, 0);
		oldFbo = id[0];
		GLES20.glGenFramebuffers(1, id, 0);
		fbo = id[0];
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fbo);
		
		GLES20.glGenTextures(1, id, 0);
		texture = id[0];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, texture, 0);
		
		GLES20.glGenRenderbuffers(1, id, 0);
		rbo = id[0];
		GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, rbo);
		GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, width, height);
		GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, rbo);
		
		int status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
		if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
			id[0] = rbo;
			GLES20.glDeleteRenderbuffers(1, id, 0);
			id[0] = fbo;
			GLES20.glDeleteFramebuffers(1, id, 0);
			id[0] = texture;
			GLES20.glDeleteTextures(1, id, 0);
			GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFbo);
			return;
		}
		
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFbo);
		fboReady = true;
	}
	
	public void bineFBO() {
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fbo);
	}
	
	public void unbineFBO() {
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, oldFbo);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
		
		if (program <= 0) {
			initDrwProgram();
		}
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		
		int[] id = new int[1];
		GLES20.glGetIntegerv(GLES20.GL_CURRENT_PROGRAM, id, 0);
		
		GLES20.glUseProgram(program);
		GLES20.glEnableVertexAttribArray(attribPosition);
		GLES20.glVertexAttribPointer(attribPosition, 3, GLES20.GL_FLOAT, false, 12, vertex);
		GLES20.glEnableVertexAttribArray(attribcoord);
		GLES20.glVertexAttribPointer(attribcoord, 2, GLES20.GL_FLOAT, false, 8, coord);
		GLES20.glUniformMatrix4fv(uniformMatrix, 1, false, matrix);
		GLES20.glUniform1i(uniformTexture, 0);
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, 24, GLES20.GL_UNSIGNED_BYTE, index);
		
		GLES20.glUseProgram(id[0]);
	}
	
	private void initDrwProgram() {
		float[] drwQuadVertex = new float[] {
				-1, 1, 0,	 -1, 0, 0,	 0, 0, 0,	 0, 1, 0,	// left-top
				-1, 0, 0,	 -1, -1, 0,	 0, -1, 0,	 0, 0, 0,	// left-bottom
				0, 1, 0,	 0, 0, 0,	 1, 0, 0,	 1, 1, 0,	// right-top
				0, 0, 0,	 0, -1, 0,	 1, -1, 0,	 1, 0, 0	// right-bottom
		};
		ByteBuffer bb = ByteBuffer.allocateDirect(drwQuadVertex.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.put(drwQuadVertex);
		vertex.position(0);
		
		float[] drwQuadCoord = new float[] {
				0, 1,	0, 0,	1, 0,	1, 1,	// left-top
				0, 1,	0, 0,	1, 0,	1, 1,	// left-bottom
				0, 1,	0, 0,	1, 0,	1, 1,	// right-top
				0, 1,	0, 0,	1, 0,	1, 1	// right-bottom
		};
		bb = ByteBuffer.allocateDirect(drwQuadCoord.length * 4);
		bb.order(ByteOrder.nativeOrder());
		coord = bb.asFloatBuffer();
		coord.put(drwQuadCoord);
		coord.position(0);
		
		byte[] drwQuadIndex = new byte[] {
				(byte)(0), (byte)(1), (byte)(2),	(byte)(2), (byte)(3), (byte)(0),
				(byte)(4), (byte)(5), (byte)(6),	(byte)(6), (byte)(7), (byte)(4),
				(byte)(8), (byte)(9), (byte)(10),	(byte)(10), (byte)(11), (byte)(8),
				(byte)(12), (byte)(13), (byte)(14), (byte)(14), (byte)(15), (byte)(12)
		};
		bb = ByteBuffer.allocateDirect(drwQuadIndex.length);
		bb.order(ByteOrder.nativeOrder());
		bb.put(drwQuadIndex);
		bb.position(0);
		index = bb;
		
		float[] drawMatrix = new float[] {
				1, 0, 0, 0, 
				0, 1, 0, 0, 
				0, 0, 1, 0, 
				0, 0, 0, 1
		};
		bb = ByteBuffer.allocateDirect(drawMatrix.length * 4);
		bb.order(ByteOrder.nativeOrder());
		matrix = bb.asFloatBuffer();
		matrix.put(drawMatrix);
		matrix.position(0);
		
		String drwVertexSource = "uniform mat4 u_MVPMatrix;\n"
				+ "uniform mat4 u_matrix;\n"
				+ "attribute vec4 a_position;\n"
				+ "attribute vec4 a_coord;\n"
				+ "varying mediump vec2 v_texCoord;\n"
				+ "void main() {\n"
				+ "	gl_Position = u_matrix * a_position;\n"
				+ "	v_texCoord = a_coord.xy;\n"
				+ "}";
		String drwFragmentSource = "#ifdef GL_ES\n"
				+ "precision lowp float;\n"
				+ "#endif\n"
				+ "varying vec2 v_texCoord;\n"
				+ "uniform sampler2D u_texture;\n"
				+ "void main() {\n"
				+ "   gl_FragColor = vec4(texture2D(u_texture, v_texCoord).xyz, 1);\n"
				+ "}";
		program = loadProgram(drwVertexSource, drwFragmentSource);
		uniformMatrix = GLES20.glGetUniformLocation(program, "u_matrix");
		attribPosition = GLES20.glGetAttribLocation(program, "a_position");
		attribcoord = GLES20.glGetAttribLocation(program, "a_coord");
		uniformTexture = GLES20.glGetUniformLocation(program, "u_texture");
	}
	
	private int loadProgram(String vertexSource, String fragmentSource) {
		int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
		int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
		int program = GLES20.glCreateProgram();
		if (program == 0) {
			return 0;
		}
	 
		GLES20.glAttachShader(program, vertexShader);
		GLES20.glAttachShader(program, fragmentShader);
		GLES20.glLinkProgram(program);
		int[] id = new int[1];
		GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, id, 0);
		if (id[0] == 0) {
			GLES20.glDeleteProgram(program);
			return 0;
		}
	 
		GLES20.glDeleteShader(vertexShader);
		GLES20.glDeleteShader(fragmentShader);
		return program;
	}

	private int loadShader(int shaderType, String source) {
		int shader = GLES20.glCreateShader(shaderType);
		if (shader == 0) {
			return 0;
		}

		GLES20.glShaderSource(shader, source);
		GLES20.glCompileShader(shader);
		int[] id = new int[1];
		GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, id, 0);
		if (id[0] == 0) {
			GLES20.glDeleteShader(shader);
			throw new RuntimeException("Error compile shader: " + GLES20.glGetShaderInfoLog(shader));
		}
		
		return shader;
	}
}
