package sunil.project3;

import android.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import sunil.project3.R;

/**
 * Created by owlslubic on 8/16/16.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView mEventTitle,mEventDate, mSectionHeader, sectionHeader;
    public ImageButton mAddEvent;
    public CardView mCalendarCard;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        mCalendarCard = (CardView) itemView.findViewById(R.id.calendar_single_event_card);

        mEventDate = (TextView) itemView.findViewById(R.id.textview_calendar_event_date);
        mEventTitle = (TextView) itemView.findViewById(R.id.textview_calendar_event_title);
        mAddEvent = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar);


        mSectionHeader = (TextView) itemView.findViewById(R.id.section_header_calendar);

//        sectionHeader = new TextView(itemView.getContext());
//
//        LinearLayout cal = (LinearLayout) itemView.findViewById(R.id.linear_layout_calendar);
//        cal.addView(sectionHeader);

    }
}
