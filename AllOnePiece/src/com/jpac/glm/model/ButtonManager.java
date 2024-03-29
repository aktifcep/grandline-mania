package com.jpac.glm.model;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;

public class ButtonManager {

	private static final ButtonManager buttons = new ButtonManager();
	
	public static ButtonManager getInstance() {
		return buttons;
	}
	
	private ArrayList<Button> answerButtons;
	
	private ArrayList<Button> choicesButtons;
	
	private int choiceClicked = 0;
	
	private AnswerDoneListener answerDoneListener;
	
	private ButtonManager() {
		answerButtons = new ArrayList<Button>();
		choicesButtons = new ArrayList<Button>();
	}
	
	public void addChoiceButton(Button button) {
		button.setOnClickListener(new ChoiceOnClickListener());
		choicesButtons.add(button);
	}
	
	public void addAnswerButton(Button button) {
		answerButtons.add(button);
	}
	
	public Button getAnswerAt(int index) {
		return answerButtons.get(index);
	}
	
	public Button getChoiceAt(int index) {
		return choicesButtons.get(index);
	}
	
	public void toggleVisibility(int index) {
		Button btn = getChoiceAt(index);
		btn.setVisibility(btn.getVisibility()==View.VISIBLE?View.INVISIBLE:View.VISIBLE);
	}
	
	public void setHyphen(int index) {
		Button ans = answerButtons.get(index);
		ans.setEnabled(false);
		ans.setText("-");
	}
	
	public void setAnswer(String text) {
		int n = answerButtons.size();
		for(int i=0; i<n; i++) {
			Button btn = answerButtons.get(i);
			if(btn.getText().length() == 0) {
				btn.setText(text);
				btn.setEnabled(true);
				break;
			}
		}
		checkAnswer();
	}
	
	private void checkAnswer() {
		if(choiceClicked == answerButtons.size()) {
			String sequence = "";
			for(int i=0; i<choiceClicked; i++) {
				sequence += answerButtons.get(i).getText().toString();
			}
			this.answerDoneListener.onAnswerComplete(sequence);
		}
	}
	
	public void resetAnswer(int index, String text) {
		Button ans = answerButtons.get(index);
		ans.setEnabled(false);
		ans.setText("");
		
		int n = choicesButtons.size();
		
		for(int i=0; i<n; i++) {
			Button btn = choicesButtons.get(i);
			if(btn.getText().toString().compareTo(text) == 0
					&& btn.getVisibility() != View.VISIBLE) {
				btn.setVisibility(View.VISIBLE);
				choiceClicked--;
				break;
			}
		}
	}
	
	public void clearAnswer() {
		answerButtons.clear();
		choiceClicked = 0;
	}
	
	public boolean toggleClickedChoiceButton() {
		if(choiceClicked != answerButtons.size()) {
			choiceClicked++;
			return true;
		}
		return false;
	}
	
	public void addAnswerDoneListener(AnswerDoneListener listener) {
		this.answerDoneListener = listener;
	}

	public void reset() {
		int n = choicesButtons.size();
		
		for(int i=0; i<n; i++) {
			choicesButtons.get(i).setVisibility(View.VISIBLE);
		}
		choicesButtons.clear();
	}

	public void setTypeFaceForChoice(Typeface font) {
		int n = choicesButtons.size();
		for(int i=0; i<n; i++) {
			choicesButtons.get(i).setTypeface(font);
			choicesButtons.get(i).setHeight(60);
		}
	}

	public void setTypeFaceForAnswer(Typeface font) {
		int n = answerButtons.size();
		for(int i=0; i<n; i++) {
			answerButtons.get(i).setTypeface(font);
			answerButtons.get(i).setTextColor(Color.WHITE);
		}
	}
}
