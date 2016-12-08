package com.example.jason.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class infoPage extends AppCompatActivity {

    private FirebaseAdapter<TimeLog> testAdapter;
    ListView testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Restaurant Info");

        Bundle extras = getIntent().getExtras();
        final String key = extras.getString("key");





        Firebase ref = new Firebase("https://waitlistapplication.firebaseio.com/");
        ref.addValueEventListener(new ValueEventListener() {      //Will get a snapshot value from out database and store it in array
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                restaurant Restaurant = snapshot.child(key).getValue(restaurant.class);

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                if(Restaurant.getName().equals("Burger King"))
                {
                    imageView.setImageResource(R.drawable.pic1);
                }
                else if(Restaurant.getName().equals("California Chicken Grill"))
                {
                    imageView.setImageResource(R.drawable.pic2);
                }
                else if(Restaurant.getName().equals("Chick-Fil-A"))
                {
                    imageView.setImageResource(R.drawable.pic3);
                }
                else if(Restaurant.getName().equals("Chipotle"))
                {
                    imageView.setImageResource(R.drawable.pic4);
                }
                else if(Restaurant.getName().equals("Gumby's"))
                {
                    imageView.setImageResource(R.drawable.pic5);
                }
                else if(Restaurant.getName().equals("Guthries"))
                {
                    imageView.setImageResource(R.drawable.pic6);
                }
                else if(Restaurant.getName().equals("Jimmy John's"))
                {
                    imageView.setImageResource(R.drawable.pic7);
                }
                else if(Restaurant.getName().equals("Maple Street Biscuit Company"))
                {
                    imageView.setImageResource(R.drawable.pic8);
                }
                else if(Restaurant.getName().equals("Mellow Mushroom"))
                {
                    imageView.setImageResource(R.drawable.pic9);
                }
                else if(Restaurant.getName().equals("Moe's"))
                {
                    imageView.setImageResource(R.drawable.pic10);
                }
                else if(Restaurant.getName().equals("Mr. Roboto"))
                {
                    imageView.setImageResource(R.drawable.pic11);
                }
                else if(Restaurant.getName().equals("Panda Express"))
                {
                    imageView.setImageResource(R.drawable.pic12);
                }
                else if(Restaurant.getName().equals("Popeyes"))
                {
                    imageView.setImageResource(R.drawable.pic13);
                }
                else if(Restaurant.getName().equals("What-A-Burger"))
                {
                    imageView.setImageResource(R.drawable.pic14);
                }
                else if(Restaurant.getName().equals("Zaxby's"))
                {
                    imageView.setImageResource(R.drawable.pic15);
                }
                else
                {
                    imageView.setImageResource(R.drawable.pic15);
                }

                TextView restName = (TextView) findViewById(R.id.restName);
                restName.setText(Restaurant.getName());

                TextView restAddr = (TextView) findViewById(R.id.restAddr);
                restAddr.setText(Restaurant.getAddress());

                TextView restPhone = (TextView) findViewById(R.id.restPhone);
                restPhone.setText(Restaurant.getPhone());

                TextView restAvg = (TextView) findViewById(R.id.restAvg);
                restAvg.setText("Wait Time: " + shortTextRep(Restaurant.getAvg()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        testList = (ListView) findViewById(R.id.timeList);


        Firebase ref2 = ref.child(key).child("log");

        testAdapter = new FirebaseAdapter<TimeLog>(ref2, TimeLog.class, android.R.layout.two_line_list_item, this) {
            @Override
            protected void populateView(View view, TimeLog tLog) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                String currentDay = sdf.format(new Date());
                if (currentDay.equals(tLog.getTime().substring(0, 10)))
                    ((TextView) view.findViewById(android.R.id.text1)).setText("Today" + " " + (tLog.getTime().substring(11, 19)));
                else {
                    ((TextView) view.findViewById(android.R.id.text1)).setText(tLog.getTime());

                }
                ((TextView) view.findViewById(android.R.id.text2)).setText(textRep(tLog.getSelection()));
            }



        };

        testList.setAdapter(testAdapter); //should populate the second page with info in array
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_infop, menu);
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
        return super.onOptionsItemSelected(item);
    }

    public String textRep(int number)
    {
        if (number == 0)
            return "No Line(~0 minutes)";
        else if (number == 1)
            return "Short(~5-10 minutes)";
        else if (number == 2)
            return "Medium(~10-20 minutes)";
        else if (number == 3)
            return "Medium-Long(~20-30 minutes)";
        else if (number == 4)
            return "Long(~30-40 minutes)";
        else if (number == 5)
            return "Very Long(over 40 minutes)";
        else
            return "error";

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

    public void InputPage(View view)
    {
        Bundle extras = getIntent().getExtras();
        final String key = extras.getString("key");
        Intent intent = new Intent(this, InputPage.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }

}
