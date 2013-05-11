package com.jpac.allonepiece.model;

import java.util.ArrayList;

import android.view.View;
import android.widget.Button;

public class ButtonManager {

	private static final ButtonManager buttons = new ButtonManager();
	
	public static ButtonManager getInstance() {
		return buttons;
	}
	
	private ArrayList<Button> answerButtons;
	
	private ArrayList<Button> choicesButtons;
	
	private ButtonManager() {
		answerButtons = new ArrayList<Button>();
		choicesButtons = new ArrayList<Button>();
	}
	
	public void addChoiceButton(Button button) {
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
	
	public void setAnswer(String text) {
		int n = answerButtons.size();
		for(int i=0; i<n; i++) {
			Button btn = answerButtons.get(i);
			if(btn.getText().length() == 0) {
				btn.setText(text);
			}
		}
	}
}
