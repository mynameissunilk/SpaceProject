package sunil.project3.ApiServices;

/**
 * Created by sunil on 8/15/16.
 */
public class Credentials {

    public static final String guardianURL = "http://content.guardianapis.com/";
    public static final String guardianKey = "84a85242-3b93-42f2-8952-138f45f50dee";

    public static final String nytURL = "https://api.nytimes.com/svc/search/v2/";
    public static final String nytKey ="4a3efda1da0840c5929ff4e7758f0b59";

            //"4a3efda1da0840c5929ff4e7758f0b59";
            //"73f5f97cf52247a7a83b9f24299a23e2";

    public static final String nasaURL = "https://api.nasa.gov/planetary/";
    public static final String nasaKey = "IsXUyhCSGkUP5QHrAAYITkO2PyqGeawPISAwZXRr";

    public static final String nprURL = "http://api.npr.org/";
    public static final String nprKey = "MDI1OTA2MzQxMDE0NzEzODI2NTU4NjNkMA000";

    public static final String twittokenURL = "https://api.twitter.com/";
    public static final String twitcons = "tJF1TxJoPHGrjyTMzIAGqjpaE";
    public static final String twitsecr = "T8IuaJBtYACzTRuWcPtIuVxEFDEFK6tgapgOqDbrS8IcGNu2NZ";
    public static final String twitTbcrypt = twitcons + ":" + twitsecr;
    public static final String twitBaseURL = "https://api.twitter.com/"; //ugh i'm dumb
    public static String twitenc64;
    public static String twitterTokenHeader="";
    public static String twitToken="";

}
