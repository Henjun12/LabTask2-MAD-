package com.example.dfp50293_lt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dictionary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ENGLISH_WORD = "english_word";
    private static final String COLUMN_BAHASA_TRANSLATION = "bahasa_translation";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENGLISH_WORD + " TEXT, " +
                COLUMN_BAHASA_TRANSLATION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a new word to the dictionary
    public void addWord(String englishWord, String bahasaTranslation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENGLISH_WORD, englishWord);
        values.put(COLUMN_BAHASA_TRANSLATION, bahasaTranslation);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to retrieve the Malay translation of an English word
    public String getTranslation(String englishWord) {
        String bahasaTranslation = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_BAHASA_TRANSLATION};
        String selection = COLUMN_ENGLISH_WORD + " = ?";
        String[] selectionArgs = {englishWord};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            bahasaTranslation = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BAHASA_TRANSLATION));
        }
        cursor.close();
        return bahasaTranslation;
    }
}
