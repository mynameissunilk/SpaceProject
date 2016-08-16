package sunil.project3;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by owlslubic on 8/15/16.
 */

//THIS WORKS ON ANDERS'S PHONE, BUT NOT ON MINE. TEST MORE LATER

public class CalendarEvent {
    public ArrayList<CalendarEventObject> eventList;
    Context context;
    public int year, month, day, hour, minute;

    public CalendarEvent(ArrayList<CalendarEventObject> list, Context context) {
        this.eventList = list;
        this.context = context;
    }

    public void addCalendarEvent(int position) {
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


}
