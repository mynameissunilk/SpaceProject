package sunil.project3.calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import sunil.project3.R;

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

class CalendarAdapter extends BaseAdapter {
    Context context;
    ArrayList<CalendarEventObject> eventList;
    public CalendarAdapter(Context context) {
        this.context=context;
        eventList = new ArrayList<>();
        eventList = new ArrayList<>();
        eventList.add(new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100"));
        eventList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
        eventList.add(new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100"));
        eventList.add(new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100"));
        eventList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
        eventList.add(new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100"));
        eventList.add(new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100"));
        eventList.add(new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100"));

    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.calendar_cardview, viewGroup, false);
            TextView tvName = (TextView) view.findViewById(R.id.textview_calendar_event_title);
            TextView tvDate = (TextView) view.findViewById(R.id.textview_calendar_event_date);
            tvName.setText(eventList.get(i).getmEventTitle());
            tvDate.setText(eventList.get(i).getmEventDate());

        }


        return view;
    }



}


            //TEMPORARILY PUT THIS STUFF HERE, WAS IN THE MAIN ACTIVITY, JUS TRYNA AVOID MERGE CONFLICTS
//    public ArrayList<CalendarEventObject> getCelestialEventsList() {
//        //calendar event data
//        eventList = new ArrayList<>();
//        eventList.add(new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100"));
//        eventList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
//        eventList.add(new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100"));
//        eventList.add(new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100"));
//        eventList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
//        eventList.add(new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100"));
//        eventList.add(new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100"));
//        eventList.add(new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100"));
//
//        return eventList;
//    }
//
//
//    public void displayEventsOnCalendarCard() {
//
//        CalendarAdapter adapter = new CalendarAdapter(this);
//        mListView = (ListView) findViewById(R.id.listview_calendar_events);
//        mListView.setAdapter(adapter);
//    }
//
////the idea is that i will display these events in the listview on a single card, but how to get that into the recyclerview?
//
