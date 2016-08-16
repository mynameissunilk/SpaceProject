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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public ArrayList<String> mInnerText;
    public ArrayList<String> mList;
    public ArrayList<String> mLargeText;
    public ArrayList<String> mShortText;
    int mViewType;
    RecyclerView.ViewHolder mViewHolder;

    // for one viewGroup
    public MainRvAdapter(int viewType, ArrayList<String> largeText, ArrayList<String> shortText, ArrayList<String> innerText
            , ArrayList<String> list) {
        this.mList = list;
        mViewType = viewType;
        this.mInnerText = innerText; this.mShortText = shortText; this.mLargeText = largeText;
    }

    // for another view group
    public MainRvAdapter(int viewType, ArrayList<String> list){
        mViewType = viewType;
        this.mList = list;
    }

    Context mContext;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // retrieving the context of this adapter
        mContext = parent.getContext();

        // choosing and inflating the view type
        switch (mViewType) {
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
                mViewHolder = new MainRvViewHolder(view);
                break;
            case 1:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2, parent, false);
                mViewHolder = new AstronautViewHolder(view2);
                break;
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

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
                AstronautViewHolder AVH = (AstronautViewHolder) holder;
                AVH.mImageView.setImageResource(android.R.drawable.ic_input_add);
                if(mShortText.size() != 0) {
                    AVH.mLargeTextView.setText(mShortText.indexOf(position));
                }
                if(mLargeText.size() != 0) {
                    AVH.mSmallTextView.setText(mLargeText.indexOf(position));
                }
                if(mInnerText.size() != 0) {
                    AVH.mInnerTextView.setText(mInnerText.indexOf(position));
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
        view.setMinimumHeight(500);
    }




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
