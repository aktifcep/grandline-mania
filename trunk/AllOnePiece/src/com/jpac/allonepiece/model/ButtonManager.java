package com.jpac.allonepiece.model;

import java.util.ArrayList;

import android.util.Log;
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
	}
	
	public void resetAnswer(int index, String text) {
		Button ans = answerButtons.get(index);
		ans.setEnabled(false);
		ans.setText("");
		
		Log.v("jpac", "Answer Text: " + index + ":" + text);
		
		int n = choicesButtons.size();
		
		for(int i=0; i<n; i++) {
			Button btn = choicesButtons.get(i);
			Log.v("jpac", "Comparing... [" + text + "," + btn.getText() + "]");
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
}
