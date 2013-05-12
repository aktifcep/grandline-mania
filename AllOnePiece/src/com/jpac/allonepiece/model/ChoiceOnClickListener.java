package com.jpac.allonepiece.model;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChoiceOnClickListener implements OnClickListener {

	private ButtonManager btnManager = ButtonManager.getInstance();
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(btnManager.toggleClickedChoiceButton()) {
			btnManager.setAnswer(((Button) v).getText().toString());
			v.setVisibility(View.INVISIBLE);
		}
	}

}
