package sunil.project3.CardObjects;

/**
 * Created by ander on 8/16/2016.
 */
public class GuardianObj extends CardObject{

    String mTitle, mDate, mUrl;

    public GuardianObj(String title, String date, String url){
        mUrl = url;  mDate = date; mTitle = title;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
