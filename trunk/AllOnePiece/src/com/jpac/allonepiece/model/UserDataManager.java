package com.jpac.allonepiece.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;

public class UserDataManager {

	private static UserDataManager udm = new UserDataManager();
	
	private UserDataManager() { }
	
	public static UserDataManager getInstance() {
		return udm;
	}
	
	private final String filename = "game.sav";
	
	public UserData getData(Context context) {
		UserData data = new UserData();
		String content = "";
		
		try {
			FileInputStream fis = context.openFileInput(filename);
			
			if(fis != null) {
				byte[] input = new byte[fis.available()];
				while(fis.read(input) != -1) {
					content += new String(input);
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	}
}
