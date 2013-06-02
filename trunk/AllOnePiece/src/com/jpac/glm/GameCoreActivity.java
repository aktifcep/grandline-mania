package com.jpac.glm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameCoreActivity extends Activity {

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(this.toString(), "onDestroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(this.toString(), "onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(this.toString(), "onRestart");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.v(this.toString(), "onRestoreInstanceState");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(this.toString(), "onResume");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.v(this.toString(), "onSaveInstanceState");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v(this.toString(), "onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v(this.toString(), "onStop");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(this.toString(), "onCreate");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

	}

}
