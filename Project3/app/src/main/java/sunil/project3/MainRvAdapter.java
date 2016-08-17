package sunil.project3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.GuardianObj;
import sunil.project3.CardObjects.NYTObj;
import sunil.project3.CardObjects.NasaObj;
import sunil.project3.CardObjects.TwitterObj;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
//public class MainRvAdapter extends BaseAdapter implements View.OnClickListener {
    public List<CardObject> mList;
    RecyclerView.ViewHolder mViewHolder;
    ArrayList<CalendarEventObject> mEventList;
    private static final String TAG = "MainRvAdapter";
    CalendarEventObject calendarObj;
    TwitterObj twitterObj;
    NYTObj nytObj;
    GuardianObj guardianObj;
    NasaObj nasaObj;
//    AstronautViewHolder AVH;
    CalendarViewHolder calVH;
    Context mContext;
    private final int TWITTER = 0, GUARDIAN = 1, NYT = 2, CALENDAR = 3, NASA = 4;

    public MainRvAdapter(List<CardObject> list) {
        this.mList = list;
    }
//
    public int getViewTypeCount() {
        return 4;
    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }

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
        } else if (mList.get(position) instanceof NasaObj) {
            return NASA;
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
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_nyt, parent, false);
                mViewHolder = new NYTViewHolder(view2);
                break;
            case GUARDIAN:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_guardian, parent, false);
                mViewHolder = new GuardianViewHolder(view3);
                break;
            case NASA:
                View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_nasa, parent, false);
                mViewHolder = new NasaViewHolder(view4);
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
        } else if (mList.get(position) instanceof NasaObj) {
            nasaObj = (NasaObj) mList.get(position);
        }


        // populating the view type
        switch (mViewHolder.getItemViewType()) {
            default:
            case TWITTER:
//                if(getItemCount())
                if (mList.get(position) instanceof TwitterObj) {
                    MainRvViewHolder MVH = (MainRvViewHolder) holder;
                    MVH.mT1.setText(twitterObj.getmName());
                }
//                MVH.mT2.setText(array.get(position).toString());
//                MVH.mT3.setText(array.get(position).toString());
//                MVH.mT4.setText(array.get(position).toString());
//                MVH.mT5.setText(array.get(position).toString());
                break;
            case NYT:
//                MainRvViewHolder cannot be cast to AstronautViewHolder
                if (mList.get(position) instanceof NYTObj) {
                    final NYTViewHolder NYT = (NYTViewHolder) holder;
                    Picasso.with(mContext)
                            .load("http://sleepypod.com/latest/wp-content/uploads/2016/03/the-new-york-times.jpg")
                            .into(NYT.mImageViewLarge);
                    NYT.mHeadder.setText(nytObj.getmHeadline());

                    NYT.mHeadder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i(TAG, "onClick: " + position);
                            int num = view.getHeight();
                            int num2 = view.getWidth();
//
                            if (num < 200) {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(num2, 200);
                                params.setMargins(30,30,30,30);
                                view.setLayoutParams(params);

                                LinearLayout.LayoutParams bufferParams1 = new LinearLayout.LayoutParams(num2, ViewGroup.LayoutParams.WRAP_CONTENT);
                                bufferParams1.setMargins(30,5,30,5);
                                NYT.mSnippet.setLayoutParams(bufferParams1);
                                NYT.mDate.setLayoutParams(bufferParams1);

                                NYT.mSnippet.setText(nytObj.getmSnippet());
                                NYT.mDate.setText(nytObj.getmDate());
                                Log.i(TAG, "onBindViewHolder: the headder   " + NYT.mHeadder.getText().toString());
                                Log.i(TAG, "onBindViewHolder: the snippet   " + NYT.mSnippet.getText().toString());
                                Log.i(TAG, "onBindViewHolder: the date      " + NYT.mDate.getText().toString());

                            } else {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(num2, 199);
                                params.setMargins(30,0,30,0);
                                view.setLayoutParams(params);

                                LinearLayout.LayoutParams bufferParams = new LinearLayout.LayoutParams(num2, 0);
                                NYT.mSnippet.setLayoutParams(bufferParams);
                                NYT.mDate.setLayoutParams(bufferParams);
                            }
                        }
                    });
                }
                break;

            case GUARDIAN:
                if (mList.get(position) instanceof GuardianObj) {
                    final GuardianViewHolder GVH = (GuardianViewHolder) holder;
                    Picasso.with(mContext)
                            .load("http://images-cdn.moviepilot.com/images/c_fill,h_1080,w_1920/t_mp_quality/u58jjbhpjnf8ccpwysct/first-star-trek-beyond-images-tease-new-friends-and-new-foes-spoilers-star-trek-3-g-752249.jpg")
                            .into(GVH.mImageViewLarge);
                    GVH.mTitle.setText(guardianObj.getmTitle());

                    GVH.mTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i(TAG, "onClick: " + position);

                        }
                    });
                }
                break;

            case NASA:
                if (mList.get(position) instanceof NasaObj){
                    final  NasaViewHolder NVH = (NasaViewHolder) holder;
                    Picasso.with(mContext)
                            .load(nasaObj.getmUrl())
                            .into(NVH.mNasaImageViewLarge);
                    NVH.mTitleNasa.setText(nasaObj.getmTitle());

                }

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
