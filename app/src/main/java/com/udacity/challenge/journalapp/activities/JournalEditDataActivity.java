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

package com.udacity.challenge.journalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.udacity.challenge.journalapp.R;
import com.udacity.challenge.journalapp.utils.JournalDatabaseHelper;

public class JournalEditDataActivity  extends AppCompatActivity {


    private static  final String TAG="JournalEditDataActivity";

    private Button mButtonSave;

    private Button mButtonDelete;

    private EditText mEditJournalTitle;


    //Extras

    private String selectedJournalItem;

    private int  foundJournalID;


    private JournalDatabaseHelper journalDatabaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        journalDatabaseHelper=new JournalDatabaseHelper(this);
        mButtonDelete=(Button) findViewById(R.id.btnDelete);
        mButtonSave=(Button) findViewById(R.id.btnSave);
        mEditJournalTitle=(EditText)findViewById(R.id.editableJournal);

        Intent receivedIntent=getIntent();

        foundJournalID=receivedIntent.getIntExtra("ID",-1);
        selectedJournalItem=receivedIntent.getStringExtra("ID");


        // Implement Button Listeners

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mEditJournalTitle.getText().toString();
                if (item==null|| item.equals(" ")){
                    toastMessage("You MUST ENTER a name");
                }else  {
                    // update

                    journalDatabaseHelper.updateData(selectedJournalItem,foundJournalID,item);
                }
            }
        });


        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    journalDatabaseHelper.deleteData(foundJournalID,selectedJournalItem);

                    //Set Textto blank

                 mEditJournalTitle.setText("");
                 //toast

                toastMessage("Removed from Database");
             }
        });
     }


    private void toastMessage(String message) {

        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
}
