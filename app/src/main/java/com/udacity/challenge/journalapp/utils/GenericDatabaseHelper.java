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
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.TextView;


/**
 * THIS IS A GENERIC DATABASE HELPER CLASS THAT CAN HANDLE GENERIC CRUD OPERATIONS
 * INDEPENDENT OF TABABASE COLUMNS  IMPLEMENTATIONS, IT ACCEPTS PARAMETARIZED VARIABLES
 * DEPENDING ON THE USECASE , IT HELPS ON NOT REPEATING THE SAME CODEBASE OVER AND OVER
 * ON DIFFERENT PLACES OF THE APPLICATION
 *
 *
 * Written  : By Paul Msegeya
 */
public class GenericDatabaseHelper extends SQLiteOpenHelper implements IGenericDatabaseHelper {


    // This constructor can be used if You would  like to use some other methods in this class that are not in the SQLiteOpenHelper class
    public GenericDatabaseHelper() {
        super(null, null, null, 1);
    }

    public GenericDatabaseHelper(Context context, String name) {
        super(context, name, null, 1);
    }


    public GenericDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


// Abstracting Database Cursor By making it as generic as possible for Re - Usability using clean coding principles


    @Override
    public Cursor getCursor(SQLiteDatabase sqLiteDatabase, String tableName, String[] tableColumnsToReturn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit, CancellationSignal cancellationSignal) {

        // This for defencive code practises  the values will be updated later by referring to the nulls set now
        Cursor returnedCursor = null;
        String[] asColumnReturned = null;


        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(tableName);

        // Update the returnedCursor Instance

        if ((returnedCursor == null) == true) { // only when it is null then we update it and return


            // get column names from the passed parameter
            if (asColumnReturned == null) {

                // Setting the projections
                asColumnReturned = tableColumnsToReturn;
            }

            //Check android versions compatibility

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                // Do something for lollipop and above versions

                returnedCursor = sqLiteQueryBuilder.query(sqLiteDatabase, asColumnReturned, selection, selectionArgs, groupBy, having, sortOrder, limit, cancellationSignal);

            } else {
                // do something for phones running an SDK before lollipop
                returnedCursor = sqLiteQueryBuilder.query(sqLiteDatabase, asColumnReturned, selection, selectionArgs, groupBy, having, sortOrder, limit);

            }

        }


        return returnedCursor;
    }


    @Override
    public String getColumnName(Cursor cursor) {

        return cursor.getString(1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, String deleteSQLCommand) {
        // On application Upgrade Delete Old Tables
        db.execSQL(deleteSQLCommand);
        // After Deleting Old tables create new Ones by Calling onCreate passing in the database param
        onCreate(db);

    }


    @Override
    public void createTable(SQLiteDatabase sqLiteDatabase, String createTableSQLCommand) {

        sqLiteDatabase.execSQL(createTableSQLCommand);
    }


    @Override
    public void insertTableColumn(String tablleName, String[] tableColumnkeys, String[] tableColumnValues) {

        //Instantiate ContentValues

        // ContentValues
        ContentValues contentValues = new ContentValues();

        // For each table Column  put table column in the contentValues


        for (String tableColumnValue : tableColumnValues
                ) {


            for (String tableColumnKey : tableColumnkeys
                    ) {
                contentValues.put(tableColumnKey, tableColumnValue);
            }

        }
        this.getReadableDatabase().insertOrThrow(tablleName, "", contentValues);

    }

    @Override
    public void updateTableColumn(String[] updateColumns, String updateSQLCommand) {

        // Compile the statement
        SQLiteStatement statement = this.getReadableDatabase().compileStatement(updateSQLCommand);


        // Bind values


        for (int i = 0; i < updateColumns.length; i++) {
            statement.bindString(i, updateColumns[i]);
        }

        // integer number of rows affected
        int numberOfRowsAffected = statement.executeUpdateDelete();


        if (numberOfRowsAffected == 1) {
            //log Success
            Log.d("", "NUMBER OF ROWS AFFECTED,SUCCESSFULLY UPDATED "+String.valueOf(numberOfRowsAffected));

        } else {
            //Log Failure
            Log.d("", "NUMBER OF ROWS AFFECTED,FAILED UPDATED"+String.valueOf(numberOfRowsAffected));
        }

    }


    @Override
    public void deleteTableColumn(String[] columnsToDelete, String deleteSQLCommand) {

        // Compile the statement
        SQLiteStatement statement = this.getReadableDatabase().compileStatement(deleteSQLCommand);
        //Bind Values

        for (int i = 0; i < columnsToDelete.length; i++) {
            statement.bindString(i, columnsToDelete[i]);

        }

        // integer number of rows affected
        int numberOfRowsAffected = statement.executeUpdateDelete();

        if (numberOfRowsAffected == 1) {
            //log Success
            Log.d("", "NUMBER OF ROWS AFFECTED,SUCCESSFULLY DELETED"+String.valueOf(numberOfRowsAffected));

        } else {
            //Log Failure
            Log.d("", "NUMBER OF ROWS AFFECTED,FAILED DELETED"+String.valueOf(numberOfRowsAffected));
        }


    }


    @Override
    public void findAll(TextView textView, String selectAllSQLCommand) {


        Cursor cursor = this.getReadableDatabase().rawQuery(selectAllSQLCommand, null);

        // Loop through the foundRecords and append them to the text View

        int foundRecords = cursor.getColumnCount();

        while (cursor.moveToNext()) {

            String returnedStringToView;
            String returnedBeforeTheEnd = "";
            String returnedAtTheEnd = "";


            for (int i = 0; i < foundRecords; i++) {

                // Detect the last records and create a return string to the text view with newline appended to it

                if (i == foundRecords - 1) {

                    returnedAtTheEnd = cursor.getString(i).concat("\n");


                } else {
                    returnedBeforeTheEnd = cursor.getString(i).concat(" ");

                }


            }
            //keep Concatenating tabase columns if there is more
            returnedBeforeTheEnd += returnedBeforeTheEnd;

            returnedStringToView = returnedBeforeTheEnd.concat(returnedAtTheEnd);

            // Adding the returned String in the textView for displaying in the app

            textView.setText(returnedStringToView);


        }

        // Free the Opened Cursor
        cursor.close();
    }


}
