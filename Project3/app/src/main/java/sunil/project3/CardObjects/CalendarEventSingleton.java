package sunil.project3.CardObjects;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by owlslubic on 8/15/16.
 */

//THIS WORKS ON ANDERS'S PHONE, BUT NOT ON MINE. TEST MORE LATER

public class CalendarEventSingleton {
    private static final String TAG = "CalendarEventSingleton";
    private static CalendarEventSingleton sInstance;

    public ArrayList<CalendarEventObject> eventList;
    public int year, month, day, hour, minute;

    private CalendarEventSingleton(){}

    public static CalendarEventSingleton getInstance(){
        if(sInstance == null){
            sInstance = new CalendarEventSingleton();
        }
        return sInstance;
    }


    public void addCalendarEvent(int position, Context context) {
        long startMillis = 0;
        long endMillis = 0;
        year = eventList.get(position).getmYear();
        month = eventList.get(position).getmMonth();
        day = eventList.get(position).getmDay();
        hour = eventList.get(position).getmHour();
        minute = eventList.get(position).getmMinute();

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month, day, hour, minute);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, month, day, hour, minute+1);
        endMillis = endTime.getTimeInMillis();

        ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, eventList.get(position).getmEventTitle());
        values.put(CalendarContract.Events.DESCRIPTION, eventList.get(position).getmDetailUrl());
        values.put(CalendarContract.Events.CALENDAR_ID, 3);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York");
        contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);

        Toast.makeText(context, eventList.get(position).getmEventTitle() + " added to your calendar", Toast.LENGTH_SHORT).show();
    }

    public void addEventsToMasterList(ArrayList<CalendarEventObject> eventList){

        eventList = getEventList();
        for (int i = 0; i < eventList.size(); i++) {
            CardObjSingleton.getInstance().addToMasterList(eventList.get(i));
            Log.i(TAG, "addEventsToMasterList: "+i);
        }
    }


    public ArrayList<CalendarEventObject> getEventList(){
        eventList = new ArrayList<>();
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
