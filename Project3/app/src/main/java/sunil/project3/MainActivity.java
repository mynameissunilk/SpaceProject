package sunil.project3;

import android.app.ActionBar;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
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

import android.util.Base64;
import android.util.Log;

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


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sunil.project3.ApiServices.GuardianApiService;
import sunil.project3.ApiServices.NasaApiService;
import sunil.project3.ApiServices.NprService;
import sunil.project3.ApiServices.TwitterApiService;
import sunil.project3.CardObjects.CalendarEventObject;

import java.util.List;

import sunil.project3.ApiServices.Endpoints;
//import sunil.project3.CardObjects.APOD;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.TwitterObj;
import sunil.project3.ApiServices.NprService;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;

import sunil.project3.Guardian.Content;
import sunil.project3.Guardian.ResponseBody;
import sunil.project3.NPR.ContentNpr;
import sunil.project3.NPR.Story;
import sunil.project3.Twitter.TwitterContent;



public class MainActivity extends AppCompatActivity {

    // The Guardian
    public static final String guardianURL = "http://content.guardianapis.com/";
    public static final String guardianKey = "84a85242-3b93-42f2-8952-138f45f50dee";

    // The New York Times
    public static final String nytURL = "https://api.nytimes.com/svc/search/v2/";
    public static final String nytKey ="4a3efda1da0840c5929ff4e7758f0b59";
    //"73f5f97cf52247a7a83b9f24299a23e2";

    // Nasa Astronomy Picture of the Day
    public static final String nasaURL = "https://api.nasa.gov/planetary/";
    public static final String nasaKey = "IsXUyhCSGkUP5QHrAAYITkO2PyqGeawPISAwZXRr";

    // NPR
    public static final String nprURL = "http://api.npr.org/";
    public static final String nprKey = "MDI1OTA2MzQxMDE0NzEzODI2NTU4NjNkMA000";

    // Twitter
    // old keys from the OAuth lab... they work, but I didn't realize what I was doing wrong till way after getting new keys... :/
    // Consumer: "tJF1TxJoPHGrjyTMzIAGqjpaE"
    // Secret: "T8IuaJBtYACzTRuWcPtIuVxEFDEFK6tgapgOqDbrS8IcGNu2NZ"

    public static final String twitterTokenURL = "https://api.twitter.com/";
    public static final String twitterConsumerKey ="HT6chghQd3fjhQGTYDMhl7nX8";
    public static final String twitterSecretKey ="HdEim1rkLxHo5rFguQHxDm1E71Af2NOyBBn75vtfFvhoRy6Gez";
    public static final String twitterPreEncryption = twitterConsumerKey + ":" + twitterSecretKey;
    public static final String twitterBaseURL = "https://api.twitter.com/"; //ugh i'm dumb
    public static String encryptedKey64;
    public static String twitToken;



    //public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public RecyclerView mRecyclerView;
    public RecyclerView mRecyclerView2;
    public ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageScrape;
    HorizontalScrollView mHorizontalScrollView;

    ImageButton mToggle;

    CursorAdapter mCursorAdapter;
    ListView mListView;
    CardView mWebScrapeCardView;
    ImageView mImageView;
    private static final String TAG = "MainActivity";




    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToggle = (ImageButton) findViewById(R.id.toggle);
        mWebScrapeCardView = (CardView) findViewById(R.id.webScrapeCardView);
        mImageView = (ImageView) findViewById(R.id.imageScrape);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#5E35B1"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setShowHideAnimationEnabled(true);

//        Endpoints.connectTwitterforToken();

        // SQLite HELPER
        DBHelper helper = DBHelper.getInstance(this);

        // TALK TO ENDPOINTS, WRITE RESULT OBJECTS TO TABLE
        connectGuardian();
        connectNasa();
        connectNPR();


        // NOW CREATE OBJECTS FROM THE TABLE
        CardObjSingleton cardsing = CardObjSingleton.getInstance();

        cardsing.addObjectToMasterList(helper.createAPODFromTable());
        cardsing.addListToMasterList(helper.getGuardianListFromDb());
        cardsing.addListToMasterList(helper.getNPRListFromDb());



        //recyclerview setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        String marsUrl = "http://highmars.org/wp-content/uploads/2016" + "/05/high-mars-10.jpg";

        SetDailyPhotos setDailyPhotos = new SetDailyPhotos();
        setDailyPhotos.execute();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            mToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int height = mWebScrapeCardView.getHeight();
                    if (height < 100) {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        mToggle.setImageResource(R.mipmap.up);
                        Log.i(TAG, "onClick: height " + height);
                    } else {
                        mWebScrapeCardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                        Log.i(TAG, "onClick: height " + height);
                        mToggle.setImageResource(R.mipmap.down);
                    }
                }
            });
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
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

        mRecyclerView.setLayoutManager(manager);
        MainRvAdapter adapter = new MainRvAdapter(CardObjSingleton.getInstance().getMasterList());
        mRecyclerView.setAdapter(adapter);


        //notification
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.bigText("Don't you want to know what's going on in space?")
                .setBigContentTitle("Come see what's new!");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("Hey, Space Cadet...")
                .setAutoCancel(true)
                .setStyle(textStyle)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0));
        //use jobscheduler to determine when to periodically launch notification?
        NotificationManagerCompat.from(MainActivity.this).notify(0, notificationBuilder.build());


        //inserting astronomical event objects into database
        //i know this doesn't belong here in oncreate, but i don't know where to put it

        helper.deleteCalendarTableContents();
        CalendarEventObject event = new CalendarEventObject("43P/Wolf-Harrington at perihelion", "Friday", 2016, 8, 19, 0, 0, "https://in-the-sky.org/news.php?id=20160819_18_100");
        CalendarEventObject event1 = new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 0,0, "https://in-the-sky.org/news.php?id=20160821_11_100");
        CalendarEventObject event2 = new CalendarEventObject("The Moon at perigee", "Sunday", 2016, 8, 21, 0, 0, "https://in-the-sky.org/news.php?id=20160822_09_100");
        CalendarEventObject event3 = new CalendarEventObject("Asteroid 2 Pallas at opposition", "Monday", 2016, 8, 22, 01, 00, "https://in-the-sky.org/news.php?id=20160822_15_100");
        CalendarEventObject event4 = new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 0, 0, "https://in-the-sky.org/news.php?id=20160822_16_100");
        CalendarEventObject event5 = new CalendarEventObject("43P/Wolf-Harrington reaches its brightest", "Tuesday", 2016, 8, 23, 0, 0, "https://in-the-sky.org/news.php?id=20160823_18_100");
        CalendarEventObject event6 = new CalendarEventObject("Conjunction between Mars and Saturn", "Wednesday", 2016, 8, 24, 11, 37, "https://in-the-sky.org/news.php?id=20160824_16_100");
        CalendarEventObject event7 = new CalendarEventObject("144P/Kushida at perihelion", "Tuesday", 2016, 8, 30, 0, 0, "https://in-the-sky.org/data/object.php?id=103071");
        helper.addCalendarToTable(event);
        helper.addCalendarToTable(event1);
        helper.addCalendarToTable(event2);
        helper.addCalendarToTable(event3);
        helper.addCalendarToTable(event4);
        helper.addCalendarToTable(event5);
        helper.addCalendarToTable(event6);
        helper.addCalendarToTable(event7);
        /*
        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";

        CardObjSingleton.getInstance().addListToMasterList(DBHelper.getInstance(this).getEventListFromDb());

        ArrayList<String> scrollViewURLS = new ArrayList<String>();
        scrollViewURLS.add(eartUrl);
        scrollViewURLS.add(marsUrl);
        scrollViewURLS.add(person1);
        scrollViewURLS.add(person2);

<<<<<<< HEAD

=======
    }
>>>>>>> 93574c9aeaf7665b05bf2753759cc25a8ac88f1a



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
//        NasaObj nasaObj2 = new NasaObj(eartUrl, "An article on Earth", "because");

////        String temp1 = "From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid.";
////        String temp2 = "Great turbulent clouds at the edge of forever consectetur star stuff harvesting star ligh";
////        String temp3 = "White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number oneinteriors of collapsing stars";
////        String temp4 = "Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster gr star light.";
////        String temp5 = "Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very smal hydrogen atoms colonies";
////        String marsUrl = "http://highmars.org/wp-content/uploads/2016/05/high-mars-10.jpg";
////        String eartUrl = "https://lifesjourneyblog.files.wordpress.com/2013/07/planet-earth-from-space.jpg";
////        String person1 = "http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png";
////        String person2 = "https://d.ibtimes.co.uk/en/full/1356835/number-2-u-s-president-barack-obama-second-most-admired-person-planet.jpg?w=400";
////
////        TwitterObj twitterObj1 = new TwitterObj(person1, "name", temp5, "8/14/2016", "Anders");
////        TwitterObj twitterObj2 = new TwitterObj(person2, "name", temp3, "8/14/2016", "Anders");
////        GuardianObj guardianObj1 = new GuardianObj(temp1, temp2, temp3);
////        GuardianObj guardianObj2 = new GuardianObj(temp1, temp2, temp3);
////        NYTObj nytObj1 = new NYTObj(temp1, temp2, temp3, temp4);
////        NYTObj nytObj2 = new NYTObj(temp1, temp2, temp3, temp4);
////        NasaObj nasaObj1 = new NasaObj(marsUrl, "An article on Mars", "because");
//

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
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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



    public class SetDailyPhotos extends AsyncTask<Void, Void, String> {
        String mURL;
        private static final String TAG = "SetDailyPhotos";

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
//        client.connect();
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
//        AppIndex.AppIndexApi.start(client, viewAction);
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
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }




    // ENDPOINTS BELOW PLEASE LET THE ENDPOINTS BE AT THE BOTTOM OF THIS ;)
    public  void connectGuardian() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(guardianURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GuardianApiService guardianService = retrofit.create(GuardianApiService.class);

        Call<Content> call = guardianService.getArticles(guardianKey);

        call.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                try {
                    Log.i("GUARDIAN","CONNECTED");

                    List<ResponseBody> guardianArticles = response.body().getResponse().getResults();
                    ArrayList<CardObject> guardianArticleList = new ArrayList<CardObject>();

                    // print articles & links
                    for (int i = 0; i < guardianArticles.size(); i++) {
                        Log.i("CONTENTS: ", guardianArticles.get(i).getApiUrl() + " " + guardianArticles.get(i).getWebTitle());
                        guardianArticleList.add(new GuardianArticle(
                                guardianArticles.get(i).getWebTitle(),
                                guardianArticles.get(i).getWebUrl()));

                        // write guardian articles to Guardian table!
                        DBHelper helper1 = DBHelper.getInstance(MainActivity.this);
                        helper1.addListToTable(guardianArticleList);
                        Log.i("GUARDIANARTICLES","LIST ADDING LIST TO TABLE");

                        // Log.i("guardian", "onResponse: number of articles:  "+guardianArticles.size());
                        // Log.i("guardian", "master list size:  "+CardObjSingleton.getInstance().getMasterList().size());
                    }

                    Log.i("SUCCESS", "CONNECTED");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Log.i("THE GUARDIAN", "FAILED TO CONNECT");
            }
        });
    }

    public  void connectNasa() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor) // the logging interceptor
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(nasaURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NasaApiService nasaService = retrofit.create(NasaApiService.class);

        Call<ContentNasa> call = nasaService.getAPOD(nasaKey);

        call.enqueue(new Callback<ContentNasa>() {
            @Override
            public void onResponse(Call<ContentNasa> call, Response<ContentNasa> response) {
                Log.i("NASA","CONNECTION SUCCESSFUL");

                String apodTitle = response.body().getTitle();
                String apodexplanation = response.body().getExplanation();
                String apodUrl = response.body().getUrl();

                APOD picOfDay = new APOD(apodTitle,apodexplanation,apodUrl);

                DBHelper helper = DBHelper.getInstance(MainActivity.this);
                helper.addAPODToTable(picOfDay);
                Log.i("APOD","ADDING IMAGE INFO TO TABLE");

            }

            @Override
            public void onFailure(Call<ContentNasa> call, Throwable t) {
                Log.i("NASA","CONNECTION FAILED");
            }
        });
    }

    public  void connectNPR() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient nprClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit nprfit = new Retrofit.Builder()
                .baseUrl(nprURL)
                .client(nprClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NprService nprService = nprfit.create(NprService.class);

        Call<ContentNpr> nprCall = nprService.getArticle(nprKey);
        nprCall.enqueue(new Callback<ContentNpr>() {
            @Override
            public void onResponse(Call<ContentNpr> call, Response<ContentNpr> response) {
                Log.i("SUCCESS:", "HOORAY");

                ArrayList<Story> responseCopy = new ArrayList<Story>(response.body().getList().getStory());

                // copy the relevant items of responsecopy into an nprarticle list
                ArrayList<NprArticle> nprList = new ArrayList<>();
                ArrayList<CardObject>nprHolder = new ArrayList<>();
                for (int i = 0; i < responseCopy.size(); i++) {
                    nprList.add(new NprArticle(
                            responseCopy.get(i).getTitle().get$text(),
                            responseCopy.get(i).getText().getParagraph().get(0).get$text(),
                            responseCopy.get(i).getStoryDate().get$text(),
                            responseCopy.get(i).getLink().get(0).get$text()
                    ));
                    nprHolder.add(nprList.get(i));
                }

                // messy way to add npr articles to table
                DBHelper help = DBHelper.getInstance(MainActivity.this);
                help.addListToTable(nprHolder);

                /*for (int i = 0; i < nprList.size(); i++) {
                    Log.i("TITLE", nprList.get(i).getTitle());
                    Log.i("DESC", nprList.get(i).getParagraph());
                    Log.i("DATE", nprList.get(i).getDate());
                    Log.i("URL", nprList.get(i).getURL());
                }*/

            }
            @Override
            public void onFailure(Call<ContentNpr> call, Throwable t) {
                Log.i("FAILURE", "FAILURE");
            }
        });
    }

    public void connectTwitterforToken() {
        byte[] concatArray = twitterPreEncryption.getBytes();
        encryptedKey64 = Base64.encodeToString(concatArray, Base64.NO_WRAP);

        // twitter oauth
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(twitterTokenURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TwitterApiService twitterService = retrofit.create(TwitterApiService.class);

        final String twitAuth = "Basic " + encryptedKey64;

        Call<okhttp3.ResponseBody> call = twitterService.getToken(twitAuth, "application/x-www-form-urlencoded;charset=UTF-8", "client_credentials");
        call.enqueue(new Callback<okhttp3.ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                Log.i("TWITTER", "GETTING TOKEN URL...");
                String output = null;
                try {
                    output = response.body().string();
                    // sometimes fancy gson needs to go !@#$ off
                    JSONObject jason = new JSONObject(output);
                    twitToken = jason.getString("access_token");

                    connectTwitterwithToken("Bearer "+twitToken);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                Log.i("FAILURE", "DID NOT GET TOKEN");
            }
        });

    }

    public  void connectTwitterwithToken(String bearerToken) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient twitterTokenClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(twitterBaseURL)
                .client(twitterTokenClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TwitterApiService timelineService = retrofit.create(TwitterApiService.class);

        Call<TwitterContent> timelineCall = timelineService.getTimeline(bearerToken,"application/json;charset=utf-8", "NASA_Astronauts", 5);

        timelineCall.enqueue(new Callback<TwitterContent>() {
            @Override
            public void onResponse(Call<TwitterContent> call, Response<TwitterContent> response) {
                Log.i("@GET WITH TOKEN", "GOT TO GETTIMELINE() ONRESPONSE");

            }

            @Override
            public void onFailure(Call<TwitterContent> call, Throwable t) {
                Log.i("@GET WITH TOKEN", "FAILED");
            }
        });
    }
}

