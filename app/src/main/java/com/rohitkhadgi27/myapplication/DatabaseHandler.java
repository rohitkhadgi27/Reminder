package com.rohitkhadgi27.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rohit on 5/23/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "my_database";
    //Table Name
    private static final String TABLE_NAME = "my_table";

    //Table column names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_STATUS = "status";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
                + " VARCHAR, " + KEY_DETAIL + " VARCHAR, " + KEY_STATUS
                + " INTEGER " + ")";
        Log.d("rohitkhadgi27", "table=" + CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //creates table again
        onCreate(db);
    }

    public void addWork(GS data) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Log.d("check", "value== ....." + eTitle +" "+ eDetail);
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, data.getTitle());
        values.put(KEY_DETAIL, data.getDetail());
        values.put(KEY_STATUS, 0);
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); //
    }

    public ArrayList<GS> getGS(){
        ArrayList<GS> workList = new ArrayList<GS>();

        //Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_STATUS + "=0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cr.moveToFirst()) {
            do {
                GS obj = new GS();
                obj.setId(cr.getInt(0));
                obj.setTitle(cr.getString(1));
                obj.setDetail(cr.getString(2));

                //Adding to List
                workList.add(obj);
            } while (cr.moveToNext());
        }
        db.close();

        return workList;
    }

    public void removeItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] whereArgs = { String.valueOf(id) } ;
        db.delete(TABLE_NAME, KEY_ID + " =?", whereArgs);
        db.close();
    }

    public void updateIteminfo(int id, String title, String detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_DETAIL, detail);
        String [] whereArgs = { String.valueOf(id) } ;
        db.update(TABLE_NAME, cv, KEY_ID + " =?", whereArgs);
        db.close();
    }


    public void shiftItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_STATUS, 1);
        String [] whereArgs = { String.valueOf(id) };
        int test = db.update(TABLE_NAME, cv, KEY_ID + "=?", whereArgs);
       // Log.d("test", "test=" + test);
        db.close();
    }

    public ArrayList<GS> completedGS() {
        ArrayList<GS> completedList = new ArrayList<GS>();

        //select Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_STATUS + "=1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(selectQuery, null);
        if(cr.moveToFirst()){
            do{
                GS obj = new GS();
                obj.setId(cr.getInt(0));
                obj.setTitle(cr.getString(1));
                obj.setDetail(cr.getString(2));

                //Adding to arraylist
                completedList.add(obj);
            }while(cr.moveToNext());
        }
        db.close();

        return completedList;
    }
}


