package sunil.project3.ApiServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import sunil.project3.Guardian.Content;
import sunil.project3.Guardian.Result;

/**
 * Created by sunil on 8/15/16.
 */
public interface GuardianApiService {
    @GET("/search/{q}&{api-key}")
    Call<Result>getArticle(
            @Header("api-key") String apikey,
            @Query("q") String query
    );
}
