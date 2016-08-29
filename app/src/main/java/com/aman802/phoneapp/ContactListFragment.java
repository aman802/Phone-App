package com.aman802.phoneapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<ContactDetails> list = new ArrayList<ContactDetails>();
    CustomAdapter customAdapter;
    Communicate communicate;
    Vibrator vibrate;

    public ContactListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicate = (Communicate) getActivity();
        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        SharedPreferences preferences = getActivity().getSharedPreferences("Favorites",Context.MODE_PRIVATE);

        list.add(new ContactDetails(preferences.getString("name1","-------------------"),preferences.getString("num1","-----------")));
        list.add(new ContactDetails(preferences.getString("name2","-------------------"),preferences.getString("num2","-----------")));
        list.add(new ContactDetails(preferences.getString("name3","-------------------"),preferences.getString("num3","-----------")));
        list.add(new ContactDetails(preferences.getString("name4","-------------------"),preferences.getString("num4","-----------")));
        list.add(new ContactDetails(preferences.getString("name5","-------------------"),preferences.getString("num5","-----------")));
        list.add(new ContactDetails(preferences.getString("name6","-------------------"),preferences.getString("num6","-----------")));
        list.add(new ContactDetails(preferences.getString("name7","-------------------"),preferences.getString("num7","-----------")));
        list.add(new ContactDetails(preferences.getString("name8","-------------------"),preferences.getString("num8","-----------")));
        list.add(new ContactDetails(preferences.getString("name9","-------------------"),preferences.getString("num9","-----------")));
        list.add(new ContactDetails(preferences.getString("name10","-------------------"),preferences.getString("num10","-----------")));

        Resources res =getResources();


        listView = (ListView) getActivity().findViewById(R.id.listView);

        customAdapter = new CustomAdapter(getActivity(),list,res);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0: communicate.function(list.get(0).getNumber());
                    break;
            case 1: communicate.function(list.get(1).getNumber());
                break;
            case 2: communicate.function(list.get(2).getNumber());
                break;
            case 3: communicate.function(list.get(3).getNumber());
                break;
            case 4: communicate.function(list.get(4).getNumber());
                break;
            case 5: communicate.function(list.get(5).getNumber());
                break;
            case 6: communicate.function(list.get(6).getNumber());
                break;
            case 7: communicate.function(list.get(7).getNumber());
                break;
            case 8: communicate.function(list.get(8).getNumber());
                break;
            case 9: communicate.function(list.get(9).getNumber());
                break;
        }
        SharedPreferences preferences = getActivity().getSharedPreferences("MyData",Context.MODE_PRIVATE);
        if(preferences.getBoolean("vibrate",false)==true){
            vibrate.vibrate(50);
        }
    }
}
