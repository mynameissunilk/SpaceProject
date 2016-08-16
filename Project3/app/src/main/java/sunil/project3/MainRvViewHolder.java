package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvViewHolder extends RecyclerView.ViewHolder{
    TextView mTextView,mBuffer;
    TextView mT1,mT2,mT3,mT4,mT5;

    public MainRvViewHolder(View itemView) {
        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.textview);
        mT1 = (TextView) itemView.findViewById(R.id.text_view1);
        mT2 = (TextView) itemView.findViewById(R.id.text_view2);
        mT3 = (TextView) itemView.findViewById(R.id.text_view3);
        mT4 = (TextView) itemView.findViewById(R.id.text_view4);
        mT5 = (TextView) itemView.findViewById(R.id.text_view5);
        mBuffer = (TextView) itemView.findViewById(R.id.buffer);
    }
}
