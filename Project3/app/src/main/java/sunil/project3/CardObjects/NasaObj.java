package sunil.project3.CardObjects;

/**
 * Created by owlslubic on 8/16/16.
 */
public class NasaObj extends CardObject{

    String mUrl, mTitle, mExplanation;

    public NasaObj(String url, String title, String explanation){
        mUrl = url;
        mTitle = title;
        mExplanation = explanation;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmExplanation() {
        return mExplanation;
    }

    public void setmExplanation(String mExplanation) {
        this.mExplanation = mExplanation;
    }
}
