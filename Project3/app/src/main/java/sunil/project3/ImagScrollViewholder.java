package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ander on 8/18/2016.
 */
public class ImagScrollViewholder extends RecyclerView.ViewHolder {
    public ImageView mImageView1, mImageView2, mImageView3, mImageView4;

    public ImagScrollViewholder(View itemView) {
        super(itemView);
        mImageView1 = (ImageView) itemView.findViewById(R.id.imageSlider1);
        mImageView2 = (ImageView) itemView.findViewById(R.id.imageSlider2);
        mImageView3 = (ImageView) itemView.findViewById(R.id.imageSlider3);
        mImageView4 = (ImageView) itemView.findViewById(R.id.imageSlider4);
    }
}
