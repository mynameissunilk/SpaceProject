package sunil.project3.ApiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import sunil.project3.NYT.ContentNyt;
import sunil.project3.NYT.Doc;

/**
 * Created by sunil on 8/15/16.
 */
public interface NytApiService {

    @GET("?{api-key}&{sort}&{fq=type_of_material}&{fq=section_name}&{q}")
    Call<Doc>getArticle(
            @Header("api-key") String apikey,
            @Query("sort") String sort,
            @Query("fq=type_of_material") String material,
            @Query("fq=section_name") String section,
            @Query("q") String query);
    // TODO: make sure you don't have to exclude &s in an interface method declaration
}
