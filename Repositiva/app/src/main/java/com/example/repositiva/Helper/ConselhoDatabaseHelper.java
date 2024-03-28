package com.example.repositiva.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.repositiva.Conselho;

import java.util.ArrayList;
import java.util.List;

public class ConselhoDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ConselhoDB";
    private static final String TABLE_CONSELHOS = "conselhos";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXTO_CONSELHO = "texto_conselho";

    public ConselhoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONSELHOS_TABLE = "CREATE TABLE " + TABLE_CONSELHOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXTO_CONSELHO + " TEXT)";
        db.execSQL(CREATE_CONSELHOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSELHOS);
        onCreate(db);
    }

    public void adicionarConselho(Conselho conselho) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEXTO_CONSELHO, conselho.getTextoConselho()); // Use o método correto para obter o texto do conselho
        db.insert(TABLE_CONSELHOS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Conselho> getAllConselhos() {
        List<Conselho> conselhosList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONSELHOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Conselho conselho = new Conselho();
                conselho.setTextoConselho(cursor.getString(cursor.getColumnIndex(KEY_TEXTO_CONSELHO)));
                conselhosList.add(conselho);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return conselhosList;
    }
}
