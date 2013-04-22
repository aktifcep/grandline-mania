package com.jpac.allonepiece;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.jpac.allonepiece.model.QuestionBundle;
import com.jpac.allonepiece.model.QuestionManager;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		QuestionBundle qb = QuestionManager.getInstance().getNextQuestion(null);
		char[] xyz = QuestionManager.getInstance().generateRandomLetters(qb.getAnswer(), qb.getRandomLetterSeed());
		
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
	}
}
