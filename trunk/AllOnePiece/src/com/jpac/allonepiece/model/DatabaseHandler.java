package com.jpac.allonepiece.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
		String CREATE_ANSWER_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ANSWER + " TEXT,"
				+ KEY_CATEGORY + " TEXT," + KEY_SEED + " TEXT," + KEY_ANSWERED
				+ " INTEGER)";
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
		values.put(KEY_ANSWERED, qb.isAnswered() ? 1 : 0);

		Log.v("jpac","Added Question");
		db.insert(TABLE_ANSWERS, null, values);
		db.close();
	}

	public QuestionBundle getQuestion(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ANSWERS, new String[] { KEY_ID,
				KEY_ANSWER, KEY_CATEGORY, KEY_SEED, KEY_ANSWERED }, KEY_ID
				+ "=?", new String[] { String.valueOf(id) }, null, null, null,
				null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		QuestionBundle qb = new QuestionBundle();
		qb.setID(Integer.parseInt(cursor.getString(0)));
		qb.setAnswer(cursor.getString(1));
		qb.setCategory(Category.valueOf(cursor.getString(2)));
		qb.setRandomLetterSeed(Long.valueOf(cursor.getString(3)));
		qb.setAnswered(Integer.valueOf(cursor.getString(4)) == 0 ? false : true);
		
		return qb;
	}
	
	public List<QuestionBundle> getAllQuestions() {
		List<QuestionBundle> questionList = new ArrayList<QuestionBundle>();
		
		String query = "SELECT * FROM " + TABLE_ANSWERS;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()) {
			do {
				QuestionBundle qb = new QuestionBundle();
				qb.setID(Integer.valueOf(cursor.getString(0)));
				qb.setAnswer(cursor.getString(1));
				qb.setCategory(Category.valueOf(cursor.getString(2)));
				qb.setRandomLetterSeed(Long.valueOf(cursor.getString(3)));
				qb.setAnswered(Integer.valueOf(cursor.getString(4)) == 0 ? false : true);
			} while(cursor.moveToNext());
		}
		
		return questionList;
	}
	
	public int getQuestionsCount() {
		String countQuery = "SELECT * FROM " + TABLE_ANSWERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	
	public int updateQuestion(int id, boolean answered) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_ANSWERED, answered ? 1 : 0);
		
		return db.update(TABLE_ANSWERS, values, KEY_ID + "=?",
				new String[] {String.valueOf(id)});
	}
}
