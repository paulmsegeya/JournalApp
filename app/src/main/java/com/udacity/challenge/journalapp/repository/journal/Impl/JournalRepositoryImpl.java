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

package com.udacity.challenge.journalapp.repository.journal.Impl;

import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.udacity.challenge.journalapp.repository.journal.Impl.JournalRepository;

public class JournalRepositoryImpl implements JournalRepository{


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void add(String title, String content) {

    }

    @Override
    public void update(String oldItem, String newItem) {

    }

    @Override
    public void delete(String firstname) {

    }

    @Override
    public void listAll(TextView textView) {

    }
}
