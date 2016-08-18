package sunil.project3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.CalendarContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Calendar;
import java.util.List;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CalendarEventSingleton;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;
import sunil.project3.CardObjects.GuardianObj;
import sunil.project3.CardObjects.NYTObj;
import sunil.project3.CardObjects.NasaObj;
import sunil.project3.CardObjects.TwitterObj;

public class MainActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public RecyclerView mRecyclerView2;

    CursorAdapter mCursorAdapter;
    TextView mT1, mT2, mT3, mT4, mT5;
    ListView mListView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //facebook setup
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



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
        NasaObj nasaObj2 = new NasaObj(eartUrl, "An article on Earth", "becauseee");


        List<CardObject> masterList = CardObjSingleton.getInstance().getMasterList();

        CardObjSingleton.getInstance().addEventsToMasterList();
        masterList.add(nasaObj1);
        masterList.add(nasaObj2);
        masterList.add(guardianObj1);
        masterList.add(guardianObj2);
        masterList.add(twitterObj1);
        masterList.add(twitterObj2);
        masterList.add(nytObj1);
        masterList.add(nytObj2);
        masterList.add(nytObj1);
        masterList.add(nytObj2);
//        masterList.add(new CalendarEventObject("α–Cygnid meteor shower", "Sunday", 2016, 8, 21, 00, 00, "https://in-the-sky.org/news.php?id=20160821_11_100"));
//        masterList.add(new CalendarEventObject("Conjunction between the Moon and Uranus", "Monday", 2016, 8, 22, 07, 28, "https://in-the-sky.org/news.php?id=20160822_16_100"));

        Log.i("list", "master list size: "+masterList.size());


        //recyclerview setup CARDOBJECTS
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            manager.setOrientation(LinearLayout.VERTICAL);} else {manager.setOrientation(LinearLayout.HORIZONTAL);}
        mRecyclerView.setLayoutManager(manager);
        MainRvAdapter adapter = new MainRvAdapter(CardObjSingleton.getInstance().getMasterList());
        mRecyclerView.setAdapter(adapter);


//        //notification
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




}
