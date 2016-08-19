package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sunil.project3.NPR.Text;

/**
 * Created by owlslubic on 8/16/16.
 */
public class NasaViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleNasa, mBufferInner, mExplanation;
    ImageView mNasaImageViewLarge;

    TextView mSectionHeader;

    public NasaViewHolder(View itemView) {
        super(itemView);
        mNasaImageViewLarge = (ImageView) itemView.findViewById(R.id.nasa_image);
        mTitleNasa = (TextView) itemView.findViewById(R.id.nasa_title);
        mExplanation = (TextView) itemView.findViewById(R.id.nasa_explanation);
//        mBufferInner = (TextView) itemView.findViewById(R.id.bufferInner);

        mSectionHeader = (TextView) itemView.findViewById(R.id.section_header_nasa);
    }
}
