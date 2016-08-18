package sunil.project3.ApiServices;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import sunil.project3.NPR.ContentNpr;

public interface NprService {

    @GET("query?fields=title,storyDate,text&id=1026&num=110&type=topic&output=JSON&numResults=10")
    Call<ContentNpr>getArticle(
            @Query("apiKey") String apikey);
}
