package com.aman802.phoneapp;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpeedDial extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    LinearLayout l;
    TextView key,number,t1,t2,t3,t4,t5,t6,t7,t8,t9;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_speed_dial);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        e7 = (EditText) findViewById(R.id.editText7);
        e8 = (EditText) findViewById(R.id.editText8);
        e9 = (EditText) findViewById(R.id.editText9);
        l = (LinearLayout) findViewById(R.id.LLSD);
        key = (TextView) findViewById(R.id.key);
        number = (TextView) findViewById(R.id.number);
        t1 = (TextView) findViewById(R.id.s1);
        t2 = (TextView) findViewById(R.id.s2);
        t3 = (TextView) findViewById(R.id.s3);
        t4 = (TextView) findViewById(R.id.s4);
        t5 = (TextView) findViewById(R.id.s5);
        t6 = (TextView) findViewById(R.id.s6);
        t7 = (TextView) findViewById(R.id.s7);
        t8 = (TextView) findViewById(R.id.s8);
        t9 = (TextView) findViewById(R.id.s9);

        SharedPreferences s = getSharedPreferences("MyData",MODE_PRIVATE);

        if(s.getBoolean("dark",false) == true){
            l.setBackgroundColor(Color.parseColor("#616161"));
            key.setTextColor(Color.WHITE);
            number.setTextColor(Color.WHITE);
            t1.setTextColor(Color.WHITE);
            t2.setTextColor(Color.WHITE);
            t3.setTextColor(Color.WHITE);
            t4.setTextColor(Color.WHITE);
            t5.setTextColor(Color.WHITE);
            t6.setTextColor(Color.WHITE);
            t7.setTextColor(Color.WHITE);
            t8.setTextColor(Color.WHITE);
            t9.setTextColor(Color.WHITE);
            e1.setTextColor(Color.WHITE);
            e2.setTextColor(Color.WHITE);
            e3.setTextColor(Color.WHITE);
            e4.setTextColor(Color.WHITE);
            e5.setTextColor(Color.WHITE);
            e6.setTextColor(Color.WHITE);
            e7.setTextColor(Color.WHITE);
            e8.setTextColor(Color.WHITE);
            e9.setTextColor(Color.WHITE);


        }

        if(s.getString("1","") != "")
            e1.setText(s.getString("1",""));
        if(s.getString("2","") != "")
            e2.setText(s.getString("2",""));
        if(s.getString("3","") != "")
            e3.setText(s.getString("3",""));
        if(s.getString("4","") != "")
            e4.setText(s.getString("4",""));
        if(s.getString("5","") != "")
            e5.setText(s.getString("5",""));
        if(s.getString("6","") != "")
            e6.setText(s.getString("6",""));
        if(s.getString("7","") != "")
            e7.setText(s.getString("7",""));
        if(s.getString("8","") != "")
            e8.setText(s.getString("8",""));
        if(s.getString("9","") != "")
            e9.setText(s.getString("9",""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speed_dial, menu);
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
            Intent i = new Intent(this,Settings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void save(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("1", e1.getText().toString());
        editor.putString("2",e2.getText().toString());
        editor.putString("3",e3.getText().toString());
        editor.putString("4",e4.getText().toString());
        editor.putString("5",e5.getText().toString());
        editor.putString("6",e6.getText().toString());
        editor.putString("7",e7.getText().toString());
        editor.putString("8",e8.getText().toString());
        editor.putString("9", e9.getText().toString());
        editor.commit();

        Intent i = new Intent(this,Settings.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,Settings.class));
    }
}
