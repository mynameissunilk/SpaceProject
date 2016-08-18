package sunil.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import sunil.project3.ApiServices.Endpoints;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Endpoints.connectTwitterforToken();

        // Endpoints.connectNYT();


    }
}
