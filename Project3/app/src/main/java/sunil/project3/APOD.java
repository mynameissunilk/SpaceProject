package sunil.project3;

import sunil.project3.CardObjects.CardObject;

/**
 * Created by sunil on 8/17/16.
 */
public class APOD extends CardObject{
    private String Title;
    private String Explanation;
    private String URL;

    public APOD(String t, String e, String u){
        Title = t;
        Explanation = e;
        URL = u;
    }

    public void setTitle(String t){Title = t;}
    public String getTitle(){return Title;}

    public void setExplanation(String e){Explanation = e;}
    public String getExplanation(){return Explanation;}

    public void setURL(String u){URL = u;}
    public String getURL(){return URL;}

}
