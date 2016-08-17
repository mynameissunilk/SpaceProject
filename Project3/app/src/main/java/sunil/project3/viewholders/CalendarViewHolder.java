package sunil.project3.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import sunil.project3.R;

/**
 * Created by owlslubic on 8/16/16.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView mEventTitle, mEventTitle2, mEventTitle3,mEventTitle4,mEventTitle5,mEventDate, mEventDate2, mEventDate3, mEventDate4, mEventDate5;
    public ImageButton mAddEvent, mAddEvent2, mAddEvent3, mAddEvent4, mAddEvent5;
    public CardView mCalendarCard;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        mCalendarCard = (CardView) itemView.findViewById(R.id.calendar_cardview);

        mEventDate = (TextView) itemView.findViewById(R.id.textview_calendar_event_date);
        mEventTitle = (TextView) itemView.findViewById(R.id.textview_calendar_event_title);
        mAddEvent = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar);

//        mEventDate2 = (TextView) itemView.findViewById(R.id.textview_calendar_event_date2);
//        mEventTitle2 = (TextView) itemView.findViewById(R.id.textview_calendar_event_title2);
//        mAddEvent2 = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar2);
//
//        mEventDate3 = (TextView) itemView.findViewById(R.id.textview_calendar_event_date3);
//        mEventTitle3 = (TextView) itemView.findViewById(R.id.textview_calendar_event_title3);
//        mAddEvent3 = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar3);
//
//        mEventDate4 = (TextView) itemView.findViewById(R.id.textview_calendar_event_date4);
//        mEventTitle4 = (TextView) itemView.findViewById(R.id.textview_calendar_event_title4);
//        mAddEvent4 = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar4);
//
//        mEventDate5 = (TextView) itemView.findViewById(R.id.textview_calendar_event_date5);
//        mEventTitle5 = (TextView) itemView.findViewById(R.id.textview_calendar_event_title5);
//        mAddEvent5 = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar5);

    }
}
