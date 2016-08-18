package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sunil.project3.R;

/**
 * Created by owlslubic on 8/16/16.
 */
public class GuardianViewHolder extends RecyclerView.ViewHolder {
    TextView mBufferInner, mTitle;
    ImageView mImageViewLarge;
    TextView mSectionHeader;

    public GuardianViewHolder(View itemView) {
        super(itemView);
        mImageViewLarge = (ImageView) itemView.findViewById(R.id.image_child_large);
        mTitle = (TextView) itemView.findViewById(R.id.title_text_guardian);
//        mBufferInner = (TextView) itemView.findViewById(R.id.bufferInner);


        mSectionHeader = (TextView) itemView.findViewById(R.id.section_header_guardian);
    }
}
