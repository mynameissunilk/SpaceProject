package sunil.project3;

/**
 * Created by sunil on 8/17/16.
 */
public class GuardianArticle {
    private String Title;
    private String URL;

    public GuardianArticle(String t, String u){
        Title = t;
        URL = u;
    }

    public void setTitle(String t){Title = t;}
    public String getTitle(){return Title;}

    public void setURL(String u){URL = u;}
    public String getURL(){return URL;}
}
