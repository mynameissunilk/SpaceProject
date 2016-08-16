package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ander on 8/16/2016.
 */
public class AstronautViewHolder extends RecyclerView.ViewHolder{
    TextView mLargeTextView, mSmallTextView;
    ImageView mImageViewLarge;
//    TextView mLargeTextView, mSmallTextView, mImageText;
//    ImageView mImageViewLarge;

    public AstronautViewHolder(View itemView) {
        super(itemView);

        mImageViewLarge = (ImageView) itemView.findViewById(R.id.image_child_large);
        mSmallTextView = (TextView) itemView.findViewById(R.id.short_text);
//        mLargeTextView = (TextView) itemView.findViewById(R.id.long_text);
//        mImageViewSmaller = (ImageView) itemView.findViewById(R.id.image_child_small);
//        mImageText = (ImageView) itemView.findViewById(R.id.image_child_small);

    }
}
