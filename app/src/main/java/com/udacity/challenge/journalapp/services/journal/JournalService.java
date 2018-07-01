/*
 *
 * Developer :  Paul Msegeya
 *
 * Email : paul.msegeya@gmail.com
 *
 * Country : Tanzania
 *
 * Copyright (c) 2018.  |  Open Source
 */

package com.udacity.challenge.journalapp.services.journal;

import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;


public interface JournalService {

    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    void add(String title, String content);

    void update(String oldItem, String newItem);

    void delete(String item);

    void listAll(TextView textView);
}
