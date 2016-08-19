package sunil.project3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import sunil.project3.CardObjects.CalendarEventObject;
import java.util.List;

import sunil.project3.ApiServices.Endpoints;
import sunil.project3.CardObjects.APOD;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.TwitterObj;
import sunil.project3.ApiServices.NprService;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;


public class MainActivity extends AppCompatActivity {
    //public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public RecyclerView mRecyclerView;
    public RecyclerView mRecyclerView2;
    public ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageScrape;
    HorizontalScrollView mHorizontalScrollView;
    ImageButton mToggle;

    CursorAdapter mCursorAdapter;
    //    TextView mT1, mT2, mT3, mT4, mT5;
    ListView mListView;
    CardView mWebScrapeCardView;
    ImageView mImageView;

    private static final String TAG = "MainActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToggle = (ImageButton) findViewById(R.id.toggle);
        mWebScrapeCardView = (CardView) findViewById(R.id.webScrapeCardView);
        mImageView = (ImageView) findViewById(R.id.imageScrape);


//        Endpoints.connectTwitterforToken();

        String marsUrl = "http://highmars.org/wp-content/uploads/2016" + "/05/high-mars-10.jpg";

        SetDailyPhotos setDailyPhotos = new SetDailyPhotos();
        setDailyPhotos.execute();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            mToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int height = mWebScrapeCardView.getHeight();
                    if (height < 100) {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));
                        mToggle.setImageResource(R.mipmap.up);
                        Log.i(TAG, "onClick: height " + height);
                    } else {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(100, 0));
                        Log.i(TAG, "onClick: height " + height);
                        mToggle.setImageResource(R.mipmap.down);
                    }
                }
            });
        }
        else {

            mToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int height = mWebScrapeCardView.getHeight();
                    if (height < 100) {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        Log.i(TAG, "onClick: height " + height);
                    } else {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT));
                        Log.i(TAG, "onClick: height " + height);
                    }
                }
            });
        }

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



        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";

        TwitterObj twitterObj1 = new TwitterObj(person1, "name", temp5, "8/14/2016", "Anders");
        TwitterObj twitterObj2 = new TwitterObj(person2, "name", temp3, "8/14/2016", "Anders");
        GuardianObj guardianObj1 = new GuardianObj(temp1, temp2, temp3);
        GuardianObj guardianObj2 = new GuardianObj(temp1, temp2, temp3);
        NYTObj nytObj1 = new NYTObj(temp1, temp2, temp3, temp4);
        NYTObj nytObj2 = new NYTObj(temp1, temp2, temp3, temp4);
        NasaObj nasaObj1 = new NasaObj(marsUrl, "An article on Mars", "because");
        NasaObj nasaObj2 = new NasaObj(eartUrl, "An article on Earth", "because");



//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//*/
        List<CardObject> masterList = CardObjSingleton.getInstance().getMasterList();
        if (masterList.size()>0){masterList.clear();}
//        masterList.add(nasaObj1);
        CardObjSingleton.getInstance().addEventsToMasterList();
//        masterList.add(guardianObj1);
//        masterList.add(guardianObj2);
//        masterList.add(twitterObj1);
//        masterList.add(twitterObj2);
//        masterList.add(nytObj1);
//        masterList.add(nytObj2);
//        masterList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
//        masterList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));
        Log.i("list", "master list size: "+masterList.size());
//        ArrayList<CalendarEventObject> eventList = CalendarEventSingleton.getInstance().getEventList();
//        CalendarEventSingleton.getInstance().addEventsToMasterList(eventList);

//*/
        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";

        APOD apod1 = new APOD("Title here", temp2, temp3);
        APOD apod2 = new APOD("Title here", temp2, temp3);
        NprArticle npr1 = new NprArticle("Title here", temp2, "20/12/2016", temp4);
        NprArticle npr2 = new NprArticle("Title here", temp2, "20/12/2016", temp4);
        GuardianArticle guard1 = new GuardianArticle("An article on Mars", "because");
        GuardianArticle guard2 = new GuardianArticle("An article on Earth", "because");

        masterList.add(npr1);
        masterList.add(npr2);
        masterList.add(apod2);
        masterList.add(apod1);
        masterList.add(guard2);
        masterList.add(guard1);

        for (int i = 0; i < masterList.size(); i ++){
            Log.i(TAG, "onCreate: " + masterList.get(i));
        }

        //recyclerview setup CARDOBJECTS
        //recyclerview setup CARDOBJECTS
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






//
//
//           //code for share feature, add this in whichever onClickListener should do
//                            Intent shareIntent = new Intent();
//                            shareIntent.setAction(Intent.ACTION_SEND);
//                            shareIntent.putExtra(Intent.EXTRA_TEXT, guardianObj.getURL());//second parameter is whatever we wanna send as a string url
//                            shareIntent.setType("text/plain");
//                            MainActivity.this.startActivity(shareIntent);






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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public class SetDailyPhotos extends AsyncTask<Void,Void,String>{
        Bitmap mBitmap;
        String mURL;
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect("http://apod.nasa.gov/apod/astropix.html").get();
                Log.i(TAG, "doInBackground: document" + document);
                Elements img = document.select("p a img");

                Log.i(TAG, "doInBackground image element: " + img);
                String imgSource = img.attr("src");
                Log.i(TAG, "doInBackground: image source " + imgSource);
                mURL = imgSource;

                Log.i(TAG, "doInBackground: url" + mURL);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            mImageScrape = (ImageView) findViewById(R.id.imageScrape);
//            mImageScrape.setImageBitmap(mBitmap);
//            http://apod.nasa.gov/
            String total = "http://apod.nasa.gov/" + mURL;
            Picasso.with(MainActivity.this).load(total).into(mImageScrape);
            Log.i(TAG, "onPostExecute: " + total);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://sunil.project3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://sunil.project3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
