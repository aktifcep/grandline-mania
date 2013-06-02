package com.jpac.glm.model;

import android.graphics.drawable.BitmapDrawable;

public class QuestionBundle {

	private BitmapDrawable image;
	
	private Category category;
	
	private String answer;
	
	private int id;
	
	private long randomLetterSeed;

	private boolean answered;
	
	public QuestionBundle() { }
	
	public QuestionBundle(QuestionBundle qb) {
		this.image = qb.image;
		this.category = qb.category;
		this.answer = qb.answer;
		this.id = qb.id;
		this.randomLetterSeed = qb.randomLetterSeed;
		this.answered = qb.answered;
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
	
	public boolean isAnswered() {
		return this.answered;
	}

	public void setImage(BitmapDrawable image) {
		this.image = image;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setRandomLetterSeed(long randomLetterSeed) {
		this.randomLetterSeed = randomLetterSeed;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	public QuestionBundle clone() {
		return new QuestionBundle(this);
	}
}
