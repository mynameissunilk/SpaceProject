package sunil.project3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<MainRvViewHolder> {
    private static final String TAG = "MainRvAdapter";
    //    public ArrayList<String> list;
    public ArrayList<CalendarEventObject> eventList;
    Context context;


    public MainRvAdapter(ArrayList<CalendarEventObject> list, Context context) {
        this.eventList = list;
        this.context = context;
    }
//    @Override
//    public MainRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
//        MainRvViewHolder viewHolder = new MainRvViewHolder(view);


    @Override
    public MainRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cardview, parent, false);
        MainRvViewHolder viewHolder = new MainRvViewHolder(view);

        //for inflating different card layouts
//        if(viewType == CARDVIEW_TYPE_1){
//              View view = ...
//              viewholder = new Otherviewholder}


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MainRvViewHolder holder, final int position) {
//        holder.mTextView.setText(list.get(position));
        holder.mAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //you should do this in an async task
                new CalendarEvent(eventList, context).addCalendarEvent(position);

            }
        });
        holder.mCalendarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(eventList.get(position).getmDetailUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);


            }
        });
        holder.mEventTitle.setText(eventList.get(position).getmEventTitle());
        holder.mEventDate.setText(eventList.get(position).getmEventDate());


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


}


