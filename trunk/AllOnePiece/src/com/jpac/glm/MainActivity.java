package com.jpac.glm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jpac.allonepiece.R;
import com.jpac.glm.model.QuestionManager;
import com.jpac.glm.util.Util;

public class MainActivity extends GameCoreActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		QuestionManager.getInstance().init(getApplicationContext());

		findViewById(R.id.playButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		

		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		((Button) findViewById(R.id.playButton)).setTypeface(font);
		((Button) findViewById(R.id.languageOption)).setTypeface(font);
		((Button) findViewById(R.id.soundOption)).setTypeface(font);
		((Button) findViewById(R.id.infoOption)).setTypeface(font);
		((TextView) findViewById(R.id.currentLevel)).setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
