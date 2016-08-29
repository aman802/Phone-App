package com.aman802.phoneapp;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialPadFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    Button B1, B2, B3, B4, B5, B6, B7, B8, B9, B0, star, hash, plus, call, add, back;
    TextView t;
    LinearLayout L,L2,L3;
    Vibrator vibrate;


    public DialPadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dial_pad, container, false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        L = (LinearLayout) getActivity().findViewById(R.id.LLDP);
        L2 = (LinearLayout) getActivity().findViewById(R.id.LLDP2);
        L3 = (LinearLayout) getActivity().findViewById(R.id.LLDP3);
        t = (TextView) getActivity().findViewById(R.id.textnumber);
        B1 = (Button) getActivity().findViewById(R.id.one);
        B2 = (Button) getActivity().findViewById(R.id.two);
        B3 = (Button) getActivity().findViewById(R.id.three);
        B4 = (Button) getActivity().findViewById(R.id.four);
        B5 = (Button) getActivity().findViewById(R.id.five);
        B6 = (Button) getActivity().findViewById(R.id.six);
        B7 = (Button) getActivity().findViewById(R.id.seven);
        B8 = (Button) getActivity().findViewById(R.id.eight);
        B9 = (Button) getActivity().findViewById(R.id.nine);
        B0 = (Button) getActivity().findViewById(R.id.zero);
        star = (Button) getActivity().findViewById(R.id.star);
        hash = (Button) getActivity().findViewById(R.id.hash);
        plus = (Button) getActivity().findViewById(R.id.plus);
        call = (Button) getActivity().findViewById(R.id.call);
        add = (Button) getActivity().findViewById(R.id.add);
        back = (Button) getActivity().findViewById(R.id.back);

        //call.setTextColor(Color.WHITE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData",Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("dark",false) == true){
            B1.setTextColor(Color.WHITE);
            B1.setBackgroundColor(Color.parseColor("#616161"));
            B2.setTextColor(Color.WHITE);
            B2.setBackgroundColor(Color.parseColor("#616161"));
            B3.setTextColor(Color.WHITE);
            B3.setBackgroundColor(Color.parseColor("#616161"));
            B4.setTextColor(Color.WHITE);
            B4.setBackgroundColor(Color.parseColor("#616161"));
            B5.setTextColor(Color.WHITE);
            B5.setBackgroundColor(Color.parseColor("#616161"));
            B6.setTextColor(Color.WHITE);
            B6.setBackgroundColor(Color.parseColor("#616161"));
            B7.setTextColor(Color.WHITE);
            B7.setBackgroundColor(Color.parseColor("#616161"));
            B8.setTextColor(Color.WHITE);
            B8.setBackgroundColor(Color.parseColor("#616161"));
            B9.setTextColor(Color.WHITE);
            B9.setBackgroundColor(Color.parseColor("#616161"));
            B0.setTextColor(Color.WHITE);
            B0.setBackgroundColor(Color.parseColor("#616161"));
            star.setTextColor(Color.WHITE);
            star.setBackgroundColor(Color.parseColor("#616161"));
            hash.setTextColor(Color.WHITE);
            hash.setBackgroundColor(Color.parseColor("#616161"));
            plus.setTextColor(Color.WHITE);
            plus.setBackgroundColor(Color.parseColor("#616161"));
            back.setTextColor(Color.WHITE);
            back.setBackgroundColor(Color.parseColor("#424242"));
            add.setTextColor(Color.WHITE);
            add.setBackgroundColor(Color.parseColor("#616161"));
            B1.setPadding(2, 2, 2, 2);
            B2.setPadding(2,2,2,2);
            B3.setPadding(2,2,2,2);
            B4.setPadding(2,2,2,2);
            B5.setPadding(2,2,2,2);
            B6.setPadding(2,2,2,2);
            B7.setPadding(2,2,2,2);
            B8.setPadding(2,2,2,2);
            B9.setPadding(2,2,2,2);
            B0.setPadding(2,2,2,2);
            star.setPadding(2, 2, 2, 2);
            hash.setPadding(2, 2, 2, 2);
            plus.setPadding(2, 2, 2, 2);
            back.setPadding(2, 2, 2, 2);
            add.setPadding(2, 2, 2, 2);
            B1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B9.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            B0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            star.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            hash.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            plus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            back.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            add.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            t.setBackgroundColor(Color.parseColor("#424242"));
            t.setTextColor(Color.parseColor("#FFFFFF"));
            L.setBackgroundColor(Color.parseColor("#424242"));
            L2.setBackgroundColor(Color.parseColor("#424242"));
            L3.setBackgroundColor(Color.parseColor("#424242"));
        }

        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B0.setOnClickListener(this);
        star.setOnClickListener(this);
        hash.setOnClickListener(this);
        plus.setOnClickListener(this);
        call.setOnClickListener(this);
        add.setOnClickListener(this);
        back.setOnClickListener(this);

        B1.setOnLongClickListener(this);
        B2.setOnLongClickListener(this);
        B3.setOnLongClickListener(this);
        B4.setOnLongClickListener(this);
        B5.setOnLongClickListener(this);
        B6.setOnLongClickListener(this);
        B7.setOnLongClickListener(this);
        B8.setOnLongClickListener(this);
        B9.setOnLongClickListener(this);
        back.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getActivity().getSharedPreferences("MyData",Context.MODE_PRIVATE);
        boolean vib = preferences.getBoolean("vibrate",true);
        switch (v.getId()) {
            case R.id.one:
                t.setText(t.getText() + "1");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.two:
                t.setText(t.getText() + "2");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.three:
                t.setText(t.getText() + "3");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.four:
                t.setText(t.getText() + "4");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.five:
                t.setText(t.getText() + "5");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.six:
                t.setText(t.getText() + "6");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.seven:
                t.setText(t.getText() + "7");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.eight:
                t.setText(t.getText() + "8");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.nine:
                t.setText(t.getText() + "9");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.zero:
                t.setText(t.getText() + "0");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.star:
                t.setText(t.getText() + "*");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.hash:
                t.setText(t.getText() + "#");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.plus:
                t.setText(t.getText() + "+");
                if(vib == true)
                    vibrate.vibrate(50);
                break;
            case R.id.call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + t.getText()));
                startActivity(callIntent);
                break;
            case R.id.add:
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE,t.getText());
                int PICK_CONTACT = 100;
                getActivity().startActivityForResult(intent, PICK_CONTACT);
                break;
            case R.id.back:
                if (t.length() != 0) {
                    t.setText(t.getText().toString().substring(0, t.length() - 1));
                    if(vib == true)
                        vibrate.vibrate(50);
                }
                else {
                }
                break;
        }
    }

    public void getNoFromList(String num) {
        t.setText(num);
    }

    @Override
    public boolean onLongClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        switch (v.getId()) {
            case R.id.one:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("1","")));
                startActivity(callIntent);
                break;
            case R.id.two:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("2","")));
                startActivity(callIntent);
                break;
            case R.id.three:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("3","")));
                startActivity(callIntent);
                break;
            case R.id.four:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("4","")));
                startActivity(callIntent);
                break;
            case R.id.five:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("5","")));
                startActivity(callIntent);
                break;
            case R.id.six:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("6","")));
                startActivity(callIntent);
                break;
            case R.id.seven:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("7","")));
                startActivity(callIntent);
                break;
            case R.id.eight:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("8","")));
                startActivity(callIntent);
                break;
            case R.id.nine:
                callIntent.setData(Uri.parse("tel:" + sharedPreferences.getString("9","")));
                startActivity(callIntent);
                break;
            case R.id.zero:
                t.setText(t.getText() + "+");
            case R.id.back:
                if(sharedPreferences.getBoolean("vibrate",true) == true)
                    vibrate.vibrate(50);
                t.setText("");
                break;
        }
        return true;
    }



    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }

    }
}
