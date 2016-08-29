package com.aman802.phoneapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aman Vangani on 21-07-2016.
 */
public class CustomAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    ContactDetails tempValues = null;
    int i = 0;

    /*************
     * CustomAdapter Constructor
     *****************/
    public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView name;
        public TextView number;

    }

    /******
     * Depends upon data size called for each row , Create each ListView row
     *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            SharedPreferences preferences = activity.getSharedPreferences("MyData",Context.MODE_PRIVATE);
            if(preferences.getBoolean("dark",false)==true)
                vi = inflater.inflate(R.layout.list_item_dark, null);
            else
                vi = inflater.inflate(R.layout.list_item,null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            if(preferences.getBoolean("dark",false)==true) {
                holder.name = (TextView) vi.findViewById(R.id.name_dark);
                holder.number = (TextView) vi.findViewById(R.id.number_dark);
            }
            else{
                holder.name = (TextView) vi.findViewById(R.id.name);
                holder.number = (TextView) vi.findViewById(R.id.number);
            }

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (ContactDetails) data.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.name.setText(tempValues.getName());
            holder.number.setText(tempValues.getNumber());
        }

        return vi;
    }
}

















