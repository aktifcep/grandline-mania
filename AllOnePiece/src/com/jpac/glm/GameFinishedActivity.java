package com.jpac.glm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jpac.allonepiece.R;
import com.jpac.glm.util.Util;

public class GameFinishedActivity extends GameCoreActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finished);
		
		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");

		((TextView) findViewById(R.id.textView1)).setTypeface(font);
		((TextView) findViewById(R.id.textView2)).setTypeface(font);
		((TextView) findViewById(R.id.textView3)).setTypeface(font);
		((TextView) findViewById(R.id.textView4)).setTypeface(font);
		((Button) findViewById(R.id.continueButton)).setTypeface(font);
		
		findViewById(R.id.continueButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GameFinishedActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
				startActivity(intent);
			}
		});
	}
}
