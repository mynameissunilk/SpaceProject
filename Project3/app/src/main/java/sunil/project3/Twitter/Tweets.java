package sunil.project3.Twitter;

import java.util.ArrayList;

/**
 * Created by sunil on 8/18/16.
 */
public class Tweets {

    ArrayList<Tweet> tweets = new ArrayList<>();

    public Tweets(){}

    public Tweets(ArrayList<Tweet> tweets){
        this.tweets = tweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
