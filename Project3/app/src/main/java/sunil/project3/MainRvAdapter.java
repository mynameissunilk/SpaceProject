package sunil.project3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.GuardianObj;
import sunil.project3.CardObjects.NYTObj;
import sunil.project3.CardObjects.TwitterObj;

import sunil.project3.viewholders.CalendarViewHolder;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public List<CardObject> mList;
    RecyclerView.ViewHolder mViewHolder;
    ArrayList<CalendarEventObject> mEventList;
    private static final String TAG = "MainRvAdapter";
    CalendarEventObject calendarObj;
    TwitterObj twitterObj;
    NYTObj nytObj;
    GuardianObj guardianObj;
    AstronautViewHolder AVH;
    CalendarViewHolder calVH;
    Context mContext;
    private final int TWITTER = 0, GUARDIAN = 1, NYT = 2, CALENDAR = 3;

    public MainRvAdapter(List<CardObject> list) {
        this.mList = list;
    }


    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof NYTObj) {
            return NYT;
        } else if (mList.get(position) instanceof TwitterObj) {
            return TWITTER;
        } else if (mList.get(position) instanceof GuardianObj) {
            return GUARDIAN;
//        }else if (mList.get(position) instanceof CalendarEventObject){
//            return CALENDAR;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // retrieving the context of this adapter
        mContext = parent.getContext();

        // choosing and inflating the view type
        switch (viewType) {
            case TWITTER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                mViewHolder = new MainRvViewHolder(view);
                break;
            case NYT:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2, parent, false);
                mViewHolder = new AstronautViewHolder(view2);
                break;
            case GUARDIAN:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2, parent, false);
                mViewHolder = new AstronautViewHolder(view3);
                break;
//            case CALENDAR:
//                View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cardview, parent, false);
//                mViewHolder = new CalendarViewHolder(view4);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        //determining type of object
        if (mList.get(position) instanceof NYTObj) {
            nytObj = (NYTObj) mList.get(position);
        } else if (mList.get(position) instanceof TwitterObj) {
            twitterObj = (TwitterObj) mList.get(position);
        } else if (mList.get(position) instanceof GuardianObj) {
            guardianObj = (GuardianObj) mList.get(position);
//        } else if (mList.get(position) instanceof CalendarEventObject) {
//            calendarObj = (CalendarEventObject) mList.get(position);
        }


        // populating the view type
        switch (mViewHolder.getItemViewType()) {
            default:
            case TWITTER:
                MainRvViewHolder MVH = (MainRvViewHolder) holder;
                MVH.mT1.setText(twitterObj.getmName());
//                MVH.mT2.setText(array.get(position).toString());
//                MVH.mT3.setText(array.get(position).toString());
//                MVH.mT4.setText(array.get(position).toString());
//                MVH.mT5.setText(array.get(position).toString());
                break;
            case NYT:

//                MainRvViewHolder cannot be cast to sunil.project3.AstronautViewHolder

                AVH = (AstronautViewHolder) holder;
                AVH.mImageViewLarge.setImageResource(android.R.drawable.ic_input_add);
                AVH.mSmallTextView.setText(nytObj.getmSnippet());
//                AVH.mSmallTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.i(TAG, "onClick: " + position);
//                        int num = view.getHeight();
//                        int num2 = view.getWidth();
//                        if (num < 200) {
//                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
//                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 200);
//                            view.setLayoutParams(parms);
//
//                        } else {
//                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
//                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 75);
//                            view.setLayoutParams(parms);
//                        }
//                    }
//                });
                break;

            case GUARDIAN:
                AVH = (AstronautViewHolder) holder;
                AVH.mImageViewLarge.setImageResource(android.R.drawable.ic_input_add);
                AVH.mSmallTextView.setText(guardianObj.getmTitle());
//                AVH.mSmallTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.i(TAG, "onClick: " + position);
//                        int num = view.getHeight();
//                        int num2 = view.getWidth();
//                        if (num < 200) {
//                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
//                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 200);
//                            view.setLayoutParams(parms);
//
//                        } else {
//                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
//                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 75);
//                            view.setLayoutParams(parms);
//                        }
//                    }
//                });
                break;
//            case CALENDAR:
//                calVH = (CalendarViewHolder) holder;
//                if (mEventList.size() <6) {
//                    calVH.mEventTitle.setText(mEventList.get(0).getmEventTitle());
//                    calVH.mEventTitle2.setText(mEventList.get(1).getmEventTitle());
//                    calVH.mEventTitle3.setText(mEventList.get(2).getmEventTitle());
//                    calVH.mEventTitle4.setText(mEventList.get(3).getmEventTitle());
//                    calVH.mEventTitle5.setText(mEventList.get(4).getmEventTitle());
//
//                    calVH.mEventDate.setText(mEventList.get(0).getmEventDate());
//                    calVH.mEventDate2.setText(mEventList.get(1).getmEventDate());
//                    calVH.mEventDate3.setText(mEventList.get(2).getmEventDate());
//                    calVH.mEventDate4.setText(mEventList.get(3).getmEventDate());
//                    calVH.mEventDate5.setText(mEventList.get(4).getmEventDate());
//
//                }
//                break;
//
//
//            calVH.mAddEvent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CalendarEventSingleton.getInstance().addCalendarEvent(0, mContext);
//                }
//            });
//            calVH.mAddEvent2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CalendarEventSingleton.getInstance().addCalendarEvent(1, mContext);
//
//                }
//            });
//            calVH.mAddEvent3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CalendarEventSingleton.getInstance().addCalendarEvent(2, mContext);
//
//                }
//            });
//            calVH.mAddEvent4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CalendarEventSingleton.getInstance().addCalendarEvent(3, mContext);
//
//                }
//            });
//            calVH.mAddEvent5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CalendarEventSingleton.getInstance().addCalendarEvent(4, mContext);
//
//                }
//            });
//        }
//        break;


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

    @Override
    public int getItemCount () {
        return mList.size();
    }

    @Override
    public void onClick (View view){

    }
}
