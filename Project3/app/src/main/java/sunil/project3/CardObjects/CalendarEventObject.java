package sunil.project3.CardObjects;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by owlslubic on 8/15/16.
 */
public class CalendarEventObject extends CardObject{
    String mEventTitle, mEventDate, mDetailUrl, mWeekDay;
    int mYear, mMonth, mDay, mHour, mMinute;

    public String getmEventTitle() {
        return mEventTitle;
    }

    public String getmEventDate() {

        if (mHour == 00 && mMinute == 00) {
            return mWeekDay + ", " + mMonth + "/" + mDay + "/" + mYear;
        }
        return mWeekDay + ", " + mMonth + "/" + mDay + "/" + mYear + " at " + mHour + ":" + mMinute;
    }

    public String getmDetailUrl() {
        return mDetailUrl;
    }


    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public String getmWeekDay() {
        return mWeekDay;
    }


    public CalendarEventObject(String eventTitle, String weekDay, int year, int month, int day, int hour, int minute, String detailUrl) {
        mEventTitle = eventTitle;
        mWeekDay = weekDay;
        mYear = year;
        mMonth = month;
        mDay = day;
        mHour = hour;
        mMinute = minute;
        mDetailUrl = detailUrl;

    }

    public void addCalendarEvent(CalendarEventObject event, Context context) {
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

        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100"));
        CardObjSingleton.getInstance().addToMasterList(new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100"));

        Log.i("list", "addEventsToMasterList, number of events is: "+ CardObjSingleton.getInstance().getMasterList().size());


    }


}
