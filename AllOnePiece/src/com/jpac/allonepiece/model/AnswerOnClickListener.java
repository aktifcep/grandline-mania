package com.jpac.allonepiece.model;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AnswerOnClickListener implements OnClickListener {

	private int index = -1;
	
	private ButtonManager btnManager = ButtonManager.getInstance();

	public AnswerOnClickListener(int index) {
		Log.v("jpac", "Index: "+index);
		this.index = index;
	}
	
	@Override
	public void onClick(View v) {
		btnManager.resetAnswer(index, ((Button) v).getText().toString());
	}

	public int getIndex() {
		return this.index;
	}
}
