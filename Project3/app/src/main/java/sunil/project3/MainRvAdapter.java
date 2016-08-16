package sunil.project3;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import sunil.project3.calendar.CalendarEventObject;
import sunil.project3.calendar.CalendarEventSingleton;
import sunil.project3.viewholders.CalendarViewHolder;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int CALENDAR = 0;
    public static final int NEWS = 1;
    public static final int TWITTER = 2;



    public ArrayList<String> mInnerText;
    public ArrayList<String> mList;
    public ArrayList<String> mShortText;
    public ArrayList<CalendarEventObject> mEventList;
    int mViewType;
    RecyclerView.ViewHolder mViewHolder;
    AstronautViewHolder AVH;
    CalendarViewHolder calVH;

    // for one viewGroup
    public MainRvAdapter(int viewType, ArrayList<String> shortText, ArrayList<String> innerText
            , ArrayList<String> list, ArrayList<CalendarEventObject> eventList) {
        this.mList = list;
        mViewType = viewType;
        this.mShortText = shortText;
        this.mInnerText = innerText;

        mEventList = eventList;
    }

    //int how many we want to be shown




//    // for another view group
//    public MainRvAdapter(int viewType, ArrayList<String> list){
//        mViewType = viewType;
//        this.mList = list;
//    }



//    public MainRvAdapter(int viewType, ArrayList<CalendarEventObject> eventList){
//        mEventList = eventList;
//        mViewType = viewType;
//    }




    Context mContext;

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // retrieving the context of this adapter
        mContext = parent.getContext();

        // choosing and inflating the view type
        switch (mViewType) {
            case 0:
                View view0 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                mViewHolder = new MainRvViewHolder(view0);
                break;
            case 1:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2, parent, false);
                mViewHolder = new AstronautViewHolder(view1);
                break;
            case 2:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cardview,parent, false);
                mViewHolder = new CalendarViewHolder(view2);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // getting all the items in the following table
        ArrayList array = DBHelper.getInstance(mContext).getAllItemsArrayList();

        // populating the view type
        switch (mViewType) {
            default:
            case 0:
                MainRvViewHolder MVH = (MainRvViewHolder) holder;
                MVH.mT1.setText(array.get(position).toString());
//                MVH.mT2.setText(array.get(position).toString());
//                MVH.mT3.setText(array.get(position).toString());
//                MVH.mT4.setText(array.get(position).toString());
//                MVH.mT5.setText(array.get(position).toString());
                break;
            case 1:
                AVH = (AstronautViewHolder) holder;
                AVH.mImageView.setImageResource(android.R.drawable.ic_input_add);
                if(!mShortText.get(0).isEmpty()) {
                    AVH.mSmallTextView.setText(mShortText.get(position));
                }
                if(!mInnerText.get(0).isEmpty()) {
                    AVH.mInnerTextView.setText(mInnerText.get(position));
                    AVH.mInnerTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AVH.mSmallTextView.setHeight(2000);
                            AVH.mSmallTextView.setLayoutParams(new FrameLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, 200));
                            Toast.makeText(mContext,"click" + view.getHeight(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case 2:
                calVH = (CalendarViewHolder) holder;
                if(mEventList.size()>0){
                    calVH.mEventTitle.setText(mEventList.get(0).getmEventTitle());
                    calVH.mEventTitle2.setText(mEventList.get(1).getmEventTitle());
                    calVH.mEventTitle3.setText(mEventList.get(2).getmEventTitle());
                    calVH.mEventTitle4.setText(mEventList.get(3).getmEventTitle());
                    calVH.mEventTitle5.setText(mEventList.get(4).getmEventTitle());

                    calVH.mEventDate.setText(mEventList.get(0).getmEventDate());
                    calVH.mEventDate2.setText(mEventList.get(1).getmEventDate());
                    calVH.mEventDate3.setText(mEventList.get(2).getmEventDate());
                    calVH.mEventDate4.setText(mEventList.get(3).getmEventDate());
                    calVH.mEventDate5.setText(mEventList.get(4).getmEventDate());

                    calVH.mAddEvent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CalendarEventSingleton.getInstance().addCalendarEvent(0, mContext);
                        }
                    });
                    calVH.mAddEvent2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CalendarEventSingleton.getInstance().addCalendarEvent(1, mContext);

                        }
                    });
                    calVH.mAddEvent3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CalendarEventSingleton.getInstance().addCalendarEvent(2, mContext);

                        }
                    });
                    calVH.mAddEvent4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CalendarEventSingleton.getInstance().addCalendarEvent(3, mContext);

                        }
                    });
                    calVH.mAddEvent5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CalendarEventSingleton.getInstance().addCalendarEvent(4, mContext);

                        }
                    });
                }
                break;



        }

    }

    @Override
    public int getItemCount() {
        return mEventList.size();
//        return mList.size();
    }

    @Override
    public void onClick(View view) {

    }

//    @Override
//    public void onClick(View view) {
////        switch (mViewType){
////            case 0:
////                AVH.mLargeTextView.setVisibility(View.VISIBLE);
//                AVH.mSmallTextView.setVisibility(View.VISIBLE);
//                AVH.mSmallTextView.setLayoutParams(new FrameLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, 200));
//        Toast.makeText(mContext,"click" + view.getHeight(), Toast.LENGTH_SHORT).show();
////        }
//    }




//
//    @Override
//    public void onBindViewHolder(MainRvViewHolder holder, final int position) {
////        holder.mTextView.setText(list.get(position));
//
//        holder.mAddEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //you should do this in an async task
//                new CalendarEvent(eventList, mContext).addCalendarEvent(position);
//
//            }
//        });
//        holder.mCalendarCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse(eventList.get(position).getmDetailUrl());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                mContext.startActivity(intent);
//            }
//        });
//        holder.mEventTitle.setText(eventList.get(position).getmEventTitle());
//        holder.mEventDate.setText(eventList.get(position).getmEventDate());
//
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return eventList.size();
//    }
}
