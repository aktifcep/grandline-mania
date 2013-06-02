package com.jpac.glm.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class UserDataManager {

	private static UserDataManager udm = new UserDataManager();
	
	private UserDataManager() { }
	
	public static UserDataManager getInstance() {
		return udm;
	}
	
	private final String filename = "game.sav";
	
	public void saveData(Context context, UserData data) {
		Log.v("jpac", "Saving File...");
		String content = data.getQID() + ":" + data.getGold();

		Log.v("jpac", content);
		
		try {
			FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UserData getData(Context context) {
		Log.v("jpac", "Loading File...");
		UserData data = new UserData();
		String content = "";
		
		try {
			FileInputStream fis = context.openFileInput(filename);
			
			if(fis != null) {
				byte[] input = new byte[fis.available()];
				while(fis.read(input) != -1) {
					content += new String(input);
				}
				fis.close();
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.v("jpac", content);
		
		if(content.length() > 0) {
			String[] metadata = content.split(":");
			if(metadata.length == 2) {
				data.setQID(Integer.valueOf(metadata[0]));
				data.setGold(Integer.valueOf(metadata[1]));
			}
		}
		
		return data;
	}
	
	public class UserData {
		
		private int qid;
		
		private int gold;
		
		public UserData() {
			qid = -1;
			gold = 50;
		}
		
		public void setQID(int qid) {
			this.qid = qid;
		}
		
		public void setGold(int gold) {
			this.gold = gold;
		}
		
		public int getQID() {
			return this.qid;
		}
		
		public int getGold() {
			return this.gold;
		}
		
		@Override
		public String toString() {
			return "[qid=" + this.qid + ",gold=" + this.gold + "]";
		}
	}
}
