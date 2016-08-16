package sunil.project3;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvViewHolder extends RecyclerView.ViewHolder{
    TextView mTextView;
    TextView mEventTitle, mEventDate;
    ImageButton mAddEvent;
    CardView mCalendarCard;


    public MainRvViewHolder(View itemView) {
        super(itemView);

        //temp view on card
        mTextView= (TextView) itemView.findViewById(R.id.textview);

        //calendar card
        mEventDate = (TextView) itemView.findViewById(R.id.textview_calendar_event_date);
        mEventTitle = (TextView) itemView.findViewById(R.id.textview_calendar_event_title);
        mAddEvent = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar);
        mCalendarCard = (CardView) itemView.findViewById(R.id.calendar_cardview);
    }
}


