package sunil.project3.CardObjects;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ander on 8/16/2016.
 */
public class CardObjSingleton {

    private static final String TAG = "CardObjSingleton";
    public List<CardObject> masterArray = new ArrayList<>();

    public static CardObjSingleton instance;

    private CardObjSingleton() {
    }

    public static CardObjSingleton getInstance() {
        if (instance == null) {
            instance = new CardObjSingleton();
        }
        return instance;
    }

    public void addToMasterList(CardObject cardObject) {
        masterArray.add(cardObject);
    }

    public List<CardObject> getMasterList() {
        Log.i("list", "getMasterList size is: " + masterArray.size());
        return masterArray;
    }


    //calendar event-related methods
    public void addEventToCalendar(CalendarEventObject event, Context context) {
        long startMillis = 0;
        long endMillis = 0;
        int year = event.getmYear();
        int month = event.getmMonth();
        int day = event.getmDay();
        int hour = event.getmHour();
        int minute = event.getmMinute();

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month, day, hour, minute);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, month, day, hour, minute + 10);
        endMillis = endTime.getTimeInMillis();

        ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, event.getmEventTitle());
        values.put(CalendarContract.Events.DESCRIPTION, event.getmDetailUrl());
        values.put(CalendarContract.Events.CALENDAR_ID, 3);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York");
        //using hardcoded calendar id because requesting the READ_CALENDAR permission was giving me trouble when I tried to query for the id,
        //I'll come back to it, and checking permissions, when other stuff works
        contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);

        Toast.makeText(context, event.getmEventTitle() + " added to your calendar", Toast.LENGTH_SHORT).show();
    }


    public void addEventsToMasterList() {
        CalendarEventObject event = new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100");
        CalendarEventObject event1 = new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100");
        CalendarEventObject event2 = new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100");
        CalendarEventObject event3 = new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100");
        CalendarEventObject event4 = new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100");
        CalendarEventObject event5 = new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100");
        CalendarEventObject event6 = new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100");
        CalendarEventObject event7 = new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100");

//        if (masterArray.contains(event)) {
//        } else if (masterArray.contains(event1)) {
//        } else if (masterArray.contains(event2)) {
//        } else if (masterArray.contains(event3)) {
//        }
//        else if(masterArray.contains(event4)){}
//        else if(masterArray.contains(event5)){}
//        else if(masterArray.contains(event6)){}
//        else if(masterArray.contains(event7)){}
//        else {
            masterArray.add(event);
            masterArray.add(event1);
            masterArray.add(event2);
            masterArray.add(event3);
            masterArray.add(event4);
            masterArray.add(event5);
            masterArray.add(event6);
            masterArray.add(event7);
//        }
        Log.i("list", "addEventsToMasterList, number of events is: " + CardObjSingleton.getInstance().getMasterList().size());

    }


}

