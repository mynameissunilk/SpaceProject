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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public ArrayList<String> mInnerText;
    public ArrayList<String> mList;
    public ArrayList<String> mShortText;
    int mViewType;
    RecyclerView.ViewHolder mViewHolder;
    AstronautViewHolder AVH;

    // for one viewGroup
    public MainRvAdapter(int viewType, ArrayList<String> shortText, ArrayList<String> innerText
            , ArrayList<String> list) {
        this.mList = list;
        mViewType = viewType;
        this.mShortText = shortText;
        this.mInnerText = innerText;
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
                            int num = AVH.mSmallTextView.getHeight();
                            if (num < 300) {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(400, 300);
                                AVH.mSmallTextView.setLayoutParams(parms);
                            }
                            else {
                                Toast.makeText(mContext, "height = " + view.getHeight(), Toast.LENGTH_SHORT).show();
                                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(400, 0);
                                AVH.mSmallTextView.setLayoutParams(parms);
                            }
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
}
