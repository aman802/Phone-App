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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    TextView sd,fav;
    CheckBox checkBox,vibration;
    Button save;
    LinearLayout rl;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        sd = (TextView) findViewById(R.id.speed);
        fav = (TextView) findViewById(R.id.fav);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        vibration = (CheckBox) findViewById(R.id.vibration);
        save = (Button) findViewById(R.id.save);
        rl = (LinearLayout) findViewById(R.id.LS);


        SharedPreferences preferences = getSharedPreferences("MyData",MODE_PRIVATE);
        if(preferences.getBoolean("dark",false) == true){
            checkBox.setChecked(true);
            checkBox.setTextColor(Color.WHITE);
            vibration.setTextColor(Color.WHITE);
            sd.setTextColor(Color.WHITE);
            fav.setTextColor(Color.WHITE);
            rl.setBackgroundColor(Color.parseColor("#616161"));
        }
        else
            checkBox.setChecked(false);
        if(preferences.getBoolean("vibrate",false) == true)
            vibration.setChecked(true);
        else
            vibration.setChecked(false);

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.this,SpeedDial.class);
                startActivity(i);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("dark", isChecked);
                editor.commit();
            }
        });

        vibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("vibrate",isChecked);
                editor.commit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.this, MainActivity.class);
                startActivity(i);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.this,Favorites.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
