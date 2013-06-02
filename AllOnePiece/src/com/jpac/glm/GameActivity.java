package com.jpac.glm;

import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jpac.allonepiece.R;
import com.jpac.glm.model.AnswerDoneListener;
import com.jpac.glm.model.ButtonManager2;
import com.jpac.glm.model.QuestionBundle;
import com.jpac.glm.model.QuestionManager;
import com.jpac.glm.model.UserDataManager;
import com.jpac.glm.model.UserDataManager.UserData;
import com.jpac.glm.util.Util;

public class GameActivity extends GameCoreActivity implements AnswerDoneListener {

	protected static QuestionBundle qb = null;
	
	protected static QuestionBundle answered = null;
	
	protected static UserData userdata = null;
	
	private ButtonManager2 btnManager = ButtonManager2.getInstance();
	
	private String currentAnswer;

	private String rawCurrentAnswer;
	
	private QuestionBundle question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(!QuestionManager.getInstance().isReady()) {
			QuestionManager.getInstance().init(this);
		}
		
		UserData data = UserDataManager.getInstance().getData(this);
		Log.v("jpac", data.toString());
		userdata = data;
		if(data.getQID() != -1) {
			question = QuestionManager.getInstance().getQuestionByID(data.getQID());
		} else {
			question = QuestionManager.getInstance().getNextQuestion(null);
		}
		
		if(question != null) {
			setContentView(R.layout.activity_game);
			userdata.setQID(question.getID());
			btnManager.addAnswerDoneListener(this);
			currentAnswer = "";
			showQuestion();
		} else {
			Intent intent = new Intent(this, GameFinishedActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			this.finish();
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		UserDataManager.getInstance().saveData(this, userdata);
	}

	public void showQuestion() {

		qb = (QuestionBundle) question.clone();
			
		rawCurrentAnswer = question.getAnswer();
		char[] xyz = QuestionManager.getInstance().generateRandomLetters(rawCurrentAnswer, question.getRandomLetterSeed());

		btnManager.init(this, xyz);
		
		((TextView) findViewById(R.id.categoryLabel)).setText(question.getCategory().getName());
		
		prepareAnswer(rawCurrentAnswer);
		
		((Button) findViewById(R.id.backButton)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				GameActivity.this.finish();
			}
		});
		
		try {
			ImageView iv = (ImageView) findViewById(R.id.itemImage);
			Bitmap bm = Util.getBitmap(getAssets(), "image/"+question.getID()+".png");
			bm = Util.resize(bm, 0.35);
			iv.setImageBitmap(bm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((TextView) findViewById(R.id.levelLabel)).setText("Level "+QuestionManager.getInstance().getAnsweredQuestionsCount());
		((TextView) findViewById(R.id.goldLabel)).setText("Gold: " + userdata.getGold());
		
		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		((TextView) findViewById(R.id.levelLabel)).setTypeface(font);
		((TextView) findViewById(R.id.categoryLabel)).setTypeface(font);
		((TextView) findViewById(R.id.backButton)).setTypeface(font);
		((TextView) findViewById(R.id.goldLabel)).setTypeface(font);
		
		findViewById(R.id.addAnswerButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ButtonManager2.getInstance().putCorrectAnswerButton(mergeAnswer(splitAnswer(rawCurrentAnswer)));
			}
		});
		
		findViewById(R.id.removeChoiceButton).setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				ButtonManager2.getInstance().removeWrongChoiceButton(mergeAnswer(splitAnswer(rawCurrentAnswer)));
			}
		});
	}
	
	private String[] splitAnswer(String answer) {
		return answer.split("%");
	}
	
	private String mergeAnswer(String[] answer) {
		int n = answer.length;
		String merge = "";
		
		for(int i=0; i<n; i++) {
			merge += answer[i];
		}
		
		return merge;
	}

	protected void prepareAnswer(String answer) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.answerLayout);
		layout.removeAllViews();
		
		String[] subLayouts = splitAnswer(answer);
		int n = subLayouts.length;

		Typeface font = Util.getFont(getAssets(), "fonts/freshman.ttf");
		
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
				button.setTypeface(font);
				button.setTextColor(Color.WHITE);
				button.setText("");
				button.setClickable(false);
				button.setEnabled(false);
				final LinearLayout.LayoutParams viewMargin = new LinearLayout.LayoutParams(55, 55);
				viewMargin.setMargins(1, 1, 1, 1);
				button.setLayoutParams(viewMargin);
				sublayout.addView(button);
				btnManager.addAnswerButton(button);
			}
			
			layout.addView(sublayout);
			
		}
	}

	@Override
	public void onAnswerComplete(String sequence) {
		Log.v("jpac", ">" + currentAnswer + "><" + sequence + "<");
		if(sequence.compareTo(currentAnswer) == 0) {
			answered = qb;

			question = QuestionManager.getInstance().getNextQuestion(answered);
			userdata.setGold(userdata.getGold()+5);
			if(question != null) {
				userdata.setQID(question.getID());
			}
			
			UserDataManager.getInstance().saveData(this, userdata);
			Intent intent = new Intent(this, CorrectAnswerActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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
