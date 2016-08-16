package sunil.project3.ApiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.project3.ContentNasa;


public interface NasaApiService {

    @GET("/planetary/apod")
    Call<ContentNasa> getAPOD (@Query("api_key")String key);

}
