package sunil.project3.ApiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.project3.Guardian.ResponseBody;


public interface GuardianApiService {
    //@Headers({"api-key"+":"+})
    @GET("/search?q=space")
    Call<ResponseBody>getArticles(
            @Query("api-key") String apikey
    );
}
