package com.mygdx.game;

import android.opengl.GLES20;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.mob.MobSDK;
import com.mob.tools.utils.UIHandler;

import cn.sharerec.recorder.impl.LibGDXRecorder;
import cn.sharerec.recorder.impl.SrecApplicationAdapter;

/* 
 * 使MyGdxGame继承自SrecApplicationAdapter而非ApplicationAdapter，才能执行录屏
 * (extending MyGdxGame from SrecApplicationAdapter but ApplicationAdapter)
 */
public class MyGdxGame extends SrecApplicationAdapter implements InputProcessor {
	private static final int STEP = 10;
	private static final float FADE_STEP = 0.1f;
	private SpriteBatch batch;
	private Texture img;
	private BitmapFont whiteFont;
	private BitmapFont redFont;
	private Rectangle startRecorder;
	private Rectangle videoCenter;
	private Rectangle localVidoes;
	private Rectangle userProfile;
	
	private Rectangle stopRecorder;
	private Rectangle stopRecorderUpload;
	
	private int width;
	private int height;
	private int x;
	private int xStep;
	private int y;
	private int yStep;
	private float fade;
	private boolean started;
	
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("calibri.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 72;
		whiteFont = generator.generateFont(parameter);
		redFont = generator.generateFont(parameter);
		generator.dispose();
		whiteFont.setColor(1, 1, 1, 1);
		redFont.setColor(1, 0, 0, 1);
		
		Gdx.input.setInputProcessor(this);
	}
	
	public void pause() {
		// 暂停录制(pause recorder)
		getRecorder().pause();
	}
	
	public void resume() {
		// 恢复录制(resume recorder)
		getRecorder().resume();
	}
	
	/*
	 * render方法已经被占用且不能重载，请将游戏逻辑转移到onRender下
	 * (the method render has been overloaded, please move your game logics to onRender)
	 */
	protected void onRender() {
		getFrameSize();
		initButtons();
		if (isDirectionChanged()) {
			fade = 1;
		}
		drawFrame();
		prepareNextFrame();
	}
	
	private void getFrameSize() {
		if (width == 0 || height == 0) {
			int[] vp = new int[4];
			GLES20.glGetIntegerv(GLES20.GL_VIEWPORT, vp, 0);
			width = vp[2];
			height = vp[3];
		}
	}
	
	private void initButtons() {
		if (startRecorder == null) {
			GlyphLayout layout = new GlyphLayout();
			layout.setText(whiteFont, "Start Recorder");
			float btnHeight = layout.height + 40;
			float btnLeft = layout.width + 40;
			startRecorder = new Rectangle(0, height - 10, btnLeft, btnHeight);
			
			layout = new GlyphLayout();
			layout.setText(whiteFont, "Video Center");
			videoCenter = new Rectangle(0, height - 10 - btnHeight, btnLeft, btnHeight);
			
			layout = new GlyphLayout();
			layout.setText(whiteFont, "User Profile");
			userProfile = new Rectangle(0, height - 10 - btnHeight * 2, btnLeft, btnHeight);
			
			layout = new GlyphLayout();
			layout.setText(whiteFont, "Local Videos");
			localVidoes = new Rectangle(0, height - 10 - btnHeight * 3, btnLeft, btnHeight);
			
			
			//=================================================================
			layout = new GlyphLayout();
			layout.setText(redFont, "Stop Recorder Preview");
			stopRecorder = new Rectangle(0, height - 10, btnLeft, btnHeight);
			
			layout = new GlyphLayout();
			layout.setText(redFont, "Stop Recorder Upload");
			stopRecorderUpload = new Rectangle(0, height - 10 - btnHeight, btnLeft, btnHeight);
		}
	}
	
	private boolean isDirectionChanged() {
		boolean dirChanged = false;
		if (x <= 0) {
			xStep = STEP;
			dirChanged = true;
		} else if (x >= width - img.getWidth()) {
			xStep = -STEP;
			dirChanged = true;
		}
		
		if (y <= 0) {
			yStep = STEP;
			dirChanged = true;
		} else if (y >= height - img.getHeight()) {
			yStep = -STEP;
			dirChanged = true;
		}
		return dirChanged;
	}
	
	private void drawFrame() {
		Gdx.gl.glClearColor((fade > 0 ? fade : 0), 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		if (started) {
			redFont.draw(batch, "Stop Recorder Preview", stopRecorder.x + 20, stopRecorder.y - 10);
			redFont.draw(batch, "Stop Recorder Upload", stopRecorderUpload.x + 20, stopRecorderUpload.y - 10);
		} else {
			whiteFont.draw(batch, "Start Recorder", startRecorder.x + 20, startRecorder.y - 10);
			whiteFont.draw(batch, "Video Center", videoCenter.x + 20, videoCenter.y - 10);
			whiteFont.draw(batch, "User Profile", userProfile.x + 20, userProfile.y - 10);
			whiteFont.draw(batch, "Local Videos", localVidoes.x + 20, localVidoes.y - 10);
		}
		batch.end();
	}
	
	private void prepareNextFrame() {
		x += xStep;
		y += yStep;
		fade -= FADE_STEP;
		if (fade < 0) {
			fade = 0;
		}
	}
	
	public boolean keyDown(int keycode) {
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean keyUp(int keycode) {
		return false;
	}
	
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	public boolean scrolled(int amount) {
		return false;
	}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	private boolean upload = false;
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int touchY = height - screenY;
		if (started) {
			if (stopRecorder.x <= screenX && (stopRecorder.x + stopRecorder.getWidth() >= screenX)
					&& stopRecorder.y >= touchY && stopRecorder.y - stopRecorder.getHeight() <= touchY) {
				upload = false;
				// 停止录制 (stop recorder)
				getRecorder().stop();
			}else if(stopRecorderUpload.x <= screenX && (stopRecorderUpload.x + stopRecorderUpload.getWidth() >= screenX)
					&& stopRecorderUpload.y >= touchY && stopRecorderUpload.y - stopRecorderUpload.getHeight() <= touchY){
				upload = true;
				getRecorder().stop();
			}
		} else {
			if (startRecorder.x <= screenX && (startRecorder.x + startRecorder.getWidth() >= screenX)
					&& startRecorder.y >= touchY && startRecorder.y - startRecorder.getHeight() <= touchY) {
				if (getRecorder().isAvailable()) {
					// 开始录制 (start recorder)
					getRecorder().start();
				} else {
					UIHandler.sendEmptyMessage(0, new Callback() {
						public boolean handleMessage(Message msg) {
							Toast.makeText(MobSDK.getContext(), "Not supported device", Toast.LENGTH_SHORT).show();
							return false;
						}
					});
				}
			} else if (videoCenter.x <= screenX && (videoCenter.x + videoCenter.getWidth() >= screenX)
					&& videoCenter.y >= touchY && videoCenter.y - videoCenter.getHeight() <= touchY) {
				// 进入视频中心(Enter video center page)
				getRecorder().showVideoCenter();
			} else if (userProfile.x <= screenX && (userProfile.x + userProfile.getWidth() >= screenX)
					&& userProfile.y >= touchY && userProfile.y - userProfile.getHeight() <= touchY) {
				// 进入个人页面(Enter user profile page)
				getRecorder().showProfile();
			}else if(localVidoes.x <= screenX && (localVidoes.x + localVidoes.getWidth() >= screenX)
					&& localVidoes.y >= touchY && localVidoes.y - localVidoes.getHeight() <= touchY){
				getRecorder().showLocalVideos(null);
			}
		}
		return false;
	}
	
	protected void onStateChange(int state) {
		if (state == LibGDXRecorder.STATE_STARTED) {
			started = true;
		} else if (state == LibGDXRecorder.STATE_STOPPED) {
			started = false;
			
			// 设置分享参数并打开分享页面(Set share params and show share page)
			getRecorder().setText("libgdx Demo");
			getRecorder().addCustomAttr("score", "5000");
			getRecorder().addCustomAttr("name", "ShareRec Developer");
			getRecorder().addCustomAttr("brand", "hehe!");
			getRecorder().addCustomAttr("level", "10");
			UIHandler.sendEmptyMessage(0, new Callback() {
				
				@Override
				public boolean handleMessage(Message msg) {
					if(upload){
						getRecorder().showShare();	
					}else {
						getRecorder().showLastVideoOffLine();
					}
					return false;
				}
			});
			
			
		}
	}
	
}
