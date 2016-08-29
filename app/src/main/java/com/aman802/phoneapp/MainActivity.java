package com.aman802.phoneapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity implements Communicate{

    LinearLayout layout;
    static final int PICK_CONTACT = 1;
    int unicode = 0x1F60A;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("MyData",MODE_PRIVATE);

        if(preferences.getBoolean("first",true) == true){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Welcome");
            alertDialogBuilder.setMessage("Welcome to the Phone App.\n You need to set your favorite numbers so that you can contact them easily");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getBaseContext(),Favorites.class));
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


        layout = (LinearLayout) findViewById(R.id.LL);

        /*SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("dark",false) == true){
            layout.setBackgroundColor(Color.parseColor("#424242"));
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent i = new Intent(this,Settings.class);
            startActivity(i);
            return true;
        }

        else if(id == R.id.action_about){
            Intent i = new Intent(this,About.class);
            startActivity(i);
            return true;
        }

        else if(id == R.id.action_help){
            Intent i = new Intent(this,Help.class);
            startActivity(i);
            return true;
        }

        else if(id == R.id.action_contacts){
            Intent i = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i, PICK_CONTACT);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void function(String s) {
        FragmentManager manager = getFragmentManager();

        DialPadFragment dialPadFragment = (DialPadFragment) manager.findFragmentById(R.id.dialpad);
        dialPadFragment.getNoFromList(s);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        if(reqCode == PICK_CONTACT){
            if(resultCode==Activity.RESULT_OK){

                Uri uri = data.getData();
                ContentResolver contentResolver = getContentResolver();
                Cursor contentCursor = contentResolver.query(uri, null, null,null, null);

                if(contentCursor.moveToFirst()){
                    String id = contentCursor.getString(contentCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                    String hasPhone =
                            contentCursor.getString(contentCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1"))
                    {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        phones.moveToFirst();
                        String contactNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        function(contactNumber);

                    }
                }
                super.onActivityResult(reqCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed(){
        this.finish();
        System.exit(0);
    }
}
