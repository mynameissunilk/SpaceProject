package sunil.project3;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temp card data
        arrayList = new ArrayList<>();
        arrayList.add("From which we spring! Drake Equation, kindling the energy hidden in matter Drake Equation Euclid. ");
        arrayList.add("Great turbulent clouds at the edge of forever consectetur star stuff harvesting star light");
        arrayList.add("White dwarf Euclid paroxysm of global death of brilliant syntheses concept of the number one tingling of the spine. Brain is the seed of intelligence, as a patch of light encyclopaedia galactica paroxysm of global death bits of moving fluff, finite but unbounded kindling the energy hidden in matter, rings of Uranus Hypatia prime number cosmos Euclid decipherment made in the interiors of collapsing stars.");
        arrayList.add("Vanquish the impossible the carbon in our apple pies hydrogen atoms globular star cluster great turbulent clouds muse about brain is the seed of intelligence Drake Equation, venture a billion trillion rings of Uranus cosmic ocean, billions upon billions a very small stage in a vast cosmic arena prime number muse about vanquish the impossible not a sunrise but a galaxyrise rings of Uranus star stuff harvesting star light. ");
        arrayList.add("Apollonius of Perga? Citizens of distant epochs? At the edge of forever colonies a very small stage in a vast cosmic arena explorations Orion's sword trillion of brilliant syntheses how far away at the edge of forever. Dream of the mind's eye cosmic ocean gathered by gravity, made in the interiors of collapsing stars, cosmic ocean explorations dream of the mind's eye realm of the galaxies! Dream of the mind's eye. Circumnavigated Vangelis rich in mystery decipherment hydrogen atoms colonies.");

        //recyclerview setup
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        MainRvAdapter adapter = new MainRvAdapter(arrayList);
        mRecyclerView.setAdapter(adapter);



        //notification
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,0);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
       // notificationBuilder.setSmallIcon();
        notificationBuilder.setContentTitle("Don't you want to know what's going on in space?")
                .setContentText("Come see what's new!")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        //use jobscheduler to determine when to launch notification?
        NotificationManagerCompat.from(MainActivity.this).notify(1, notificationBuilder.build());



    }
}
