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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class JournalDatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "JournalDatabaseHelper";

    // define table name
    private static final String TABLE_NAME = "JOURNALS";

    //Define  Table Columns
    private static final String ID_COLUMN = "ID";
    private static final String TITLE_COLUMN = "TILE";
    private static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    private static final String CREATEDAT_COLUMN = "CREATEDAT";

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
        Log.d(TAG,"About to add Data to the Journal   DATA >>  TITLE :"+journalTitle + " DESCRIPTION :"+journalDescription);

        if (dbProcessingResult==0){
            dbProcessingResult=db.insert(TABLE_NAME,null,contentValues);

            if (statusFlag=false){

                if (dbProcessingResult>=0){
                    Log.d(TAG,"INSERT SUCCESSFULLY  DATA >>  TITLE :"+journalTitle + " DESCRIPTION :"+journalDescription+"RESULT : "+dbProcessingResult);


                }else if (dbProcessingResult==-1){
                    Log.d(TAG,"INSERT FAILED  DATA >>  TITLE :"+journalTitle + " DESCRIPTION :"+journalDescription+"RESULT : "+dbProcessingResult);

                }
            }

        }

        return  statusFlag;
    }



    public boolean deleteData(String journalTitle){

        long dbProcessingResult=0;
        boolean statusFlag=false;

        // get sql lite context

        SQLiteDatabase  db= this.getWritableDatabase();
        ContentValues  contentValues= new ContentValues();
        contentValues.put(TITLE_COLUMN,journalTitle);

        Log.d(TAG,"About to Delete Data to the Journal   DATA >>  TITLE :"+journalTitle);



        if (dbProcessingResult==0){
           // dbProcessingResult=db.insert(TABLE_NAME,null,contentValues);

            dbProcessingResult=db.delete(TABLE_NAME,null, null);

            if (statusFlag=false){

                if (dbProcessingResult>=0){
                    Log.d(TAG,"DELETED SUCCESSFULLY  DATA >>  TITLE :"+journalTitle +"RESULT : "+dbProcessingResult);


                }else if (dbProcessingResult==-1){
                    Log.d(TAG,"DELETE  FAILED  DATA >>  TITLE :"+journalTitle + " DESCRIPTION :"+dbProcessingResult);

                }
            }

        }

        return  statusFlag;
    }

    public boolean updateData(String journalTitle , String newJournalTitle){

        long dbProcessingResult=0;
        boolean statusFlag=false;

        // get sql lite context

        SQLiteDatabase  db= this.getWritableDatabase();
        ContentValues  contentValues= new ContentValues();
        contentValues.put(TITLE_COLUMN,journalTitle);

        Log.d(TAG,"About to Delete Data to the Journal   DATA >>  TITLE :"+journalTitle);



        if (dbProcessingResult==0){
            // dbProcessingResult=db.insert(TABLE_NAME,null,contentValues);

            dbProcessingResult=db.delete(TABLE_NAME,null, null);

            if (statusFlag=false){

                if (dbProcessingResult>=0){
                    Log.d(TAG,"DELETED SUCCESSFULLY  DATA >>  TITLE :"+journalTitle +"RESULT : "+dbProcessingResult);


                }else if (dbProcessingResult==-1){
                    Log.d(TAG,"DELETE  FAILED  DATA >>  TITLE :"+journalTitle + " DESCRIPTION :"+dbProcessingResult);

                }
            }

        }

        return  statusFlag;
    }

}
