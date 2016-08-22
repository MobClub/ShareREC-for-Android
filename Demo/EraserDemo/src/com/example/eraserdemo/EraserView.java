package com.example.eraserdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EraserView extends RelativeLayout {

	public EraserView(Context context) {
		super(context);
		init(context, null);
	}

	public EraserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public EraserView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}
	
	private void init(Context context, AttributeSet attrs) {
		ImageView iv = new ImageView(context);
		iv.setScaleType(ScaleType.CENTER_INSIDE);
		addView(iv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EraserView);
		int resId = ta.getResourceId(R.styleable.EraserView_src, 0);
		ta.recycle();
		if (resId > 0) {
			iv.setImageResource(resId);
		}
		
		MaskView mv = new MaskView(context);
		addView(mv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		final TextView tvTime = new TextView(context);
		tvTime.setMinEms(8);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(ALIGN_PARENT_BOTTOM);
		addView(tvTime, lp);
		
		tvTime.post(new Runnable() {
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss aa");
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				tvTime.setText(sdf.format(cal.getTime()));
				tvTime.postDelayed(this, 300);
			}
		});
	}

	private static class MaskView extends View {
		private static final int MASK_COLOR = 0x7f000000;
		private static final float TOUCH_TOLERANCE = 6;
		private int screenWidth;
		private int screenHeight;
		private Bitmap bitmap;
		private Canvas bmCanvas;
		private Paint mPaint;
		private Path mPath;
		private float mX, mY;

		public MaskView(Context context) {
			super(context);
			init(context);
		}

		public MaskView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public MaskView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}
		
		private void init(Context context) {
			DisplayMetrics dm = new DisplayMetrics();
			dm = this.getResources().getDisplayMetrics();
			screenWidth = dm.widthPixels;
			screenHeight = dm.heightPixels;
			
			mPaint = new Paint();
			mPaint.setAlpha(0);
			mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
			mPaint.setAntiAlias(true);
			mPaint.setDither(true);
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(60);
			
			mPath = new Path();
			
			bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Config.ARGB_8888);
			bmCanvas = new Canvas(bitmap);
			bmCanvas.drawColor(MASK_COLOR);
		}

		protected void onDraw(Canvas canvas) {
			bmCanvas.drawPath(mPath, mPaint);
			canvas.drawBitmap(bitmap, 0, 0, null);
		}

		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					mPath.reset();
					mPath.moveTo(x, y);
					mX = x;
					mY = y;
					invalidate();
				}
				break;
				case MotionEvent.ACTION_MOVE: {
					float dx = Math.abs(x - mX);
					float dy = Math.abs(y - mY);
					if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
						mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
						mX = x;
						mY = y;
					}
					invalidate();
				}
				break;
				case MotionEvent.ACTION_UP: {
					mPath.reset();
				}
				break;
			}
			return true;
		}

	}
	
}
