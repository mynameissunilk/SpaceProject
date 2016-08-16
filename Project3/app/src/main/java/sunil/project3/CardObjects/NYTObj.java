package sunil.project3.CardObjects;

/**
 * Created by ander on 8/16/2016.
 */
public class NYTObj extends CardObject {

    String mHeadline, mSnippet, mWebUrl, mDate;

    public NYTObj(String headline, String snippet, String url, String date){
        mHeadline = headline; mSnippet = snippet; mWebUrl = url; mDate = date;
    }

    public String getmHeadline() {
        return mHeadline;
    }

    public void setmHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    public String getmSnippet() {
        return mSnippet;
    }

    public void setmSnippet(String mSnippet) {
        this.mSnippet = mSnippet;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
