package com.jpac.allonepiece;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jpac.allonepiece.model.AnswerDoneListener;
import com.jpac.allonepiece.model.AnswerOnClickListener;
import com.jpac.allonepiece.model.ButtonManager;
import com.jpac.allonepiece.model.QuestionBundle;
import com.jpac.allonepiece.model.QuestionManager;
import com.jpac.allonepiece.util.Util;

public class GameActivity extends Activity implements AnswerDoneListener {

	private ButtonManager btnManager = ButtonManager.getInstance();
	
	private String currentAnswer;
	
	private String rawCurrentAnswer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game);
		
		btnManager.addAnswerDoneListener(this);
		currentAnswer = "";

		showQuestion();
	}
	
	public void showQuestion() {
		
		QuestionBundle qb = QuestionManager.getInstance().getNextQuestion(null);
		
		if(qb == null) {
			Toast.makeText(getApplicationContext(), "Game is Finished", Toast.LENGTH_SHORT).show();
		} else {
			
			rawCurrentAnswer = qb.getAnswer();
			char[] xyz = QuestionManager.getInstance().generateRandomLetters(rawCurrentAnswer, qb.getRandomLetterSeed());
			
			((Button) findViewById(R.id.letter1)).setText(""+xyz[0]);
			((Button) findViewById(R.id.letter2)).setText(""+xyz[1]);
			((Button) findViewById(R.id.letter3)).setText(""+xyz[2]);
			((Button) findViewById(R.id.letter4)).setText(""+xyz[3]);
			((Button) findViewById(R.id.letter5)).setText(""+xyz[4]);
			((Button) findViewById(R.id.letter6)).setText(""+xyz[5]);
			((Button) findViewById(R.id.letter7)).setText(""+xyz[6]);
			((Button) findViewById(R.id.letter8)).setText(""+xyz[7]);
			((Button) findViewById(R.id.letter9)).setText(""+xyz[8]);
			((Button) findViewById(R.id.letter10)).setText(""+xyz[9]);
			((Button) findViewById(R.id.letter11)).setText(""+xyz[10]);
			((Button) findViewById(R.id.letter12)).setText(""+xyz[11]);
			((Button) findViewById(R.id.letter13)).setText(""+xyz[12]);
			((Button) findViewById(R.id.letter14)).setText(""+xyz[13]);

			btnManager.addChoiceButton((Button) findViewById(R.id.letter1));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter2));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter3));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter4));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter5));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter6));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter7));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter8));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter9));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter10));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter11));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter12));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter13));
			btnManager.addChoiceButton((Button) findViewById(R.id.letter14));

			Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
			btnManager.setTypeFaceForChoice(font);
			
			((TextView) findViewById(R.id.categoryLabel)).setText(qb.getCategory().getName());
			
			prepareAnswer(rawCurrentAnswer);
			
			((Button) findViewById(R.id.backButton)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					GameActivity.this.finish();
				}
			});
			
			((TextView) findViewById(R.id.levelLabel)).setText(""+QuestionManager.getInstance().getAnsweredQuestionsCount());
			
			((TextView) findViewById(R.id.levelLabel)).setTypeface(font);
			((TextView) findViewById(R.id.categoryLabel)).setTypeface(font);
			((TextView) findViewById(R.id.backButton)).setTypeface(font);
		}		
	}
	
	protected void prepareAnswer(String answer) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.answerLayout);
		layout.removeAllViews();
		btnManager.clearAnswer();
		
		String[] subLayouts = answer.split("%");
		int n = subLayouts.length;
		int idx = 0;
		
		for(int i=0; i<n; i++) {
			// TODO: put dynamic addition of linear layouts for answer here
			LinearLayout sublayout = new LinearLayout(this);
			sublayout.setOrientation(LinearLayout.HORIZONTAL);
			sublayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			sublayout.setGravity(Gravity.CENTER_HORIZONTAL);
			
			currentAnswer += subLayouts[i];
			String answers = subLayouts[i];
			int m = answers.length();
			for(int j=0; j<m; j++) {
				Button button = new Button(this);
				button.setBackgroundResource(R.drawable.btn_black);
				button.setText("");
				button.setClickable(false);
				button.setEnabled(false);
				final LinearLayout.LayoutParams viewMargin = new LinearLayout.LayoutParams(55, 55);
				viewMargin.setMargins(1, 1, 1, 1);
				button.setLayoutParams(viewMargin);
				button.setOnClickListener(new AnswerOnClickListener(idx++));
				
				sublayout.addView(button);
				btnManager.addAnswerButton(button);
			}
			
			layout.addView(sublayout);
			
		}

		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		btnManager.setTypeFaceForAnswer(font);
	}

	@Override
	public void onAnswerComplete(String sequence) {
		if(sequence.compareTo(currentAnswer) == 0) {
			Intent intent = new Intent(this, CorrectAnswerActivity.class);
			intent.putExtra("answer", rawCurrentAnswer);
			startActivity(intent);
		} else {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			findViewById(R.id.answerLayout).startAnimation(shake);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
}
