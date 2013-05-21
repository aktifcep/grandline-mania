package com.jpac.allonepiece.model;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;

import com.jpac.allonepiece.R;
import com.jpac.allonepiece.util.Util;

public class ButtonManager2 {

	public static final ButtonManager2 buttons = new ButtonManager2();
	
	public static ButtonManager2 getInstance() {
		return buttons;
	}
	
	private Button[] layer1;
	private Button[] layer2;
	
	private ArrayList<AnswerButton> answers;

	private AnswerDoneListener answerDoneListener;
	
	private int choiceClicked = 0;
	
	private ButtonManager2() {
		layer1 = new Button[7];
		layer2 = new Button[7];
	}
	
	public void init(Activity activity, char[] choices) {
		layer1[0] = (Button) activity.findViewById(R.id.letter1);
		layer1[1] = (Button) activity.findViewById(R.id.letter2);
		layer1[2] = (Button) activity.findViewById(R.id.letter3);
		layer1[3] = (Button) activity.findViewById(R.id.letter4);
		layer1[4] = (Button) activity.findViewById(R.id.letter5);
		layer1[5] = (Button) activity.findViewById(R.id.letter6);
		layer1[6] = (Button) activity.findViewById(R.id.letter7);

		layer2[0] = (Button) activity.findViewById(R.id.letter8);
		layer2[1] = (Button) activity.findViewById(R.id.letter9);
		layer2[2] = (Button) activity.findViewById(R.id.letter10);
		layer2[3] = (Button) activity.findViewById(R.id.letter11);
		layer2[4] = (Button) activity.findViewById(R.id.letter12);
		layer2[5] = (Button) activity.findViewById(R.id.letter13);
		layer2[6] = (Button) activity.findViewById(R.id.letter14);

		Typeface font = Util.getFont(activity.getAssets(), "fonts/freshman.ttf");
		
		for(int i=0; i<7; i++) {
			layer1[i].setText(choices[i]+"");
			layer1[i].setTypeface(font);
			layer1[i].setOnClickListener(new ChoiceClickListener(1, i));
		}
		
		for(int i=7; i<14; i++) {
			layer2[i-7].setText(choices[i]+"");
			layer2[i-7].setTypeface(font);
			layer2[i-7].setOnClickListener(new ChoiceClickListener(2, i-7));
		}
		
		if(answers == null) {
			answers = new ArrayList<ButtonManager2.AnswerButton>();
		}
		answers.clear();
		
		choiceClicked = 0;
	}
	
	private void choiceSelected(int layer, int index) {
		if(choiceClicked != answers.size()) {
			if(layer == 1) {
				layer1[index].setVisibility(View.INVISIBLE);
				addAnswer(layer, index, layer1[index].getText().toString());
			} else {
				layer2[index].setVisibility(View.INVISIBLE);
				addAnswer(layer, index, layer2[index].getText().toString());
			}
			choiceClicked++;
			checkForDone();
		}
	}
	
	private void checkForDone() {
		if(choiceClicked == answers.size()) {
			String sequence = "";
			int n = answers.size();
			for(int i=0; i<n; i++) {
				sequence += answers.get(i).getText();
			}
			answerDoneListener.onAnswerComplete(sequence);
		}
	}

	private void choiceReset(int layer, int index) {
		if(layer == 1) {
			layer1[index].setVisibility(View.VISIBLE);
		} else {
			layer2[index].setVisibility(View.VISIBLE);
		}
		choiceClicked--;
	}
	
	private void answerSelected(Button btn) {
		int n = answers.size();
		
		for(int i=0; i<n; i++) {
			AnswerButton ans = answers.get(i);
			if(ans.getText() == btn.getText()) {
				ans.disable();
				ans.setText("");
				choiceReset(ans.getLayer(), ans.getIndex());
				break;
			}
		}
	}
	
	public void addAnswerButton(Button btn) {
		btn.setOnClickListener(new AnswerClickListener());
		answers.add(new AnswerButton(btn));
	}
	
	private void addAnswer(int layer, int index, String text) {
		int n = answers.size();
		for(int i=0; i<n; i++) {
			AnswerButton btn = answers.get(i);
			if(btn.getText().compareTo("") == 0) {
				btn.setLayer(layer);
				btn.setIndex(index);
				btn.setText(text);
				btn.enable();
				break;
			}
		}
	}

	public void addAnswerDoneListener(AnswerDoneListener listener) {
		this.answerDoneListener = listener;
	}


	protected class ChoiceClickListener implements View.OnClickListener {

		private int layer = 0;
		
		private int index = -1;
		
		public ChoiceClickListener(int layer, int index) {
			this.layer = layer;
			this.index = index;
		}
		
		@Override
		public void onClick(View v) {
			ButtonManager2.this.choiceSelected(layer, index);
		}
		
	}
	
	protected class AnswerClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			ButtonManager2.this.answerSelected((Button) v);
		}
				
	}
	
	protected class AnswerButton {
		
		private Button btn;
		
		private int layer;
		
		private int index;
		
		private int id;
		
		public AnswerButton(Button btn) {
			this.btn = btn;
			this.id = btn.getId();
		}
		
		public int getLayer() {
			return layer;
		}
		
		public int getIndex() {
			return index;
		}
		
		public int getID() {
			return id;
		}
		
		public void setLayer(int layer) {
			this.layer = layer;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		public void disable() {
			this.btn.setEnabled(false);
		}
		
		public void enable() {
			this.btn.setEnabled(true);
		}
		
		public String getText() {
			return this.btn.getText().toString();
		}
		
		public void setText(String text) {
			this.btn.setText(text);
		}
	}
}
