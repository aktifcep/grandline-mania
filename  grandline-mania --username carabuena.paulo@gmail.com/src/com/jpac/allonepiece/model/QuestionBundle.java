package com.jpac.allonepiece.model;

import android.graphics.drawable.BitmapDrawable;

public class QuestionBundle {

	private BitmapDrawable image;
	
	private Category category;
	
	private String answer;
	
	private int id;
	
	private long randomLetterSeed;

	public QuestionBundle(int id, long seed, Category category, String answer) {
		this.category = category;
		this.answer = answer;
		this.id = id;
		this.randomLetterSeed = seed;
	}
	
	public BitmapDrawable getImage() {
		return this.image;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public int getID() {
		return this.id;
	}
	
	public long getRandomLetterSeed() {
		return this.randomLetterSeed;
	}
}
