package com.aman802.phoneapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView p1,p2,p3,p4,p5,p6,p7;
    LinearLayout l;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        p1 = (TextView) findViewById(R.id.tv1);
        p2 = (TextView) findViewById(R.id.tv2);
        p3 = (TextView) findViewById(R.id.tv3);
        p4 = (TextView) findViewById(R.id.tv4);
        p5 = (TextView) findViewById(R.id.tv5);
        p6 = (TextView) findViewById(R.id.tv6);
        p7 = (TextView) findViewById(R.id.tv7);

        l = (LinearLayout) findViewById(R.id.LLH);
        SharedPreferences preferences = getSharedPreferences("MyData",MODE_PRIVATE);
        if(preferences.getBoolean("dark",false) == true){
            l.setBackgroundColor(Color.parseColor("#616161"));
            p1.setTextColor(Color.WHITE);
            p2.setTextColor(Color.WHITE);
            p3.setTextColor(Color.WHITE);
            p4.setTextColor(Color.WHITE);
            p5.setTextColor(Color.WHITE);
            p6.setTextColor(Color.WHITE);
            p7.setTextColor(Color.WHITE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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
            return true;
        }
        if (id==android.R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
    }
}
