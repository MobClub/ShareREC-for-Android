package com.mob.rootrecorder;

import cn.sharerec.recorder.OnRecorderStateListener;
import cn.sharerec.recorder.Recorder;
import cn.sharerec.recorder.impl.RootRecorder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScreenActivity extends Activity {
	
	private RootRecorder recorder;
	
	private OnRecorderStateListener getOnRecorderStateListener() {
		return new OnRecorderStateListener() {
			public void onStateChange(Recorder recorder, int state) {
				if (state == Recorder.STATE_PAUSING) {
					
				} else if (state == Recorder.STATE_STARTED) {
					
				} else if (state == Recorder.STATE_STOPPED) {
					if( recorder != null ){
						((RootRecorder)recorder).showShare();
					}
				}
			}
		};
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        glview = (RootGLSurfaceView)findViewById(R.id.glview);
        btn_start = (Button)findViewById(R.id.btnstart);
        btn_stop = (Button)findViewById(R.id.btnstop);
        btn_center = (Button)findViewById(R.id.btncenter);
        btn_exit = (Button)findViewById(R.id.btnexit);

        recorder = new RootRecorder(this, getAppkey());
        recorder.setBitRate(4000000);
        recorder.setFrameRate(30);
        
        btn_start.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	if (recorder != null && recorder.isAvailable()) {
					recorder.setOnRecorderStateListener(getOnRecorderStateListener());
					recorder.start();
				}
            }
        });
        
        btn_stop.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	if( recorder != null ){
            		recorder.stop();
            	}
            }
        });
        
        btn_center.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
				if (recorder != null) {
					recorder.showVideoCenter();
				}
            }
        });
        
        btn_exit.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	finish();
            }
        });
    }
    
    @Override protected void onPause() {
        super.onPause();
        glview.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        glview.onResume();
    }
    
	protected String getAppkey() {
		return "76684bc49b3";
	}

	private RootGLSurfaceView glview;
    private Button btn_start = null;
    private Button btn_stop = null;
    private Button btn_center = null;
    private Button btn_exit = null;

}
