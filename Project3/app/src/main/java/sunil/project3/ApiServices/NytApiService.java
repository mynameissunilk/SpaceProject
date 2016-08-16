package sunil.project3.ApiServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.project3.Guardian.Content;
import sunil.project3.NYT.ContentNyt;
import sunil.project3.NYT.Doc;


public interface NytApiService {



    // endpoints don't have to be perfect
    // retrofit overrides errything... (thankgod)
    @GET("/svc/search/v2/articlesearch.json?sort=newest&fq=type_of_material=News&fq=section_name=Science&q=nasa+space")
    Call<ContentNyt>getArticle(
            @Query("api-key") String apikey);


}
