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

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;
import android.widget.TextView;

interface IGenericDatabaseHelper {
    Cursor getCursor(SQLiteDatabase sqLiteDatabase, String tableName, String[] tableColumnsToReturn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit, CancellationSignal cancellationSignal);

    String getColumnName(Cursor cursor);

    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, String deleteSQLCommand);

    void createTable(SQLiteDatabase sqLiteDatabase, String createTableSQLCommand);

    void insertTableColumn(String tablleName, String[] tableColumnkeys, String[] tableColumnValues);

    void updateTableColumn(String[] updateColumns, String updateSQLCommand);

    void deleteTableColumn(String[] columnsToDelete, String deleteSQLCommand);

    void findAll(TextView textView, String selectAllSQLCommand);
}
