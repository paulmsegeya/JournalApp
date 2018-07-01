package com.udacity.challenge.journalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.challenge.journalapp.utils.JournalDatabaseHelper;


public class MainActivity extends AppCompatActivity {

    // Get Ui Fields and Widget References

    private static final String TAG ="MainActivity";
    EditText mTitile;
    EditText mDecription;

    //Inject the StudentService class

    private JournalDatabaseHelper journalDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Retrieve Widget Settings from XML

        mTitile =(EditText) findViewById(R.id.mEditTitle);
        mDecription = (EditText) findViewById(R.id.mEditDecription);
        journalDatabaseHelper= new JournalDatabaseHelper(this);


        // instantiate Repository here on the onCreate


    }

    public void btn_click(View view) {
        // Switch Between Buttons and Add Business Logic


    }

    private void toastMessage(String  message){

        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }


    public void addData(String journalTitle,String  journalDescription){

        boolean statusFlag=journalDatabaseHelper.addData(journalTitle,journalDescription);
        String message=null;

        if (statusFlag==true){
            message="Successfully Inserted data";
            toastMessage(message);
            Log.d(TAG,message);

        }else  {

            message="Error Inserting data";
            toastMessage(message);
            Log.d(TAG,message);
        }


    }
}
