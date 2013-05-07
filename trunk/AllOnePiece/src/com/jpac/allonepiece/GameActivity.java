package com.jpac.allonepiece;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jpac.allonepiece.model.QuestionBundle;
import com.jpac.allonepiece.model.QuestionManager;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game);

		showQuestion();
	}
	
	public void showQuestion() {
		
		QuestionBundle qb = QuestionManager.getInstance().getNextQuestion(null);
		
		if(qb == null) {
			Toast.makeText(getApplicationContext(), "Game is Finished", Toast.LENGTH_SHORT).show();
		} else {
		
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
	
	protected void prepareAnswer(String answer) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.answerLayout);
		
		
	}
}