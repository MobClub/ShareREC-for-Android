package org.cocos2dx.simplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class DemoSelector extends Activity {
	private static boolean withRECBar;
	
	public static boolean isWithRECBar() {
		return withRECBar;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);
		
		Button btn1 = new Button(this);
		btn1.setText("Launch Game without RECBar");
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				withRECBar = false;
				startActivity(new Intent(v.getContext(), SimpleGame.class));
				finish();
			}
		});
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.addView(btn1, lp);
		
		Button btn2 = new Button(this);
		btn2.setText("Launch Game with RECBar");
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				withRECBar = true;
				startActivity(new Intent(v.getContext(), RECBarDemo.class));
				finish();
			}
		});
		ll.addView(btn2, lp);
	}
	
}
