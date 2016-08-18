package sunil.project3.ApiServices;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

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
import sunil.project3.APOD;
import sunil.project3.CardObjects.CalendarEventSingleton;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.ContentNasa;
import sunil.project3.Guardian.Content;
import sunil.project3.Guardian.ResponseBody;
import sunil.project3.NPR.ContentNpr;
import sunil.project3.NPR.Story;
import sunil.project3.NYT.ContentNyt;
import sunil.project3.NYT.Doc;
import sunil.project3.NprArticle;
import sunil.project3.Twitter.TwitterContent;

public class Endpoints {

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

    // API Call/Endpoint Methods
    // All calls use Retrofit, OkhttpInterceptor, and GSON POJO's
    // Except for getting Twitter's OAuth token, where I used JSON...
    public static void connectGuardian() {
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

                    List<ResponseBody> guardianArticles = response.body().getResponse().getResults();

                    // print articles & links
                    for (int i = 0; i < guardianArticles.size(); i++) {
                        Log.i("CONTENTS: ", guardianArticles.get(i).getApiUrl() + " " + guardianArticles.get(i).getWebTitle());
                    }


                    Log.i("SUCCESS", "CONNECTED");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Log.i("FAILURE", "FAILED TO CONNECT");
            }
        });
    }

    public static void connectNasa() {
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

                String apodTitle = response.body().getTitle();
                String apodexplanation = response.body().getExplanation();
                String apodUrl = response.body().getUrl();

                //CardObjSingleton.getInstance()
                //return new APOD(apodTitle,apodexplanation,apodUrl);

            }

            @Override
            public void onFailure(Call<ContentNasa> call, Throwable t) {

            }
        });
    }

    public static void connectNPR() {
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
                ArrayList<NprArticle> nprList = new ArrayList<NprArticle>();
                for (int i = 0; i < responseCopy.size(); i++) {
                    nprList.add(new NprArticle(
                            responseCopy.get(i).getTitle().get$text(),
                            responseCopy.get(i).getText().getParagraph().get(0).get$text(),
                            responseCopy.get(i).getStoryDate().get$text(),
                            responseCopy.get(i).getLink().get(0).get$text()
                    ));
                }

                for (int i = 0; i < nprList.size(); i++) {
                    Log.i("TITLE", nprList.get(i).getTitle());
                    Log.i("DESC", nprList.get(i).getParagraph());
                    Log.i("DATE", nprList.get(i).getDate());
                    Log.i("URL", nprList.get(i).getURL());
                }

            }
            @Override
            public void onFailure(Call<ContentNpr> call, Throwable t) {
                Log.i("FAILURE", "FAILURE");
            }
        });
    }

    public static void connectTwitterforToken() {
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

        String twitAuth = "Basic " + encryptedKey64;

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

                    // token is trapped in this scope, sigh took forever to realize this
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

    public static void connectTwitterwithToken(String bearerToken) {
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

        Call<okhttp3.ResponseBody> timelineCall = timelineService.getTimeline(bearerToken,"application/json;charset=utf-8", "NASA_Astronauts", 5);//twitterapi

        timelineCall.enqueue(new Callback<okhttp3.ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                Log.i("@GET WITH TOKEN", "GOT TO GETTIMELINE() ONRESPONSE");




                Gson gson = new Gson();
                TwitterContent content = null;
                try {
                    content = new Gson().fromJson(response.body().string(),TwitterContent.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                Log.i("@GET WITH TOKEN", "FAILED");
            }
        });
    }


/*    public static void connectNYT() {
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

        Call<ContentNyt> call = nytService.getArticle(nytKey);

        call.enqueue(new Callback<ContentNyt>() {
            @Override
            public void onResponse(Call<ContentNyt> call, Response<ContentNyt> response) {
                Log.i("NYT", "CONNECTED");
                try {
                    Log.i("NYT", "CONNECTED! ");
                    ArrayList<Doc> docList = new ArrayList<>();//(ArrayList<Doc>) response.body().getResponse().getDocs();
                    String output = response.body().getResponse().getDocs().get(1).getWebUrl();
                    Log.i("URL: ", output);

                    for (int i = 0; i < docList.size(); i++) {
                        docList.add(response.body().getResponse().getDocs().get(i));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ContentNyt> call, Throwable t) {
                Log.i("NYT", "FAILED!");
            }
        });
    }*/

/*    public static void connectTwitterwithTokenCOPY(String bearerToken) {
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

        Call<TwitterContent> timelineCall = timelineService.getTimeline(bearerToken,"application/json;charset=utf-8", "NASA_Astronauts", 5);//twitterapi

        timelineCall.enqueue(new Callback<TwitterContent>() {
            @Override
            public void onResponse(Call<TwitterContent> call, Response<TwitterContent> response) {
                Log.i("@GET WITH TOKEN", " GOT TO GETTIMELINE() ONRESPONSE");

            }

            @Override
            public void onFailure(Call<TwitterContent> call, Throwable t) {
                Log.i("@GET WITH TOKEN", "FAILED");
            }
        });
    }*/
}
