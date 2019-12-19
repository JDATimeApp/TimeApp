package com.example.timeapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DDBB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TimeApp.db";
    public static final int DB_VERSION = 1;
    public DDBB(Context c){
        super(c,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBDesign.USER_CREATE_TABLE);
        db.execSQL(DBDesign.ENTRY_CREATE_TABLE);
        db.execSQL(DBDesign.SCHEDULE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBDesign.DROP_TABLE);
        db.execSQL(DBDesign.DROP_TABLE_ENTRY);
        db.execSQL(DBDesign.DROP_TABLE_SHEDULE);
        onCreate(db);
    }
}
