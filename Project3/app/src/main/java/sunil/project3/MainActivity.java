package sunil.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sunil.project3.ApiServices.GuardianApiService;
import sunil.project3.Guardian.Content;
import sunil.project3.Guardian.Result;


public class MainActivity extends AppCompatActivity {

    public static final String guardianURL = "http://content.guardianapis.com";
    public static final String guardianKey = "84a85242-3b93-42f2-8952-138f45f50dee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient.Builder().build();

        //Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(guardianURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GuardianApiService guardianService = retrofit.create(GuardianApiService.class);

        Call<Result> call = guardianService.getArticle(guardianKey,"space");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                String title = response.body().getWebTitle();
                String url = response.body().getWebUrl();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }
}
