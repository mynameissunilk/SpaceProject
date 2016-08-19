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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.TwitterObj;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public List<CardObject> mList;
    RecyclerView.ViewHolder mViewHolder;
    private static final String TAG = "MainRvAdapter";
    TwitterObj twitterObj;
    NprArticle nprObj;
    GuardianArticle guardianObj;
    APOD nasaObj;
    CalendarEventObject calendarObj;
    Context mContext;
    private final int TWITTER = 0, GUARDIAN = 1, NPR = 2, CALENDAR = 3, NASA = 4;
    int mCounterNPR, mCounterNasa, mCounterGuardian, mCounterCalendar, mCounterTwitter;

    public MainRvAdapter(List<CardObject> list) {
        this.mList = list;
        //instantiate counters for section headings
        mCounterNPR = 0;
        mCounterCalendar = 0;
        mCounterNasa = 0;
        mCounterGuardian = 0;
        mCounterTwitter = 0;
    }

    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof NprArticle) {
            return NPR;
        } else if (mList.get(position) instanceof TwitterObj) {
            return TWITTER;
        } else if (mList.get(position) instanceof GuardianArticle) {
            return GUARDIAN;
        } else if (mList.get(position) instanceof APOD) {
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
            case NPR:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_npr, parent, false);
                mViewHolder = new NPRViewHolder(view2);
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
        if (mList.get(position) instanceof NprArticle) {
            nprObj = (NprArticle) mList.get(position);
        } else if (mList.get(position) instanceof TwitterObj) {
            twitterObj = (TwitterObj) mList.get(position);
        } else if (mList.get(position) instanceof GuardianArticle) {
            guardianObj = (GuardianArticle) mList.get(position);
        } else if (mList.get(position) instanceof CalendarEventObject) {
            calendarObj = (CalendarEventObject) mList.get(position);
        } else if (mList.get(position) instanceof APOD) {
            nasaObj = (APOD) mList.get(position);
        }

        // populating the view type
        switch (mViewHolder.getItemViewType()) {
            default:
            case TWITTER:
                if (mList.get(position) instanceof TwitterObj) {
                    TwitterViewHolder TVH = (TwitterViewHolder) holder;
                    Log.i(TAG, "twitter counter is: " + mCounterCalendar);

                    if (mCounterTwitter == 0) {
                        //insert section header
//                        int width = TVH.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        MVH.mSectionHeader.setLayoutParams(params);
                        TVH.mSectionHeader.setText("See What NASA Astronauts\nAre Tweeting About");
                        mCounterTwitter++;
                    }

                    //set card info
                    TVH.mName.setText(twitterObj.getmName());
                    TVH.mUser.setText(twitterObj.getmUser());
                    TVH.mDate.setText(twitterObj.getmDate());
                    TVH.mTweet.setText(twitterObj.getmTweet());
                    Picasso.with(mContext).load(twitterObj.getmUrl()).into(TVH.mUrl);

                    //open the url in a webview
                    TVH.mName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, WebViewActivity.class);
                            intent.putExtra("url", twitterObj.getmUrl());
                            mContext.startActivity(intent);
                        }
                    });

                }
                break;

            case NPR:
                if (mList.get(position) instanceof NprArticle) {
                    final NPRViewHolder NPR = (NPRViewHolder) holder;
                    Log.i(TAG, "npr counter is: " + mCounterCalendar);
                    if (mCounterNPR == 0) {
                        //insert section header
//                        int width = NPR.mSectionHeader.getWidth();
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        NPR.mSectionHeader.setLayoutParams(params);

                        NPR.mSectionHeader.setText("Stories from NPR");
                        mCounterNPR++;
                    }
                    Picasso.with(mContext)
                            .load("http://sleepypod.com/latest/wp-content/uploads/2016/03/the-new-york-times.jpg")
                            .into(NPR.mImageViewLarge);
                    NPR.mHeadder.setText(nprObj.getTitle());

                    NPR.mHeadder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i(TAG, "onClick: " + position);
                            int num = view.getHeight();
                            int num2 = view.getWidth();

                            if (num < 200) {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(num2, 200);
                                params.setMargins(40, 30, 40, 30);
                                view.setLayoutParams(params);

                                LinearLayout.LayoutParams bufferParams1 = new LinearLayout.LayoutParams(num2, ViewGroup.LayoutParams.WRAP_CONTENT);
                                bufferParams1.setMargins(30, 5, 30, 5);
                                NPR.mSnippet.setLayoutParams(bufferParams1);
                                NPR.mDate.setLayoutParams(bufferParams1);

                                NPR.mSnippet.setText(nprObj.getParagraph());
                                NPR.mDate.setText(nprObj.getDate());
                                Log.i(TAG, "onBindViewHolder: the headder   " + NPR.mHeadder.getText().toString());
                                Log.i(TAG, "onBindViewHolder: the snippet   " + NPR.mSnippet.getText().toString());
                                Log.i(TAG, "onBindViewHolder: the date      " + NPR.mDate.getText().toString());

                            } else {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(num2, 199);
                                params.setMargins(30, 0, 30, 0);
                                view.setLayoutParams(params);

                                LinearLayout.LayoutParams bufferParams = new LinearLayout.LayoutParams(num2, 0);
                                NPR.mSnippet.setLayoutParams(bufferParams);
                                NPR.mDate.setLayoutParams(bufferParams);
                            }
                        }
                    });

                    //to open the url in a webview
                    NPR.mImageViewLarge.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, WebViewActivity.class);
                            intent.putExtra("url", nprObj.getURL());
                            mContext.startActivity(intent);
                        }
                    });

                }
                break;

            case GUARDIAN:
                if (mList.get(position) instanceof GuardianArticle) {
                    final GuardianViewHolder GVH = (GuardianViewHolder) holder;
                    Log.i(TAG, "guardian counter is: " + mCounterCalendar);
                    if (mCounterGuardian == 0) {
                        //insert section header
                        GVH.mSectionHeader.setText("Stories from The Guardian");
                        mCounterGuardian++;
                    }

                    GVH.mTitle.setText(guardianObj.getTitle());
                    GVH.mShare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "Sharing " + guardianObj.getTitle(), Toast.LENGTH_SHORT).show();

                            //code for share feature
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, guardianObj.getURL());
                            shareIntent.setType("text/plain");
                            mContext.startActivity(shareIntent);
                        }
                    });

                    GVH.mLogo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, WebViewActivity.class);
                            intent.putExtra("url", guardianObj.getURL());
                            mContext.startActivity(intent);
                            Toast.makeText(mContext, "find out more about " + guardianObj.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                break;

            case NASA:
                if (mList.get(position) instanceof APOD) {
                    final NasaViewHolder NVH = (NasaViewHolder) holder;
                    Picasso.with(mContext)
                            .load(nasaObj.getURL())
                            .into(NVH.mNasaImageViewLarge);
                    NVH.mTitleNasa.setText(nasaObj.getTitle());
                    //need a place for explanation


                    //to open the url in a webview
                    NVH.mNasaImageViewLarge.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, WebViewActivity.class);
                            intent.putExtra("url", nasaObj.getURL());
                            mContext.startActivity(intent);
                        }
                    });

                }

            case CALENDAR:
                if (mList.get(position) instanceof CalendarEventObject) {
                    final CalendarViewHolder calVH = (CalendarViewHolder) holder;
                    calendarObj = (CalendarEventObject) mList.get(position);

                    Log.i(TAG, "calendar counter is: " + mCounterCalendar);

                    if (mCounterCalendar == 0) {
                        //insert section header
                        calVH.mSectionHeader.setText("Plan Ahead: Add Upcoming \n Astronomical Events to Your Calendar");
                        mCounterCalendar++;
                    }
                    //two choices: either i dont include the following code, and the header shows up before the first and last ones (?)
                    //or i do include the following code, and the header disappears when you scroll back up. so there's no header at all
                    else if (mCounterCalendar == 1){
                        calVH.mSectionHeader.setText("");
                    }

                    calVH.mEventTitle.setText(calendarObj.getmEventTitle());
                    calVH.mEventDate.setText(calendarObj.getmEventDate());


                    calVH.mAddEvent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            calendarObj = (CalendarEventObject) mList.get(position);
                            CardObjSingleton.getInstance().addEventToCalendar(calendarObj, mContext);
                            Log.i(TAG, "addevent click: " + calendarObj.getmEventTitle());

                        }
                    });

                    //open detail url in webview
                    calVH.mCalendarCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            calendarObj = (CalendarEventObject) mList.get(position);
                            Intent intent = new Intent(mContext, WebViewActivity.class);
                            intent.putExtra("url", calendarObj.getmDetailUrl());
                            mContext.startActivity(intent);
                            Toast.makeText(mContext, "" + calendarObj.getmEventTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    calVH.mShare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            calendarObj = (CalendarEventObject) mList.get(position);
                            Toast.makeText(mContext, "Sharing url: "+ calendarObj.getmDetailUrl(), Toast.LENGTH_SHORT).show();

//
//                            //share feature
//                            Intent shareIntent = new Intent();
//                            shareIntent.setAction(Intent.ACTION_SEND);
//                            shareIntent.putExtra(Intent.EXTRA_TEXT, calendarObj.getmDetailUrl());
//                            shareIntent.setType("text/plain");
//                            mContext.startActivity(shareIntent);
//                            Toast.makeText(mContext, "Sharing " + calendarObj.getmEventTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
