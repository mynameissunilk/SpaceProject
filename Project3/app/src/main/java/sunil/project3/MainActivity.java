package sunil.project3;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public ArrayList<CalendarEventObject> eventList;


    CursorAdapter mCursorAdapter;
    TextView mT1, mT2, mT3, mT4, mT5;
    ListView mListView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        MainRvAdapter adapter = new MainRvAdapter(eventList);
        mRecyclerView.setAdapter(adapter);


        //temp card data
//        arrayList = new ArrayList<>();
//        arrayList.add("From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid. ");
//        arrayList.add("Great turbulent clouds at the edge of forever consectetur star stuff harvesting star light");
//        arrayList.add("White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number one tingling of the spine. Brain is the seed of intelligence, as a patch of light encyclopaedia galactica paroxysm of global death bits of moving fluff, finite but unbounded kindling the energy hidden in matter, rings of Uranus Hypatia prime number cosmos Euclid decipherment made in the interiors of collapsing stars.");
//        arrayList.add("Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster great turbulent clouds muse about brain is the seed of intelligence Drake Equation, venture a billion trillion rings of Uranus cosmic ocean, billions upon billions a very small stage in a vast cosmic arena prime number muse about vanquish the impossible not a sunrise but a galaxyrise rings of Uranus star stuff harvesting star light. ");
//        arrayList.add("Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very small stage in a vast cosmic arena explorations Orion's sword trillion of brilliant syntheses how far away at the edge of forever. Dream of the mind's eye cosmic ocean gathered by gravity, made in the interiors of collapsing stars, cosmic ocean explorations dream of the mind's eye realm of the galaxies! Dream of the mind's eye. Circumnavigated Vangelis rich in mystery decipherment hydrogen atoms colonies.");


        //notification
//        Intent intent = new Intent(MainActivity.this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,0);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//       // notificationBuilder.setSmallIcon();
//        notificationBuilder.setContentTitle("Don't you want to know what's going on in space?")
//                .setContentText("Come see what's new!")
//                .setAutoCancel(true)
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent);
//        //use jobscheduler to determine when to launch notification?
//        NotificationManagerCompat.from(MainActivity.this).notify(1, notificationBuilder.build());
//

//
//        DBHelper dbHelper = DBHelper.getInstance(this);
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ProviderContract.NYT.COL_1, "a");
//        contentValues.put(ProviderContract.NYT.COL_2, "b");
//        contentValues.put(ProviderContract.NYT.COL_3, "c");
//        contentValues.put(ProviderContract.NYT.COL_4, "d");
//        contentValues.put(ProviderContract.NYT.COL_5, "e");
//        getContentResolver().insert(ProviderContract.NYT.CONTENT_URI, contentValues);
//        dbHelper.addItems(contentValues);

//        mListView = (ListView) findViewById(R.id.listView);
//
//        Cursor cursor = DBHelper.getInstance(MainActivity.this).getAllItems();
//
//        mCursorAdapter = new CursorAdapter(MainActivity.this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
//            @Override
//            public View newView(Context context, Cursor cursor, ViewGroup parent) {
//                return LayoutInflater.from(MainActivity.this).inflate(R.layout.list_view_items, parent,false);
//            }
//
//            @Override
//            public void bindView(View view, Context context, Cursor cursor) {
//
//                mT1 = (TextView) view.findViewById(R.id.text_view1);
//                mT2 = (TextView) view.findViewById(R.id.text_view2);
//                mT3 = (TextView) view.findViewById(R.id.text_view3);
//                mT4 = (TextView) view.findViewById(R.id.text_view4);
//                mT5 = (TextView) view.findViewById(R.id.text_view5);
//
//                mT1.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_1)));
//                mT2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_2)));
//                mT3.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_3)));
//                mT4.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_4)));
//                mT5.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_5)));
//
//                Log.i(TAG, "bindView: " + mT1.getText().toString());
//
//            }
//        };
//
//        mListView.setAdapter(mCursorAdapter);


    }



}
