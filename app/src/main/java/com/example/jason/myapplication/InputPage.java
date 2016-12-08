package com.example.jason.myapplication;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Input a Time");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AboutPage.class);
            startActivity(intent);
        }

        if(id == R.id.home_settings)
        {
            Intent intent = new Intent(this, MainActivity.class); //Button to bring app back to homescreen
            startActivity(intent);
        }

        if(id == R.id.search_page)
        {
            Intent intent = new Intent(this, newWindow1.class);
            startActivity(intent);
        }

        if(id == R.id.menu_input)
        {
            Bundle extras = getIntent().getExtras();
            String keyName2 = extras.getString("key");
            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName2);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void action_submit(View view)
    {

        Bundle extras = getIntent().getExtras();
        final String keyName = extras.getString("key");

        Firebase ref = new Firebase("https://waitlistapplication.firebaseio.com/");
        Firebase vref = ref.child(keyName).child("log");


        RadioGroup radioGroup;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        RadioButton button0, button1, button2, button3, button4, button5;
        button0 = (RadioButton) findViewById(R.id.radioButton0);
        button1 = (RadioButton) findViewById(R.id.radioButton1);
        button2 = (RadioButton) findViewById(R.id.radioButton2);
        button3 = (RadioButton) findViewById(R.id.radioButton3);
        button4 = (RadioButton) findViewById(R.id.radioButton4);
        button5 = (RadioButton) findViewById(R.id.radioButton5);


        if(button0.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 0);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum/number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);

        }

        if(button1.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 1);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum / number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);
        }

        if(button2.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 2);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum / number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);
        }

        if(button3.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 3);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum / number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);
        }

        if(button4.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 4);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum / number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);
        }

        if(button5.isChecked() == true)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String time1 = sdf.format(new Date());
            TimeLog entry = new TimeLog(time1, 5);
            vref.push().setValue(entry);

            Firebase.setAndroidContext(this);
            vref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Firebase newRef = new Firebase("https://waitlistapplication.firebaseio.com/");
                    TimeLog log;
                    int sum = 0;
                    int number = 0;

                    for (DataSnapshot rest : snapshot.getChildren()) {
                        log = rest.getValue(TimeLog.class);
                        sum = sum + log.getSelection();
                        number++;
                    }
                    newRef.child(keyName).child("avg").setValue(sum / number);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            Intent intent = new Intent(this, infoPage.class);
            intent.putExtra("key", keyName);
            startActivity(intent);
        }


    }

}



