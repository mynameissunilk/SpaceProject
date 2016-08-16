package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ander on 8/16/2016.
 */
public class AstronautViewHolder extends RecyclerView.ViewHolder{
    TextView mInnerTextView, mLargeTextView, mSmallTextView;
    ImageView mImageView;

    public AstronautViewHolder(View itemView) {
        super(itemView);

        mInnerTextView = (TextView) itemView.findViewById(R.id.textChild);
        mSmallTextView = (TextView) itemView.findViewById(R.id.long_text);
        mLargeTextView = (TextView) itemView.findViewById(R.id.short_text);
        mImageView = (ImageView) itemView.findViewById(R.id.imageChild);

    }
}
