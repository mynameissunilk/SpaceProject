
package sunil.project3.Twitter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Entities___ {

    @SerializedName("url")
    @Expose
    private Url_____ url;
    @SerializedName("description")
    @Expose
    private Description_ description;

    /**
     * 
     * @return
     *     The url
     */
    public Url_____ getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(Url_____ url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Description_ getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Description_ description) {
        this.description = description;
    }

}
