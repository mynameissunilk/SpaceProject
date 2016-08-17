package sunil.project3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CalendarEventSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.GuardianObj;
import sunil.project3.CardObjects.NYTObj;
import sunil.project3.CardObjects.NasaObj;
import sunil.project3.CardObjects.TwitterObj;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public List<CardObject> mList;
    RecyclerView.ViewHolder mViewHolder;
    ArrayList<CalendarEventObject> mEventList;
    private static final String TAG = "MainRvAdapter";
    TwitterObj twitterObj;
    NYTObj nytObj;
    GuardianObj guardianObj;
    CalendarEventObject calendarObj;
    NasaObj nasaObj;
    CalendarViewHolder calVH;
    Context mContext;
    private final int TWITTER = 0, GUARDIAN = 1, NYT = 2, CALENDAR = 3, NASA = 4;

    //instantiate counter for section headings
    int mCounterNYT, mCounterNasa, mCounterGuardian, mCounterCalendar, mCounterTwitter;

    public MainRvAdapter(List<CardObject> list) {
        this.mList = list;
        mCounterNYT = 1;
        mCounterCalendar = 1;
        mCounterNasa = 1;
        mCounterGuardian = 1;
        mCounterTwitter = 1;
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
        } else if (mList.get(position) instanceof NasaObj) {
            return NASA;
        } else if (mList.get(position) instanceof CalendarEventObject) {
            return CALENDAR;
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_twitter, parent, false);
                mViewHolder = new TwitterViewHolder(view);
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
            case CALENDAR:
                View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_single_event_card, parent, false);
                mViewHolder = new CalendarViewHolder(view5);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //determining type of object
        if (mList.get(position) instanceof NYTObj) {
            nytObj = (NYTObj) mList.get(position);
        } else if (mList.get(position) instanceof TwitterObj) {
            twitterObj = (TwitterObj) mList.get(position);
        } else if (mList.get(position) instanceof GuardianObj) {
            guardianObj = (GuardianObj) mList.get(position);
        } else if (mList.get(position) instanceof NasaObj) {
            nasaObj = (NasaObj) mList.get(position);
        } else if (mList.get(position) instanceof CalendarEventObject) {
            calendarObj = (CalendarEventObject) mList.get(position);
        }


        // populating the view type
        switch (mViewHolder.getItemViewType()) {
            default:
            case TWITTER:
//                if(getItemCount())

                if (mList.get(position) instanceof TwitterObj) {
                    TwitterViewHolder MVH = (TwitterViewHolder) holder;
                    Log.i(TAG, "twitter counter is: " + mCounterCalendar);

                    if (mCounterTwitter == 1) {
                        //insert section header
                        int width = MVH.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        MVH.mSectionHeader.setLayoutParams(params);
                        MVH.mSectionHeader.setText("See What Astronauts Have to Say");
//                        MVH.mSectionHeader.setVisibility(View.VISIBLE);
                        mCounterTwitter++;
                    }

                    MVH.mName.setText(twitterObj.getmName());
                    MVH.mUser.setText(twitterObj.getmUser());
                    MVH.mDate.setText(twitterObj.getmDate());
                    MVH.mTweet.setText(twitterObj.getmTweet());
                    Picasso.with(mContext).load(twitterObj.getmUrl()).into(MVH.mUrl);
                }
//                MVH.mT2.setText(array.get(position).toString());
//                MVH.mT3.setText(array.get(position).toString());
//                MVH.mT4.setText(array.get(position).toString());
//                MVH.mT5.setText(array.get(position).toString());
                break;
            case NYT:
                if (mList.get(position) instanceof NYTObj) {
                    final NYTViewHolder NYT = (NYTViewHolder) holder;
                    Log.i(TAG, "nyt counter is: " + mCounterCalendar);
                    if (mCounterNYT == 1) {
                        //insert section header

//                        int width = NYT.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        NYT.mSectionHeader.setLayoutParams(params);

                        NYT.mSectionHeader.setText("Stories from The New York Times");
//                        NYT.mSectionHeader.setVisibility(View.VISIBLE);
                        mCounterNYT++;
                    }

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
                                params.setMargins(40, 30, 40, 30);
                                view.setLayoutParams(params);

                                LinearLayout.LayoutParams bufferParams1 = new LinearLayout.LayoutParams(num2, ViewGroup.LayoutParams.WRAP_CONTENT);
                                bufferParams1.setMargins(30, 5, 30, 5);
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
                                params.setMargins(30, 0, 30, 0);
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
                    Log.i(TAG, "guardian counter is: " + mCounterCalendar);
                    if (mCounterGuardian == 1) {
                        //insert section header

//                        int width = GVH.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        GVH.mSectionHeader.setLayoutParams(params);

                        GVH.mSectionHeader.setText("Stories from The Guardian");
//                        GVH.mSectionHeader.setVisibility(View.VISIBLE);
                        mCounterGuardian++;
                    }


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
                if (mList.get(position) instanceof NasaObj) {
                    final NasaViewHolder NVH = (NasaViewHolder) holder;
                    Log.i(TAG, "nasa counter is: " + mCounterCalendar);
                    if (mCounterNasa == 1) {
                        //insert section header
//                        int width = NVH.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        NVH.mSectionHeader.setLayoutParams(params);


                        NVH.mSectionHeader.setText("NASA's Picture of the Day");
//                        NVH.mSectionHeader.setVisibility(View.VISIBLE);
                        mCounterNasa++;
                    }
                    Picasso.with(mContext)
                            .load(nasaObj.getmUrl())
                            .into(NVH.mNasaImageViewLarge);
                    NVH.mTitleNasa.setText(nasaObj.getmTitle());
                }

            case CALENDAR:

                if (mList.get(position) instanceof CalendarEventObject) {
                    calVH = (CalendarViewHolder) holder;
//                    mEventList = CalendarEventSingleton.getInstance().getEventList();
//
//                    if (mEventList.size() < 9) {
                    Log.i(TAG, "calendar counter is: " + mCounterCalendar);
                        if (mCounterCalendar == 1) {

                            //insert section header

//                            int width = calVH.mSectionHeader.getWidth();
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                            calVH.mSectionHeader.setLayoutParams(params);

                            calVH.mSectionHeader.setText("Add Upcoming Astronomical Events to Your Calendar");
//                            calVH.mSectionHeader.setVisibility(View.VISIBLE);
                            mCounterCalendar++;
                        }
                        calVH.mEventTitle.setText(calendarObj.getmEventTitle());
                        calVH.mEventDate.setText(calendarObj.getmEventDate());

                        calVH.mAddEvent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CalendarEventSingleton.getInstance().addCalendarEvent(position, mContext);
                            }
                        });
                        calVH.mCalendarCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(calendarObj.getmDetailUrl()));
                                mContext.startActivity(intent);
                            }
                        });
                    }
//                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {

    }
}
