package sunil.project3.ApiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import sunil.project3.ContentNasa;

/**
 * Created by sunil on 8/15/16.
 */
public interface NasaApiService {
    @GET("{api_key}")
    Call<ContentNasa> getAPOD(@Header("api_key")String apikey);
}
