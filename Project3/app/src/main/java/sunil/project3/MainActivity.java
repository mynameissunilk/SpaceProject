package sunil.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sunil.project3.ApiServices.GuardianApiService;
import sunil.project3.ApiServices.NasaApiService;
import sunil.project3.Guardian.ResponseBody;


public class MainActivity extends AppCompatActivity {

    public static final String guardianURL = "http://content.guardianapis.com/";
    public static final String guardianKey = "84a85242-3b93-42f2-8952-138f45f50dee";

    public static final String nytURL = "https://api.nytimes.com/svc/search/v2/";
    public static final String nytKey = "73f5f97cf52247a7a83b9f24299a23e2";

    public static final String nasaURL="https://api.nasa.gov/planetary/";
    public static final String nasaKey="IsXUyhCSGkUP5QHrAAYITkO2PyqGeawPISAwZXRr";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* CONNECTION TO GUARDIAN... SUCCESS!
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(guardianURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GuardianApiService guardianService = retrofit.create(GuardianApiService.class);

        Call<ResponseBody> call = guardianService.getArticles(guardianKey);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String url = response.body().getWebUrl();
                    Log.i("SUCCESS","CONNECTED");
                    Log.i("STRING NULL?","works? "+url );
                }

                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("FAILURE","FAILED TO CONNECT");
            }
        });

        */

/*
        // CONNECTION TO NASA... SUCCESS!
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        //Request request = new Request.Builder().addHeader("api_key",nasaKey).build();

        // found something about overloading headers at https://futurestud.io/blog/retrofit-2... blah
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor) // the logging interceptor
                .build();

*//*

                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .header("api_key",nasaKey);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
*//*


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(nasaURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NasaApiService nasaService = retrofit.create(NasaApiService.class);

        Call<ContentNasa>call = nasaService.getAPOD(nasaKey);

        call.enqueue(new Callback<ContentNasa>() {
            @Override
            public void onResponse(Call<ContentNasa> call, Response<ContentNasa> response) {
                String imgurl = response.body().getUrl();
                Log.i("URL?",imgurl);
            }

            @Override
            public void onFailure(Call<ContentNasa> call, Throwable t) {

            }
        });

        */

        // NYT
        

    }
}
