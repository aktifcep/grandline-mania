package com.jpac.allonepiece;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jpac.allonepiece.util.Util;

public class CorrectAnswerActivity extends GameCoreActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_correct);
		
		findViewById(R.id.continueButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(CorrectAnswerActivity.this, GameActivity.class));
			}
		});

		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		
		((TextView) findViewById(R.id.textView1)).setTypeface(font);
		((TextView) findViewById(R.id.textView2)).setTypeface(font);
		((Button) findViewById(R.id.continueButton)).setTypeface(font);
				
		String answer = getIntent().getStringExtra("answer");
		prepareAnswer(answer);
	}
	
	
	protected void prepareAnswer(String answer) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.answerLayout);
		layout.removeAllViews();
		
		String[] subLayouts = answer.split("%");
		int n = subLayouts.length;

		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		
		for(int i=0; i<n; i++) {
			// TODO: put dynamic addition of linear layouts for answer here
			LinearLayout sublayout = new LinearLayout(this);
			sublayout.setOrientation(LinearLayout.HORIZONTAL);
			sublayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			sublayout.setGravity(Gravity.CENTER_HORIZONTAL);
			
			String answers = subLayouts[i];
			int m = answers.length();
			for(int j=0; j<m; j++) {
				Button button = new Button(this);
				button.setBackgroundResource(R.drawable.btn_black);
				button.setTypeface(font);
				button.setTextColor(Color.WHITE);
				button.setText("");
				button.setClickable(false);
				button.setEnabled(false);
				final LinearLayout.LayoutParams viewMargin = new LinearLayout.LayoutParams(55, 55);
				viewMargin.setMargins(1, 1, 1, 1);
				button.setLayoutParams(viewMargin);
				sublayout.addView(button);
			}
			
			layout.addView(sublayout);
			
		}
	}
}
