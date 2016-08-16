package sunil.project3.ApiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.project3.NYT.Doc;


public interface NytApiService {

    // endpoints don't have to be perfect
    // retrofit overrides errything... (thankgod)
    @GET("/articlesearch.json?&sort=newest&fq=type_of_material:(%22News%22)&fq=section_name:(%22Science%22)&q=nasa&q=space")
    Call<Doc>getArticle(
            @Query("api-key") String apikey);

    // TODO: make sure you don't have to exclude &s in an interface method declaration

}
