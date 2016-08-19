package sunil.project3;

import android.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.R;

/**
 * Created by owlslubic on 8/16/16.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView mEventTitle,mEventDate, mSectionHeader;
    public ImageButton mAddEvent;
    public CardView mCalendarCard;

    public CalendarViewHolder(final View itemView) {
        super(itemView);
        mCalendarCard = (CardView) itemView.findViewById(R.id.calendar_single_event_card);

        mEventDate = (TextView) itemView.findViewById(R.id.textview_calendar_event_date);
        mEventTitle = (TextView) itemView.findViewById(R.id.textview_calendar_event_title);
        mAddEvent = (ImageButton) itemView.findViewById(R.id.imagebutton_add_to_calendar);


        mSectionHeader = (TextView) itemView.findViewById(R.id.section_header_calendar);



    }
}
