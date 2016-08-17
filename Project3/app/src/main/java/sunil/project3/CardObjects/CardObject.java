package sunil.project3.CardObjects;

import java.util.List;

/**
 * Created by ander on 8/16/2016.
 */
public class CardObject {


    //i think this was just from testing something
TwitterObj TwitterObjects;


    public TwitterObj getTwitterObjects() {
        if (TwitterObj.class == null){
            TwitterObj twitterObj = new TwitterObj("a","b","c","d","e");
            return twitterObj;
        }
        return TwitterObjects;
    }

    public void setTwitterObjects(TwitterObj twitterObjects) {
        TwitterObjects = twitterObjects;
    }
}
