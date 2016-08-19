
package sunil.project3.Twitter;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Description {

    @SerializedName("urls")
    @Expose
    private List<Url___> urls = new ArrayList<Url___>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url___> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url___> urls) {
        this.urls = urls;
    }

}
