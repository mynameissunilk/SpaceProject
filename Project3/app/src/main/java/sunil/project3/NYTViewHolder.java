package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ander on 8/17/2016.
 */
public class NYTViewHolder extends RecyclerView.ViewHolder{
    TextView mSnippet, mHeadder, mDate;
    ImageView mImageViewLarge;

    TextView mSectionHeader;

    public NYTViewHolder(View itemView) {
        super(itemView);

        mImageViewLarge = (ImageView) itemView.findViewById(R.id.image_child_large);
        mHeadder = (TextView) itemView.findViewById(R.id.short_text);
        mSnippet = (TextView) itemView.findViewById(R.id.snippetNYT);
        mDate = (TextView) itemView.findViewById(R.id.dateNYT);

        mSectionHeader = (TextView) itemView.findViewById(R.id.section_header_nyt);

    }
}
