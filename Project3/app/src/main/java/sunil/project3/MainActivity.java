package sunil.project3;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import sunil.project3.CardObjects.CalendarEventObject;

import sunil.project3.ApiServices.Endpoints;
import sunil.project3.CardObjects.CardObjSingleton;


public class MainActivity extends AppCompatActivity {
    //public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public RecyclerView mRecyclerView;
    public RecyclerView mRecyclerView2;
    public ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    HorizontalScrollView mHorizontalScrollView;
    Button mToggle;

    CursorAdapter mCursorAdapter;
    ListView mListView;
    CardView mHorizontalCardView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Endpoints.connectTwitterwithToken(CardObjSingleton.getInstance().getToken());
        Endpoints.connectGuardian();


        //recyclerview setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            manager.setOrientation(LinearLayout.VERTICAL);
        } else {
            manager.setOrientation(LinearLayout.HORIZONTAL);
        }
        mRecyclerView.setLayoutManager(manager);
        MainRvAdapter adapter = new MainRvAdapter(CardObjSingleton.getInstance().getMasterList());
        mRecyclerView.setAdapter(adapter);


        //notification
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.bigText("Don't you want to know what's going on in space? Space is the Place, if you haven't heard.")
                .setBigContentTitle("Come see what's new!");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_insert_emoticon_black_24dp);
        notificationBuilder.setContentTitle("Yoohoo....")

                .setAutoCancel(true)
                .setStyle(textStyle)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0));
        //use jobscheduler to determine when to periodically launch notification?
        NotificationManagerCompat.from(MainActivity.this).notify(0, notificationBuilder.build());




        //inserting astronomical event objects into database
        //i know this shouldnt be here in oncreate, but i don't know where to put it
        DBHelper helper = DBHelper.getInstance(this);
        helper.deleteCalendarTableContents();
        CalendarEventObject event = new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 00, 00, "https://in-the-sky.org/news.php?id=20160819_18_100");
        CalendarEventObject event1 = new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100");
        CalendarEventObject event2 = new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 21, 20, "https://in-the-sky.org/news.php?id=20160822_09_100");
        CalendarEventObject event3 = new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 00, 35, "https://in-the-sky.org/news.php?id=20160822_15_100");
        CalendarEventObject event4 = new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100");
        CalendarEventObject event5 = new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 00, 00, "https://in-the-sky.org/news.php?id=20160823_18_100");
        CalendarEventObject event6 = new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100");
        CalendarEventObject event7 = new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 00, 00, "https://in-the-sky.org/news.php?id=20160830_18_100");
        helper.addCalendarToTable(event);
        helper.addCalendarToTable(event1);
        helper.addCalendarToTable(event2);
        helper.addCalendarToTable(event3);
        helper.addCalendarToTable(event4);
        helper.addCalendarToTable(event5);
        helper.addCalendarToTable(event6);
        helper.addCalendarToTable(event7);

        CardObjSingleton.getInstance().addListToMasterList(DBHelper.getInstance(this).getEventListFromDb());



    }



    /**  all this fun stuff that we are not currently using ..
     * i'll just leave it here. just in case*/
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


    //code for share feature, add this in whichever onClickListener should do
//                            Intent shareIntent = new Intent();
//                            shareIntent.setAction(Intent.ACTION_SEND);
//                            shareIntent.putExtra(Intent.EXTRA_TEXT, guardianObj.getURL());//second parameter is whatever we wanna send as a string url
//                            shareIntent.setType("text/plain");
//                            mContext.startActivity(shareIntent);


//        DBHelper dbHelper = DBHelper.getInstance(this);
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ProviderContract.NYT.COL_1, "a");
//        contentValues.put(ProviderContract.NYT.COL_2, "b");
//        contentValues.put(ProviderContract.NYT.COL_3, "c");
//        contentValues.put(ProviderContract.NYT.COL_4, "d");
//        contentValues.put(ProviderContract.NYT.COL_5, "e");
//        getContentResolver().insert(ProviderContract.NYT.CONTENT_URI, contentValues);
//        dbHelper.addItems(contentValues);

//
//
//        //temp card data
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid. ");
//        arrayList.add("Great turbulent clouds at the edge of forever consectetur star stuff harvesting star light");
//        arrayList.add("White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number one tingling of the spine. Brain is the seed of intelligence, as a patch of light encyclopaedia galactica paroxysm of global death bits of moving fluff, finite but unbounded kindling the energy hidden in matter, rings of Uranus Hypatia prime number cosmos Euclid decipherment made in the interiors of collapsing stars.");
//        arrayList.add("Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster great turbulent clouds muse about brain is the seed of intelligence Drake Equation, venture a billion trillion rings of Uranus cosmic ocean, billions upon billions a very small stage in a vast cosmic arena prime number muse about vanquish the impossible not a sunrise but a galaxyrise rings of Uranus star stuff harvesting star light. ");
//        arrayList.add("Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very small stage in a vast cosmic arena explorations Orion's sword trillion of brilliant syntheses how far away at the edge of forever. Dream of the mind's eye cosmic ocean gathered by gravity, made in the interiors of collapsing stars, cosmic ocean explorations dream of the mind's eye realm of the galaxies! Dream of the mind's eye. Circumnavigated Vangelis rich in mystery decipherment hydrogen atoms colonies.");
//
//        ArrayList<String> astroLTList = new ArrayList<>();
//        ArrayList<String> astroSTList = new ArrayList<>();
//        astroSTList.add("From which we spring! Drake Equation, k matter Drake Equation Euclid. ");
//        astroSTList.add("Great turbulent clouds at the edge of fostar stuff harvesting star light");
//        astroSTList.add("White dwarf Euclid paroxysm of  Euclid interiors of collapsing stars.");
//        astroSTList.add("Vanquish the impossible the cara galaxyrisstuff harvesting star light. ");
//        astroSTList.add("Apollonius of Perga? Citizens Vangelis  hydrogen atoms colonies.");
//
//        ArrayList<String> astroINList = new ArrayList<>();
//        astroINList.add("From which we spring! ");
//        astroINList.add("Great turbulent clouds");
//        astroINList.add("White dwarf Euclid ");
//        astroINList.add("Vanquish the impossthe. ");
//        astroINList.add("Apollonius of Perga? Citizens .");


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


}