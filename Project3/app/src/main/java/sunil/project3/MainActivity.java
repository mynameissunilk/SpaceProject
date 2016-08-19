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

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


import sunil.project3.CardObjects.CalendarEventObject;

import java.util.List;

import sunil.project3.ApiServices.Endpoints;
import sunil.project3.CardObjects.APOD;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.TwitterObj;


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

        //Endpoints.connectTwitterforToken();

        //Endpoints.connectTwitterwithToken(CardObjSingleton.getInstance().getToken());

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


        Endpoints.connectGuardian();

        /*mListView = (ListView) findViewById(R.id.HorizontalIMGlistView);


//        CardObjSingleton.getInstance().addListToMasterList(Endpoints.connectGuardian());



//        GuardianArticle guardianObj1 = new GuardianArticle("")
//
//
//        List<CardObject> masterList = CardObjSingleton.getInstance().getMasterList();
//        if (masterList.size() > 0) {
//            masterList.clear();
//        }
//        masterList.add(guardianObj1);
//        CardObjSingleton.getInstance().addEventsToMasterList();



        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";
        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";

        TwitterObj twitterObj1 = new TwitterObj(person1, "name", temp5, "8/14/2016", "Anders");
        TwitterObj twitterObj2 = new TwitterObj(person2, "name", temp3, "8/14/2016", "Anders");
        GuardianArticle guardianObj1 = new GuardianArticle(temp1, temp2);
        GuardianArticle guardianObj2 = new GuardianArticle(temp3, temp4);
        APOD nasaObj1 = new APOD(eartUrl, "An article on Mars", "because");

        ArrayList<CardObject> list = new ArrayList<>();

        list.add(guardianObj1);
        list.add(guardianObj2);
        list.add(nasaObj1);

        CardObjSingleton.getInstance().addListToMasterList(list);




        mListView = (ListView) findViewById(R.id.HorizontalIMGlistView);
>>>>>>> b4010cd11561afd98e05dda2627d69403327b306
        mToggle = (Button) findViewById(R.id.toggle);
        mHorizontalCardView = (CardView) findViewById(R.id.horizontal_scrollview);*/

//        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
/*        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
        ArrayList<String> scrollViewURLS = new ArrayList<String>();
        scrollViewURLS.add(marsUrl);
        ImagScrollViewAdapter ImgAdapter = new ImagScrollViewAdapter(this, scrollViewURLS);

        mListView.setAdapter(ImgAdapter);
        mToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int height = mListView.getHeight();
                if (height < 100) {
                    mHorizontalCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));
                    Log.i(TAG, "onClick: height " + height);
                } else {
                    mHorizontalCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                    Log.i(TAG, "onClick: height " + height);
                }
            }
        });*/


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

    }

}


//dummy data
        /*
        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";


        ArrayList<String> scrollViewURLS = new ArrayList<String>();
        scrollViewURLS.add(eartUrl);
        scrollViewURLS.add(marsUrl);
        scrollViewURLS.add(person1);
        scrollViewURLS.add(person2);



<<<<<<< HEAD
        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";
        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";

        TwitterObj twitterObj1 = new TwitterObj(person1, "name", temp5, "8/14/2016", "Anders");
        TwitterObj twitterObj2 = new TwitterObj(person2, "name", temp3, "8/14/2016", "Anders");
        GuardianObj guardianObj1 = new GuardianObj(temp1, temp2, temp3);
        GuardianObj guardianObj2 = new GuardianObj(temp1, temp2, temp3);
        NYTObj nytObj1 = new NYTObj(temp1, temp2, temp3, temp4);
        NYTObj nytObj2 = new NYTObj(temp1, temp2, temp3, temp4);
        NasaObj nasaObj1 = new NasaObj(marsUrl, "An article on Mars", "because");
        NasaObj nasaObj2 = new NasaObj(eartUrl, "An article on Earth", "because");
=======
//        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
//        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
//        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
//        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
//        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";
//        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
//        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
//        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
//        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";
//
//        TwitterObj twitterObj1 = new TwitterObj(person1, "name", temp5, "8/14/2016", "Anders");
//        TwitterObj twitterObj2 = new TwitterObj(person2, "name", temp3, "8/14/2016", "Anders");
//        GuardianObj guardianObj1 = new GuardianObj(temp1, temp2, temp3);
//        GuardianObj guardianObj2 = new GuardianObj(temp1, temp2, temp3);
//        NYTObj nytObj1 = new NYTObj(temp1, temp2, temp3, temp4);
//        NYTObj nytObj2 = new NYTObj(temp1, temp2, temp3, temp4);
//        NasaObj nasaObj1 = new NasaObj(marsUrl, "An article on Mars", "because");
>>>>>>> b4010cd11561afd98e05dda2627d69403327b306



        //populate the master list to feed into the recyclerview

//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        List<CardObject> masterList = CardObjSingleton.getInstance().getMasterList();
        if (masterList.size()>0){masterList.clear();}
        masterList.add(nasaObj1);
        CardObjSingleton.getInstance().addEventsToMasterList();
        masterList.add(guardianObj1);
        masterList.add(guardianObj2);
        masterList.add(twitterObj1);
        masterList.add(twitterObj2);
        masterList.add(nytObj1);
        masterList.add(nytObj2);
//        masterList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
//        masterList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
        Log.i("list", "master list size: "+masterList.size());








           //code for share feature, add this in whichever onClickListener should do
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, guardianObj.getURL());//second parameter is whatever we wanna send as a string url
                            shareIntent.setType("text/plain");
                            mContext.startActivity(shareIntent);






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
    }

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

*/