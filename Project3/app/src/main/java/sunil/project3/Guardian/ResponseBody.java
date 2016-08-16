
package sunil.project3.Guardian;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseBody {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("isHosted")
    @Expose
    private Boolean isHosted;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The sectionId
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * 
     * @param sectionId
     *     The sectionId
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The sectionName
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The webPublicationDate
     */
    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    /**
     * 
     * @param webPublicationDate
     *     The webPublicationDate
     */
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    /**
     * 
     * @return
     *     The webTitle
     */
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * 
     * @param webTitle
     *     The webTitle
     */
    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    /**
     * 
     * @return
     *     The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The webUrl
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The apiUrl
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * 
     * @param apiUrl
     *     The apiUrl
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * 
     * @return
     *     The isHosted
     */
    public Boolean getIsHosted() {
        return isHosted;
    }

    /**
     * 
     * @param isHosted
     *     The isHosted
     */
    public void setIsHosted(Boolean isHosted) {
        this.isHosted = isHosted;
    }

}
