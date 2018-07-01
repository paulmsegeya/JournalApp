/*
 *
 * Developer :   Paul Msegeya
 *
 * Email : paul.msegeya@gmail.com
 *
 * Cell  : +255756734448
 *
 * Country:  Tanzania
 *
 * Track :  Android Developer Track
 *
 *
 * Copyright (c) 2018
 */

package com.udacity.challenge.journalapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class JournalDatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "JournalDatabaseHelper";

    // define table name
    private static final String TABLE_NAME = "JOURNALS";

    //Define  Table Columns
    private static final String ID_COLUMN = "ID";
    private static final String TITLE_COLUMN = "TITLE";
    private static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    private static final String CREATEDAT_COLUMN = "CREATEDAT";
    private static final String SELECT_ALL_JOURNALS_COMMAND="SELECT * FROM  JOURNALS";

    // define SQL Commands

    private static final String CREATE_TABLE_COMMAND =
            "CREATE TABLE  JOURNALS ( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TITLE TEXT UNIQUE," +
                    "DESCRIPTION TEXT ," +
                    "CREATEDAT DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String DROP_TABLE_COMMAND="DROP  TABLE IF EXISTS JOURNALS;";


    public JournalDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COMMAND);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_COMMAND);
        onCreate(db);

    }


    public boolean addData(String journalTitle, String journalDescription ){

        long dbProcessingResult=0;
        boolean statusFlag=false;
        // get sql lite context

        SQLiteDatabase  db= this.getWritableDatabase();
        ContentValues  contentValues= new ContentValues();
        contentValues.put(TITLE_COLUMN,journalTitle);
        contentValues.put(DESCRIPTION_COLUMN,journalDescription);

        String sql =" INSERT INTO JOURNALS (TITLE, DESCRIPTION) VALUES ("+journalTitle+","+journalDescription+");";
        db.execSQL(sql);

        statusFlag=true;



        return  statusFlag;
    }



    public boolean deleteData(int  id , String journalTitle){

        long dbProcessingResult=0;
        boolean statusFlag=false;

        // get sql lite context

        SQLiteDatabase  db= this.getWritableDatabase();

        String sql="DELETE FROM JOURNALS WHERE TITLE = "+journalTitle+ " AND ID ="+id;
        Log.d(TAG,"DELETE JOURNALS WITH TITLE :"+journalTitle);

        db.execSQL(sql);
        statusFlag=true;

        return  statusFlag;
    }



    public boolean updateData(String oldItem ,int id, String newItem){

        long dbProcessingResult=0;
        boolean statusFlag=false;
        SQLiteDatabase  db= this.getWritableDatabase();

        String updateSql="UPDATE JOURNALS  SET  TITLE ="+newItem+" WHERE ID="+id+"  AND TITLE ="+oldItem;
        Log.d(TAG,"UPDATING JOURNALS");

        db.execSQL(updateSql);

        statusFlag=true;

        return  statusFlag;
    }


     public Cursor findAll(){

         // get sql lite context

         SQLiteDatabase  db= this.getWritableDatabase();
         Log.d(TAG,"RETRIEVING ALL JOURNALS RECORDS FROM DATABASE:");

         Cursor data=db.rawQuery(SELECT_ALL_JOURNALS_COMMAND,null);

         return data;
    }


    public Cursor getItemID(String journalTitle){
        SQLiteDatabase  db= this.getWritableDatabase();
        Log.d(TAG,"RETRIEVING ID FOR JOURNA TITLE :");
        String sql="SELECT ID FROM JOURNALS WHERE  TITLE="+journalTitle;

        Cursor data=db.rawQuery(sql,null);

        return  data;

    }

}
