package sunil.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sunil.project3.ApiServices.NprService;
import sunil.project3.ApiServices.NytApiService;
import sunil.project3.ApiServices.TwitterApiService;
import sunil.project3.NPR.ContentNpr;
import sunil.project3.NPR.Story;
import sunil.project3.NYT.ContentNyt;
import sunil.project3.NYT.Doc;


public class MainActivity extends AppCompatActivity {

    public static final String guardianURL = "http://content.guardianapis.com/";
    public static final String guardianKey = "84a85242-3b93-42f2-8952-138f45f50dee";

    public static final String nytURL = "https://api.nytimes.com/svc/search/v2/";
    public static final String nytKey ="73f5f97cf52247a7a83b9f24299a23e2";


            //"4a3efda1da0840c5929ff4e7758f0b59";
            //"73f5f97cf52247a7a83b9f24299a23e2";

    public static final String nasaURL = "https://api.nasa.gov/planetary/";
    public static final String nasaKey = "IsXUyhCSGkUP5QHrAAYITkO2PyqGeawPISAwZXRr";

    public static final String nprURL = "http://api.npr.org/";
    public static final String nprKey = "MDI1OTA2MzQxMDE0NzEzODI2NTU4NjNkMA000";

    //public static final String instaauthURL="https://api.instagram.com/oauth/authorize/?client_id=CLIENT-ID&redirect_uri=REDIRECT-URI&response_type=cod";
    public static final String twittokenURL = "https://api.twitter.com/";
    public static final String twitcons = "tJF1TxJoPHGrjyTMzIAGqjpaE";
    public static final String twitsecr = "T8IuaJBtYACzTRuWcPtIuVxEFDEFK6tgapgOqDbrS8IcGNu2NZ";
    public static final String twitTbcrypt = twitcons + ":" + twitsecr;
    public static final String twitBase = "https://api.twitter.com/"; //ugh i'm dumb
    public static String twitenc64;
    public static String twitToken;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*

//         CONNECTION TO GUARDIAN... SUCCESS!
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

        Call<Content> call = guardianService.getArticles(guardianKey);

        call.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                try{
                    Gson gson = new Gson();

                    //Content guardianquery = gson.fromJson(String.valueOf(response.body().getResponse().getResults().get(1)),Content.class);
                    //Log.i("guardian?",guardianquery.toString());


                    List<ResponseBody> guardianArticles = response.body().getResponse().getResults();

                    // print articles & links
                    for(int i = 0; i<guardianArticles.size();i++){
                        Log.i("CONTENTS: ", guardianArticles.get(i).getApiUrl() + " " + guardianArticles.get(i).getWebTitle());
                    }

                    */
/*
                    POJO pojo = gson.fromJson(response.body().string(),POJO.class)
                    String article = pojo.getText() pogo.getsomethingelse()
                    *//*


                    //getResponse.getResults
                    //go through results, and store title and url

                    Log.i("SUCCESS","CONNECTED");
                }

                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
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


*/
/*

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

                String apodTitle = response.body().getTitle();
                String apodexplanation = response.body().getExplanation();
                String apodUrl = response.body().getUrl();

                Log.i("TITLE: ",apodTitle);
                Log.i("EXPLANATION: ",apodexplanation);
                Log.i("URL: ",apodUrl);

            }

            @Override
            public void onFailure(Call<ContentNasa> call, Throwable t) {

            }
        });

*/

/*
        // NYT WORKS, QUERY NEEDS WORK...

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(nytURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NytApiService nytService = retrofit.create(NytApiService.class);

        Call<ContentNyt>call = nytService.getArticle(nytKey);

        call.enqueue(new Callback<ContentNyt>() {
            @Override
            public void onResponse(Call<ContentNyt> call, Response<ContentNyt> response) {


                try {
                    Log.i("NYT","CONNECTED! ");
                    ArrayList<Doc> docList = new ArrayList<>();//(ArrayList<Doc>) response.body().getResponse().getDocs();

                    for (int i = 0; i < docList.size(); i++) {
                        docList.add(response.body().getResponse().getDocs().get(i));
                        // hmm still failing, what the fuck is going on ...
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ContentNyt> call, Throwable t) {
                Log.i("NYT","FAILED!");
            }
        });

        */

/*

        byte[] concatArray = twitTbcrypt.getBytes();
        twitenc64 = Base64.encodeToString(concatArray, Base64.NO_WRAP);

        // twitter oauth
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(log)
                .build();

        Retrofit twitterfit = new Retrofit.Builder()
                .baseUrl(twittokenURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TwitterApiService twitterService = twitterfit.create(TwitterApiService.class);

        String twitAuth = "Basic "+twitenc64;

        Call<okhttp3.ResponseBody>call = twitterService.getToken(twitAuth,"application/x-www-form-urlencoded;charset=UTF-8","client_credentials");
        call.enqueue(new Callback<okhttp3.ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                Log.i("YES?","GOT TO THIS POINT...");
                String output = null;
                try {
                    output = response.body().string();

                    // sometimes fancy gson needs to go !@#$ off
                    JSONObject jason = new JSONObject(output);
                    twitToken = jason.getString("access_token");
                    Log.i("PLEASE WORK",twitToken);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                Log.i("FAILURE","YOU ARE A HORRIBLE PERSON");
            }
        });

        HttpLoggingInterceptor loggy = new HttpLoggingInterceptor();
        loggy.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient twitclient = new OkHttpClient.Builder().addInterceptor(loggy).build();

        Retrofit timelinefit = new Retrofit.Builder()
                .baseUrl(twitBase)
                .client(twitclient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TwitterApiService timelineService = twitterfit.create(TwitterApiService.class);
        //String twitBear = "Bearer "+twitToken;
        Call<okhttp3.ResponseBody>timelineCall = timelineService.getTimeline("Bearer "+twitToken,"nasa",5);

        timelineCall.enqueue(new Callback<okhttp3.ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                Log.i("WORKS?","WORKS.");
            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {

            }
        });

*/

        /*

        HttpLoggingInterceptor nprceptor = new HttpLoggingInterceptor();
        nprceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient nprClient = new OkHttpClient.Builder().addInterceptor(nprceptor).build();

        Retrofit nprfit = new Retrofit.Builder()
                .baseUrl(nprURL)
                .client(nprClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NprService nprService = nprfit.create(NprService.class);

        Call<ContentNpr>nprCall = nprService.getArticle(nprKey);
        nprCall.enqueue(new Callback<ContentNpr>() {
            @Override
            public void onResponse(Call<ContentNpr> call, Response<ContentNpr> response) {
                Log.i("SUCCESS:","HOORAY");



                ArrayList<Story>responseCopy = new ArrayList<Story>(response.body().getList().getStory());
                //responseCopy.get(0)


                // copy the relevant items of responsecopy into an nprarticle list
                ArrayList<NprArticle>nprList = new ArrayList<NprArticle>();

                for(int i = 0; i < responseCopy.size(); i++){
                    nprList.add(new NprArticle(
                            responseCopy.get(i).getTitle().get$text(),
                            responseCopy.get(i).getText().getParagraph().get(0).get$text(),
                            responseCopy.get(i).getStoryDate().get$text(),
                            responseCopy.get(i).getLink().get(0).get$text()
                    ));
                }

                for(int i = 0; i <nprList.size();i++){
                    Log.i("TITLE",nprList.get(i).getTitle());
                    Log.i("DESC",nprList.get(i).getParagraph());
                    Log.i("DATE",nprList.get(i).getDate());
                    Log.i("URL",nprList.get(i).getURL());

                }

            }

            @Override
            public void onFailure(Call<ContentNpr> call, Throwable t) {
                Log.i("FAILURE","FAILURE");
            }
        });
*/



    }
}
