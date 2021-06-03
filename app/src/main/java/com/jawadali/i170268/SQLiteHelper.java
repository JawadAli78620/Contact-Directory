package com.jawadali.i170268;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ContactDirectory.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + PhoneDirectoryContract.ContactEntry.TABLE_NAME + " (" +
                    PhoneDirectoryContract.ContactEntry._ID + " INTEGER PRIMARY KEY," +
                    PhoneDirectoryContract.ContactEntry.NAME_COL + " TEXT," +
                    PhoneDirectoryContract.ContactEntry.PHONE_COL + " TEXT,"+
                    PhoneDirectoryContract.ContactEntry.ADDRESS_COL + "TEXT,"+
                    PhoneDirectoryContract.ContactEntry.EMAIL_COL + "TEXT,"+
                    PhoneDirectoryContract.ContactEntry.IMAGE_URI_COL + "TEXT)";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertContact(ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();

        long rowID = db.insert(PhoneDirectoryContract.ContactEntry.TABLE_NAME, null, values);
        if(rowID == -1 ){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + PhoneDirectoryContract.ContactEntry.TABLE_NAME, null);
        return result;
    }
}
