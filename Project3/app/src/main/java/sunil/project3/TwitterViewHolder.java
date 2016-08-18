package sunil.project3;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 8/16/16.
 */
public class TwitterViewHolder extends RecyclerView.ViewHolder {


    TextView mName, mTweet, mDate, mUser;
    ImageView mUrl;

    public TwitterViewHolder(View itemView) {
        super(itemView);
        mUrl = (ImageView) itemView.findViewById(R.id.url_twitter);
        mName = (TextView) itemView.findViewById(R.id.name_twitter);
        mDate = (TextView) itemView.findViewById(R.id.date_twitter);
        mUser = (TextView) itemView.findViewById(R.id.user_twitter);
        mTweet = (TextView) itemView.findViewById(R.id.tweet_twitter);
    }
}