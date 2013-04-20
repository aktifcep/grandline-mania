package com.jpac.allonepiece.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "answersPool";
	
	private static final String TABLE_ANSWERS = "answers";
	
	private static final String KEY_ID = "id";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_ANSWER = "answer";
	private static final String KEY_SEED = "seed";
	private static final String KEY_ANSWERED = "answered";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ANSWER_TABLE = "CREATE TABLE " + TABLE_ANSWERS
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_ANSWER + " TEXT," + KEY_CATEGORY + " TEXT,"
				+ KEY_SEED + " TEXT," + KEY_ANSWERED + " INTEGER)";
		db.execSQL(CREATE_ANSWER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
		onCreate(db);
	}

	public void addQuestion(QuestionBundle qb) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_ID, qb.getID());
		values.put(KEY_ANSWER, qb.getAnswer());
		values.put(KEY_CATEGORY, qb.getCategory().getName());
		values.put(KEY_SEED, Long.toString(qb.getRandomLetterSeed()));
		values.put(KEY_ANSWERED, 0);
		
		db.insert(TABLE_ANSWERS, null, values);
		db.close();
	}
}
