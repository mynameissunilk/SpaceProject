package sunil.project3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvViewHolder extends RecyclerView.ViewHolder{
    TextView mTextView;
    public MainRvViewHolder(View itemView) {
        super(itemView);

        //temp view on card
        mTextView= (TextView) itemView.findViewById(R.id.textview);
    }
}
