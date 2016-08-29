package com.aman802.phoneapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Favorites extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,head;
    static final int PICK_CONTACT = 1;
    String contactNumber="---",name="---";
    LinearLayout l;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        l = (LinearLayout) findViewById(R.id.LLF);
        t1 = (TextView) findViewById(R.id.tfav1);
        t2 = (TextView) findViewById(R.id.tfav2);
        t3 = (TextView) findViewById(R.id.tfav3);
        t4 = (TextView) findViewById(R.id.tfav4);
        t5 = (TextView) findViewById(R.id.tfav5);
        t6 = (TextView) findViewById(R.id.tfav6);
        t7 = (TextView) findViewById(R.id.tfav7);
        t8 = (TextView) findViewById(R.id.tfav8);
        t9 = (TextView) findViewById(R.id.tfav9);
        t10 = (TextView) findViewById(R.id.tfav10);
        head = (TextView) findViewById(R.id.favText);

        SharedPreferences preferences = getSharedPreferences("MyData",MODE_PRIVATE);

        if(preferences.getBoolean("dark",false) == true){
            l.setBackgroundColor(Color.parseColor("#616161"));
            t1.setTextColor(Color.WHITE);
            t2.setTextColor(Color.WHITE);
            t3.setTextColor(Color.WHITE);
            t4.setTextColor(Color.WHITE);
            t5.setTextColor(Color.WHITE);
            t6.setTextColor(Color.WHITE);
            t7.setTextColor(Color.WHITE);
            t8.setTextColor(Color.WHITE);
            t9.setTextColor(Color.WHITE);
            t10.setTextColor(Color.WHITE);
            head.setTextColor(Color.WHITE);
        }

        b1 = (Button) findViewById(R.id.fav1);
        b2 = (Button) findViewById(R.id.fav2);
        b3 = (Button) findViewById(R.id.fav3);
        b4 = (Button) findViewById(R.id.fav4);
        b5 = (Button) findViewById(R.id.fav5);
        b6 = (Button) findViewById(R.id.fav6);
        b7 = (Button) findViewById(R.id.fav7);
        b8 = (Button) findViewById(R.id.fav8);
        b9 = (Button) findViewById(R.id.fav9);
        b10 = (Button) findViewById(R.id.fav10);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);

        SharedPreferences s = getSharedPreferences("Favorites",MODE_PRIVATE);
        if(s.getString("name1","") != ""){
            t1.setText("" + s.getString("name1","") + " " + s.getString("num1",""));
        }
        if(s.getString("name2","") != ""){
            t2.setText("" + s.getString("name2", "") + " " + s.getString("num2", ""));
        }
        if(s.getString("name3","") != ""){
            t3.setText("" + s.getString("name3","") + " " + s.getString("num3",""));
        }
        if(s.getString("name4","") != ""){
            t4.setText("" + s.getString("name4","") + " " + s.getString("num4",""));
        }
        if(s.getString("name5","") != ""){
            t5.setText("" + s.getString("name5","") + " " + s.getString("num5",""));
        }
        if(s.getString("name6","") != ""){
            t6.setText("" + s.getString("name6","") + " " + s.getString("num6",""));
        }
        if(s.getString("name7","") != ""){
            t7.setText("" + s.getString("name7","") + " " + s.getString("num7",""));
        }
        if(s.getString("name8","") != ""){
            t8.setText("" + s.getString("name8","") + " " + s.getString("num8",""));
        }
        if(s.getString("name9","") != ""){
            t9.setText("" + s.getString("name9", "") + " " + s.getString("num9",""));
        }
        if(s.getString("name10","") != ""){
            t10.setText("" + s.getString("name10","") + " " + s.getString("num10",""));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
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

    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences("Favorites",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        switch (v.getId()){
            case R.id.fav1:
                Intent i = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT);
                editor.putString("name1",name);
                editor.putString("num1",contactNumber);
                t1.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav2:
                Intent i2 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i2, PICK_CONTACT);
                editor.putString("name2", name);
                editor.putString("num2", contactNumber);
                t2.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav3:
                Intent i3 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i3, PICK_CONTACT);
                editor.putString("name3",name);
                editor.putString("num3",contactNumber);
                t3.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav4:
                Intent i4 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i4, PICK_CONTACT);
                editor.putString("name4",name);
                editor.putString("num4",contactNumber);
                t4.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav5:
                Intent i5 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i5, PICK_CONTACT);
                editor.putString("name5",name);
                editor.putString("num5",contactNumber);
                t5.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav6:
                Intent i6 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i6, PICK_CONTACT);
                editor.putString("name6",name);
                editor.putString("num6",contactNumber);
                t6.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav7:
                Intent i7 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i7, PICK_CONTACT);
                editor.putString("name7",name);
                editor.putString("num7",contactNumber);
                t7.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav8:
                Intent i8 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i8, PICK_CONTACT);
                editor.putString("name8",name);
                editor.putString("num8",contactNumber);
                t8.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav9:
                Intent i9 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i9, PICK_CONTACT);
                editor.putString("name9",name);
                editor.putString("num9",contactNumber);
                t9.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
            case R.id.fav10:
                Intent i0 = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i0, PICK_CONTACT);
                editor.putString("name10",name);
                editor.putString("num10",contactNumber);
                t10.setText("" + name + " " + contactNumber);
                editor.commit();
                break;
        }
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        if(reqCode == PICK_CONTACT){
            if(resultCode== Activity.RESULT_OK){

                Uri uri = data.getData();
                ContentResolver contentResolver = getContentResolver();
                Cursor contentCursor = contentResolver.query(uri, null, null,null, null);

                if(contentCursor.moveToFirst()){
                    String id = contentCursor.getString(contentCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                    String hasPhone =
                            contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1"))
                    {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                        phones.moveToFirst();
                        contactNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    }
                }
                super.onActivityResult(reqCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed(){
        SharedPreferences preferences = getSharedPreferences("MyData",MODE_PRIVATE);
        if(preferences.getBoolean("first",true)==true){
            startActivity(new Intent(this,MainActivity.class));
            preferences.edit().putBoolean("first",false).commit();
        }
        else
            startActivity(new Intent(this,Settings.class));
    }
}
