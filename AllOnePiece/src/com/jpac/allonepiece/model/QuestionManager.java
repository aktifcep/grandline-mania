package com.jpac.allonepiece.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;

public class QuestionManager {

	private static QuestionManager qm = new QuestionManager();
	
	private QuestionManager() {
	}

	public static QuestionManager getInstance() {
		return qm;
	}

	private DatabaseHandler handler;
	
	private Map<Integer, QuestionBundle> questions;
	private Map<Integer, QuestionBundle> answered;

	@SuppressLint("UseSparseArrays")
	public void init(Context context) {
		if (handler == null) {
			handler = new DatabaseHandler(context);
		}
		
		DataManager.loadData(handler);
		
		questions = new HashMap<Integer, QuestionBundle>();
		answered = new HashMap<Integer, QuestionBundle>();
		
		List<QuestionBundle> qbs = handler.getAllQuestions();
		Iterator<QuestionBundle> iqbs = qbs.iterator();
		while(iqbs.hasNext()) {
			QuestionBundle qb = iqbs.next();
			questions.put(qb.getID(), qb);
			if(qb.isAnswered()) {
				answered.put(qb.getID(), qb);
			}
		}
	}
	
	public QuestionBundle getNextQuestion(QuestionBundle previous) {
		if(previous != null) {
			handler.updateQuestion(previous.getID(), true);
			answered.put(previous.getID(), previous);
		}
		
		if(answered.size() == questions.size()) {
			return null;
		}
		
		int idx = 0;
		do {
			idx = getRandomNumber();
		} while(answered.containsKey(idx));
		
		return handler.getQuestion(idx);
	}
	
	private Random random = new Random(System.currentTimeMillis());
	
	private int getRandomNumber() {
		return random.nextInt(questions.size());
	}
}
