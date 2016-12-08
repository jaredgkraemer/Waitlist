package com.example.jason.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class newWindow1 extends AppCompatActivity {

    ArrayList<String> sArray = new ArrayList<String>();
    ArrayList<String> fullCopy = new ArrayList<String>();
    ArrayList<String> avgArray = new ArrayList<String>();
    CustomListAdapter mAdapter;

    restaurant restaurant;
    ListView list;
    EditText inputSearch;
    Map<String, String> keys = new HashMap<String, String>();

    /*
    Integer[] imgid= {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
            R.drawable.pic10,
            R.drawable.pic11,
            R.drawable.pic12,
            R.drawable.pic13,
            R.drawable.pic14,
            R.drawable.pic15,
    };
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_window1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Restaurant List");

        list = (ListView) findViewById(R.id.listy);
        inputSearch = (EditText) findViewById(R.id.inputSearch1);
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://waitlistapplication.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(sArray.size() != 0)
                {
                    sArray.clear();
                }
                if(avgArray.size() != 0)
                {
                    avgArray.clear();
                }

                for (DataSnapshot rest : snapshot.getChildren()) {
                    restaurant = rest.getValue(restaurant.class);
                    sArray.add(restaurant.getName());

                    avgArray.add(shortTextRep(restaurant.getAvg()));
                    if (rest.getKey() != null)
                        keys.put(restaurant.getName(), rest.getKey());

                }

                Collections.sort(sArray);
                fullCopy.addAll(sArray);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        //third parameter?

        mAdapter = new CustomListAdapter(this, sArray, keys); //arry adapter
        list.setAdapter(mAdapter); //should populate the second page with info in array


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

                String text = inputSearch.getText().toString().toLowerCase();

                mAdapter.filter(text, fullCopy);

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int position = index;

                //Get the String value of the item where the user clicked
                String name = (String) list.getItemAtPosition(position);

                String key = keys.get(name);

                //In order to start displaying new activity we need an intent
                Intent intent = new Intent(getApplicationContext(), infoPage.class);


                intent.putExtra("key", key);

                //Here we will pass the previously created intent as parameter
                startActivity(intent);

            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_window1, menu);
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


        if(id == R.id.home_settings) {

            Intent intent = new Intent(this, MainActivity.class); //Button to bring app back to homescreen
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String shortTextRep(int number)
    {
        if (number == 0)
            return "~0 minutes";
        else if (number == 1)
            return "~5-10 minutes";
        else if (number == 2)
            return "~10-20 minutes";
        else if (number == 3)
            return "~20-30 minutes";
        else if (number == 4)
            return "~30-40 minutes";
        else if (number == 5)
            return "over 40 minutes";
        else
            return "error";

    }


}
