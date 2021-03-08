package com.aman802.phoneapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDelegate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Util {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static int dpToPixel(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getRandomColor(Context context) {
        Random random = new Random();
        String[] colors = context.getResources().getStringArray(R.array.colors);
        return Color.parseColor(colors[(random.nextInt(7))]);
    }

    public static int getColorFromName(Context context, String name) {
        if (name == null || name.isEmpty()) return getRandomColor(context);
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            sum += name.charAt(i);
        }
        int position = sum % 7;
        String[] colors = context.getResources().getStringArray(R.array.colors);
        return Color.parseColor(colors[position]);
    }

    public static String getDateString(long longDate) {
        Date tempDate = new Date(longDate);
        // Same date, so show the time of call
        if (DateUtils.isToday(longDate)) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm aa", Locale.US);
            return timeFormat.format(tempDate);
        }
        else {
            Calendar currentCalendar = Calendar.getInstance();
            int currentYear = currentCalendar.get(Calendar.YEAR);
            int currentWeek = currentCalendar.get(Calendar.WEEK_OF_YEAR);

            Calendar paramCalendar = Calendar.getInstance();
            paramCalendar.setTimeInMillis(longDate);
            int paramYear = paramCalendar.get(Calendar.YEAR);
            int paramWeek = paramCalendar.get(Calendar.WEEK_OF_YEAR);

            // Same week, so show the day of the call
            if (currentYear == paramYear && currentWeek == paramWeek) {
                int dayOfTheWeek = paramCalendar.get(Calendar.DAY_OF_WEEK);
                switch (dayOfTheWeek) {
                    case Calendar.MONDAY: return "Mon ";
                    case Calendar.TUESDAY: return "Tue ";
                    case Calendar.WEDNESDAY: return "Wed ";
                    case Calendar.THURSDAY: return "Thu ";
                    case Calendar.FRIDAY: return "Fri ";
                    case Calendar.SATURDAY: return "Sat ";
                    case Calendar.SUNDAY: return "Sun ";
                }
            }

            // Show Month and Date
            else {
                int month = paramCalendar.get(Calendar.MONTH) + 1;
                int date = paramCalendar.get(Calendar.DAY_OF_MONTH);
                switch (month) {
                    case 1: return "Jan " + date;
                    case 2: return "Feb " + date;
                    case 3: return "Mar " + date;
                    case 4: return "Apr " + date;
                    case 5: return "May " + date;
                    case 6: return "Jun " + date;
                    case 7: return "Jul " + date;
                    case 8: return "Aug " + date;
                    case 9: return "Sep " + date;
                    case 10: return "Oct " + date;
                    case 11: return "Nov " + date;
                    case 12: return "Dec " + date;
                }
            }
        }

        return "";
    }

    public static void applyThemePreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        int themeType = sharedPreferences.getInt(context.getString(R.string.theme), 0);
        switch (themeType) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;

            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }

    public static void makeCall(Context context, String number) {
        if (!number.isEmpty()) {
            String name = getNameFromNumber(context, number);
            SharedPreference.setCurrentCallerName(context, name);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            context.startActivity(callIntent);
        }
    }

    public static void addNewContact(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
        context.startActivity(intent);
    }

    public static void addExistingContact(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        intent.setType(ContactsContract.RawContacts.CONTENT_ITEM_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
        context.startActivity(intent);
    }

    public static String getNameFromNumber(Context context, String number) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String[] projection = new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME};

        String name = "";
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                name = cursor.getString(0);
            }
            cursor.close();
        }

        return name;
    }

    public static ArrayList<User> getAllContacts(Context context) {
        HashMap<String, User> userIdMap = new HashMap<>();
        ArrayList<User> contactList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        Cursor cursor = contentResolver.query(contentUri, projection, null,null, ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                if (!userIdMap.containsKey(id)) {
                    String name = cursor.getString(1);
                    User tempUser = new User(id, name);
                    contactList.add(tempUser);
                    userIdMap.put(id, tempUser);
                }
            }
            cursor.close();
        }
        return contactList;
    }

    public static ArrayList<User> getFavoriteContacts(Context context) {
        ArrayList<User> contactList = new ArrayList<>();
        Uri queryUri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.STARRED,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
        };

        String selection = ContactsContract.Contacts.STARRED + "='1'";
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(queryUri, projection, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String title = (cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                String phoneNumber = null;
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor tempCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactID}, null);

                    if (tempCursor != null) {
                        while (tempCursor.moveToNext()) {
                            phoneNumber = tempCursor.getString(tempCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                        tempCursor.close();
                    }
                }
                if (phoneNumber == null) continue;
                contactList.add(new User(contactID, title, phoneNumber));
            }
            cursor.close();
        }
        return contactList;
    }

    public static ArrayList<User> getRecentContacts(Context context) {
        ArrayList<User> contactList = new ArrayList<>();
        Uri queryUri = CallLog.Calls.CONTENT_URI;
        String[] projection = new String[] {
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.DATE,
                CallLog.Calls.TYPE
        };

        String sortOrder = String.format("%s limit 500 ", CallLog.Calls.DATE + " DESC");
        Cursor cursor = context.getContentResolver().query(queryUri, projection, null,null, sortOrder);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String phoneNumber = cursor.getString(1);
            String name = (cursor.getString(2));
            long longDate = Long.parseLong(cursor.getString(3));
            int type = Integer.parseInt(cursor.getString(4));

            if(phoneNumber == null)continue;
            if (name == null || name.isEmpty()) name = "Unknown";

            contactList.add(new User(id, name, phoneNumber, type, longDate));
        }
        cursor.close();
        contactList = getContactsWithSimultaneousCount(contactList);
        return contactList;
    }

    private static ArrayList<User> getContactsWithSimultaneousCount(ArrayList<User> userList) {
        if (userList.isEmpty() || userList.size() == 1) return userList;
        ArrayList<User> tempUserList = new ArrayList<>();
        User tempUser = userList.get(0);
        int currentSimultaneousOccurrences = 1;
        int i = 0, j = 1, length = userList.size();
        while (i < length && j < length) {
            if (userList.get(i).equals(userList.get(j))) {
                currentSimultaneousOccurrences++;
            }
            else {
                if (currentSimultaneousOccurrences > 1) {
                    tempUser.setDisplayName(tempUser.getDisplayName() + " (" + currentSimultaneousOccurrences + ")");
                }
                currentSimultaneousOccurrences = 1;
                tempUserList.add(tempUser);
                i = j;
                if (i < length)
                    tempUser = userList.get(i);
            }
            j++;
        }

        if (currentSimultaneousOccurrences > 1) {
            tempUser.setDisplayName(tempUser.getDisplayName() + " (" + currentSimultaneousOccurrences + ")");
            tempUserList.add(tempUser);
        }

        return tempUserList;
    }
}
