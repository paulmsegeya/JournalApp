package com.udacity.challenge.journalapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.challenge.journalapp.R;
import com.udacity.challenge.journalapp.utils.JournalDatabaseHelper;

import java.util.ArrayList;

public class JournalListDataActivity extends AppCompatActivity{

    private static  final String TAG="JournalListDataActivity";

    private ListView mJournalListView;


    private JournalDatabaseHelper journalDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journals);

        // Get Database Context
        journalDatabaseHelper= new JournalDatabaseHelper(this);

        populateListView();
    }


    public void populateListView(){
        Log.d(TAG,"RETRIEVING ALL JOURNALS RECORDS FROM DATABASE:");
        Log.d(TAG,"POPULATING LIST VIEW:");

        Cursor data=journalDatabaseHelper.findAll();


        ArrayList<String>  listData=new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(2));  //Column  3 is the Journal Title
        }

        // Create a List Adapter


        final ListAdapter listAdapter=new ArrayAdapter<>(this,R.layout.activity_journals,listData);
        mJournalListView.setAdapter(listAdapter);

        // Adding oncliclLister

        mJournalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String journalTitle= parent.getItemAtPosition(position).toString();
                Log.d(TAG,"On Item Click you Click on:"+journalTitle);

                // You can put edit code here

                Cursor  data=journalDatabaseHelper.getItemID(journalTitle);

                // Fetch Data
                int itemID=-1;
                while (data.moveToNext()){
                    itemID=data.getInt(0);
                }

                if (itemID>-1){
                    Log.d(TAG,"Selected Column ID:"+itemID);

                    // Found ID now navigate to new Screen using INTENT
                    Intent intent = new Intent(JournalListDataActivity.this,JournalEditDataActivity.class);
                    intent.putExtra("ID",itemID);
                    intent.putExtra("TITLE",journalTitle);
                    startActivity(intent);


                }else  {

                     toastMessage("No Id is associated with that Journal Title");
                }

            }
        });



    }

    private void toastMessage(String message) {

        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
}
