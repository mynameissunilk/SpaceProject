package sunil.project3;


import sunil.project3.CardObjects.CardObject;

public class NprArticle extends CardObject{

    private String Title;
    private String Paragraph;
    private String Date;
    private String URL;

    public NprArticle(String title, String parag, String date ,String url){
        Title = title;
        Paragraph = parag;
        Date = date;
        URL = url;
    }

    public void setTitle(String t){Title = t;}
    public String getTitle(){return Title;}

    public void setParagraph(String p){Paragraph = p;}
    public String getParagraph(){return Paragraph;}

    public void setDate(String d){Date = d;}
    public String getDate(){return Date;}

    public void setURL(String u){URL = u;}
    public String getURL(){return URL;}


}
