package com.udacity.challenge.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udacity.challenge.journalapp.activities.JournalListDataActivity;
import com.udacity.challenge.journalapp.utils.JournalDatabaseHelper;


public class MainActivity extends AppCompatActivity {

    // Get Ui Fields and Widget References

    private static final String TAG = "MainActivity";
    EditText mTitle;
    EditText mDescription;
    Button   mButtonAddJournal;
    Button   mButtonViewJournals;

    //Inject the StudentService class

    private JournalDatabaseHelper journalDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Retrieve Widget Settings from XML

        mTitle = (EditText) findViewById(R.id.mEditTitle);
        mDescription = (EditText) findViewById(R.id.mEditDecription);
        mButtonAddJournal=(Button) findViewById(R.id.buttonAddJournal);
        mButtonViewJournals=(Button) findViewById(R.id.buttonViewJournals);

        journalDatabaseHelper = new JournalDatabaseHelper(this);

        // Call the click lister to BUTTONS

         mButtonAddJournal.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String  title= mTitle.getText().toString();
                 String  description=mDescription.getText().toString();

                 //Some Validation

                 if (mTitle.length()!=0 || mDescription.length()!=0){

                     //Save Logic Here

                     addData(title, description);



                 }else {
                     String message="You must put something in the field";

                     toastMessage(message);
                 }



             }
         });


         mButtonViewJournals.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               // Intent to Display  Journals List

                 Intent  intent= new Intent(MainActivity.this, JournalListDataActivity.class);
                 startActivity(intent);
             }
         });





    }








    private void toastMessage(String message) {

        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }


    public void addData(String journalTitle, String journalDescription) {

        boolean statusFlag = journalDatabaseHelper.addData(journalTitle, journalDescription);
        String message = null;

        if (statusFlag == true) {
            message = "Successfully Inserted data";
            toastMessage(message);
            Log.d(TAG, message);

        } else {

            message = "Error Inserting data";
            toastMessage(message);
            Log.d(TAG, message);
        }


    }


    public void updateData() {

    }
}
