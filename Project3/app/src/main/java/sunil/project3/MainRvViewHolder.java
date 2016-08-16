package sunil.project3;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by owlslubic on 8/15/16.
 */
public class MainRvViewHolder extends RecyclerView.ViewHolder{
    TextView mEventTitle, mEventDate;
    ImageButton mAddEvent;
    CardView mCalendarCard;


    TextView mTextView,mBuffer;
    TextView mT1,mT2,mT3,mT4,mT5;

    public MainRvViewHolder(View itemView) {
        super(itemView);

        //temp view on card

        //calendar card PUT THIS SHIT IN A NEW VIEWHOLDER
        mEventDate = (TextView) itemView.findViewById(R.id.textview_calendar_event_date);
        mEventTitle = (TextView) itemView.findViewById(R.id.textview_calendar_event_title);
        mAddEvent = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar);
        mCalendarCard = (CardView) itemView.findViewById(R.id.calendar_cardview);
        mTextView = (TextView) itemView.findViewById(R.id.textview);


        mT1 = (TextView) itemView.findViewById(R.id.text_view1);
        mT2 = (TextView) itemView.findViewById(R.id.text_view2);
        mT3 = (TextView) itemView.findViewById(R.id.text_view3);
        mT4 = (TextView) itemView.findViewById(R.id.text_view4);
        mT5 = (TextView) itemView.findViewById(R.id.text_view5);
        mBuffer = (TextView) itemView.findViewById(R.id.buffer);
    }
}
