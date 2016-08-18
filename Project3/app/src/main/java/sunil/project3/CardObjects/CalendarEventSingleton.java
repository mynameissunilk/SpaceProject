package sunil.project3.CardObjects;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import sunil.project3.MainActivity;

/**
 * Created by owlslubic on 8/15/16.
 */

//THIS WORKS ON ANDERS'S PHONE, BUT NOT ON MINE. TEST MORE LATER

public class CalendarEventSingleton {
    private static final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 0;


    private static final String TAG = "CalendarEventSingleton";
    private static CalendarEventSingleton sInstance;

    public ArrayList<CalendarEventObject> eventList = new ArrayList<>();
    public int year, month, day, hour, minute;

    private CalendarEventSingleton() {
    }

    public static CalendarEventSingleton getInstance() {
        if (sInstance == null) {
            sInstance = new CalendarEventSingleton();
        }
        return sInstance;
    }


    //restructuring this to see if it works
//    public void addCalendarEvent(CalendarEventObject event, Context context) {
//        long startMillis = 0;
//        long endMillis = 0;
//        year = event.getmYear();
//        month = event.getmMonth();
//        day = event.getmDay();
//        hour = event.getmHour();
//        minute = event.getmMinute();
//
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(year, month, day, hour, minute);
//        startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(year, month, day, hour, minute + 10);
//        endMillis = endTime.getTimeInMillis();
//        Log.i(TAG, "addCalendarEvent: begin time is " + beginTime);
//
//        ContentResolver contentResolver = context.getContentResolver();
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Events.DTSTART, startMillis);
//        values.put(CalendarContract.Events.DTEND, endMillis);
//        values.put(CalendarContract.Events.TITLE, event.getmEventTitle());
//        values.put(CalendarContract.Events.DESCRIPTION, event.getmDetailUrl());
//        values.put(CalendarContract.Events.CALENDAR_ID, 3);
//        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York");
//        //using hardcoded calendar id because requesting the READ_CALENDAR permission was giving me trouble when I tried to query for the id,
//        //I'll come back to it, and checking permissions, when other stuff works
//        contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);
//
//        Toast.makeText(context, event.getmEventTitle() + " added to your calendar", Toast.LENGTH_SHORT).show();
//    }


    //um currently it's adding these to september?
//    public void addCalendarEvent(int position, Context context) {
//        long startMillis = 0;
//        long endMillis = 0;
//        year = eventList.get(position).getmYear();
//        month = eventList.get(position).getmMonth();
//        day = eventList.get(position).getmDay();
//        hour = eventList.get(position).getmHour();
//        minute = eventList.get(position).getmMinute();
//
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(year, month, day, hour, minute);
//        startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(year, month, day, hour, minute+10);
//        endMillis = endTime.getTimeInMillis();
//        Log.i(TAG, "addCalendarEvent: begin time is "+beginTime);
//
//        ContentResolver contentResolver = context.getContentResolver();
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Events.DTSTART, startMillis);
//        values.put(CalendarContract.Events.DTEND, endMillis);
//        values.put(CalendarContract.Events.TITLE, eventList.get(position).getmEventTitle());
//        values.put(CalendarContract.Events.DESCRIPTION, eventList.get(position).getmDetailUrl());
//        values.put(CalendarContract.Events.CALENDAR_ID, 3);
//        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York");
//        //using hardcoded calendar id because requesting the READ_CALENDAR permission was giving me trouble when I tried to query for the id,
//        //I'll come back to it, and checking permissions, when other stuff works
//        contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);
//
//        Toast.makeText(context, eventList.get(position).getmEventTitle() + " added to your calendar", Toast.LENGTH_SHORT).show();
//    }


    //trying a different way
//    public void addEventsToMasterList() {
//
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100"));
//        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100"));
//
//        Log.i("list", "addEventsToMasterList, number of events is: "+ CardObjSingleton.getInstance().getMasterList().size());
//
//
//    }

//    public void addEventsToMasterList(ArrayList<CalendarEventObject> eventList){
//
//        for (int i = 0; i < eventList.size(); i++) {
//            CardObjSingleton.getInstance().addToMasterList(eventList.get(i));
//            Log.i("list", "addEventsToMasterList: "+i);
//        }
//    }


    public ArrayList<CalendarEventObject> getEventList() {
        if (eventList.size() > 0) {
            eventList.clear();
        }
        eventList.add(new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100"));
        eventList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
        eventList.add(new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100"));
        eventList.add(new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100"));
        eventList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
        eventList.add(new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100"));
        eventList.add(new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100"));
        eventList.add(new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100"));
        return eventList;
    }


}
