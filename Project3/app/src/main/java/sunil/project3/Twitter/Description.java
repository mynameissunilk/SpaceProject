package sunil.project3.Twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunil on 8/18/16.
 */
public class Description {

    private List<Object> urls = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The urls
     */
    public List<Object> getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     *     The urls
     */
    public void setUrls(List<Object> urls) {
        this.urls = urls;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
