package sunil.project3;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.GuardianObj;
import sunil.project3.CardObjects.NYTObj;
import sunil.project3.CardObjects.TwitterObj;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public ArrayList<String> mInnerText;
    //    public ArrayList<String> mList;
    public List<CardObject> mList;
    public ArrayList<String> mShortText;
    int mViewType;
    RecyclerView.ViewHolder mViewHolder;
    AstronautViewHolder AVH;
    private static final String TAG = "MainRvAdapter";
    int populationQ;
    TwitterObj twitterObj;
    NYTObj nytObj;
    GuardianObj guardianObj;

    private final int TWITTER = 0, GUARDIAN = 1, NYT = 2;

    public MainRvAdapter(List<CardObject> list) {
        this.mList = list;
    }

    Context mContext;


    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof NYTObj) {
            return NYT;
        } else if (mList.get(position) instanceof TwitterObj) {
            return TWITTER;
        } else if (mList.get(position) instanceof GuardianObj) {
            return GUARDIAN;
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
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // getting all the items in the following table
//        ArrayList array = DBHelper.getInstance(mContext).getAllItemsArrayList();

        if (mList.get(position) instanceof NYTObj) {
             twitterObj = (TwitterObj) mList.get(position);
        } else if (mList.get(position) instanceof TwitterObj) {
            nytObj = (NYTObj) mList.get(position);
        } else if (mList.get(position) instanceof GuardianObj) {
            guardianObj = (GuardianObj) mList.get(position);
        }

//        TwitterHolder twitterHolder = (TwitterHolder)mViewHolder;
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
                AVH = (AstronautViewHolder) holder;
                AVH.mImageViewLarge.setImageResource(android.R.drawable.ic_input_add);
                AVH.mSmallTextView.setText(nytObj.getmSnippet());
                AVH.mSmallTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick: " + position);
                        int num = view.getHeight();
                        int num2 = view.getWidth();
                        if (num < 200) {
                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 200);
                            view.setLayoutParams(parms);

                        } else {
                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 75);
                            view.setLayoutParams(parms);
                        }
                    }
                });
                break;
            case GUARDIAN:
                AVH = (AstronautViewHolder) holder;
                AVH.mImageViewLarge.setImageResource(android.R.drawable.ic_input_add);
                AVH.mSmallTextView.setText(guardianObj.getmTitle());
                AVH.mSmallTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick: " + position);
                        int num = view.getHeight();
                        int num2 = view.getWidth();
                        if (num < 200) {
                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 200);
                            view.setLayoutParams(parms);

                        } else {
                            Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(num2, 75);
                            view.setLayoutParams(parms);
                        }
                    }
                });
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
