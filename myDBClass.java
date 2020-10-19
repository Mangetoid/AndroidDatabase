package com.example.androiddatabaseagiannn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class myDBClass extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mydatabase2";
    // Table Name
    private static final String TABLE_MEMBER = "members";
    public myDBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//Create Table
        db.execSQL("CREATE TABLE " + TABLE_MEMBER +
                "(MemberID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT(100)," +
                " Tel TEXT(100));");
    }
    public long InsertData(String strName, String strTel) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); // Write Data
            ContentValues Val = new ContentValues();
            Val.put("Name", strName);
            Val.put("Tel", strTel);
            long rows = db.insert(TABLE_MEMBER, null, Val);
            db.close();
            return rows;
        } catch (Exception e) {
            return -1;
        }
    }
    public String[] SelectData(String strMemberID) {
        try {
            String arrData[] = null;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" },
                    "MemberID=?",
                    new String[] { String.valueOf(strMemberID) }, null, null, null, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                }
            }
            cursor.close();
            db.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    public String[] SelectAllData() {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT * FROM " + TABLE_MEMBER;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0)
                                + " - " + cursor.getString(1)
                                + " - " + cursor.getString(2);;
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    public long UpdateData(String strMemberID,String strName,String strTel) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data
            ContentValues Val = new ContentValues();
            Val.put("Name", strName);
            Val.put("Tel", strTel);
            long rows = db.update(TABLE_MEMBER, Val, " MemberID = ?",
                    new String[] { String.valueOf(strMemberID) });
            db.close();
            return rows; // return rows updated.
        } catch (Exception e) {
            return -1;
        }
    }
    public long DeleteData(String strMemberID) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); // Write Data
            long rows = db.delete(TABLE_MEMBER," MemberID = ?",
                    new String[] { String.valueOf(strMemberID) });
            db.close();
            return rows; // return rows updated.
        } catch (Exception e) {
            return -1;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}