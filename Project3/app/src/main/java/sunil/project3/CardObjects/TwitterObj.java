package sunil.project3.CardObjects;

/**
 * Created by ander on 8/16/2016.
 */
public class TwitterObj extends CardObject {

    String mUrl, mName, mTweet, mDate, mUser;

    public TwitterObj(String url, String name, String tweet, String date, String user){
        mUrl = url; mName = name; mTweet = tweet; mDate = date; mUser = user;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTweet() {
        return mTweet;
    }

    public void setmTweet(String mTweet) {
        this.mTweet = mTweet;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }
}
