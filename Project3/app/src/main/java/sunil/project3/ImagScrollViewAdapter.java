package sunil.project3;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagScrollViewAdapter extends ArrayAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String> imageURLs;
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    RecyclerView.ViewHolder mViewHolder;
    private static final String TAG = "ImagScrollViewAdapter";


    public ImagScrollViewAdapter(Context context, ArrayList<String> imageUrls) {
        super(context, R.layout.horizontal_listview, imageUrls);
        mContext = context;
        this.imageURLs = imageUrls;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        RecyclerView.ViewHolder viewHolder;

        view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_listview, null);
        ImagScrollViewholder holder = new ImagScrollViewholder(view);
        holder.mImageView1 = (ImageView) view.findViewById(R.id.imageSlider1);


        Picasso.with(mContext)
                .load(imageURLs.get(position))
                .into(holder.mImageView1);

        return view;
    }


}
