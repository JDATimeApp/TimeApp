package com.example.timeapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DDBB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "timeapp.db";
    private static final int DB_VERSION = 1;
    public DDBB(Context c){
        super(c,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBDesign.USER_CREATE_TABLE);
        db.execSQL(DBDesign.ENTRY_CREATE_TABLE);
        db.execSQL(DBDesign.SCHEDULE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL(DBDesign.DROP_TABLE);
            db.execSQL(DBDesign.DROP_TABLE_ENTRY);
            db.execSQL(DBDesign.DROP_TABLE_SCHEDULE);
            onCreate(db);
        }
    }
}