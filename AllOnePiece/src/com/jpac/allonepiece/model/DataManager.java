package com.jpac.allonepiece.model;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.util.Log;


public class DataManager {

	private DataManager() { }
	
	public static void loadData(DatabaseHandler handler, Context context) {
		Log.v("jpac","Loading Data");
		int n = handler.getQuestionsCount();
		
		String content = "";
		
		try {
			InputStream is = context.getAssets().open("qa.glm");
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			content = new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] raw = content.split("\n");
		int rawCount = raw.length;
		
		if(n != rawCount) {
			for(int i=n; i<rawCount; i++) {
				String[] line = raw[i].split(";");
				
				QuestionBundle qb = new QuestionBundle();
				qb.setID(i);
				qb.setAnswer(line[0].trim());
				qb.setCategory(Category.valueOf(line[1].trim()));
				// some random whitespace bullshit >:|
				qb.setRandomLetterSeed(i);
				qb.setAnswered(false);
				
				Log.v("jpac","Question Created");
				Log.v("jpac","Answer="+line[0].trim()+";Category="+line[1].trim()+";"+qb.isAnswered());
				handler.addQuestion(qb);
			}
		}
	}
}
