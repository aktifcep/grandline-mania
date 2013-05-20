package com.jpac.allonepiece;

import com.jpac.allonepiece.model.AnswerOnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class CorrectAnswerActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_correct);
		
		findViewById(R.id.continueButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(CorrectAnswerActivity.this, GameActivity.class));
			}
		});
		
		String answer = getIntent().getStringExtra("answer");
		prepareAnswer(answer);
	}
	
	
	protected void prepareAnswer(String answer) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.answerLayout);
		layout.removeAllViews();
		
		String[] subLayouts = answer.split("%");
		int n = subLayouts.length;
		int idx = 0;
		
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
				button.setText(answers.charAt(j)+"");
				button.setWidth(55);
				button.setClickable(false);
				button.setEnabled(false);
				button.setOnClickListener(new AnswerOnClickListener(idx++));
				sublayout.addView(button);
			}
			
			layout.addView(sublayout);
			
		}
	}
}
