
package sunil.project3.NPR;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Link_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("$text")
    @Expose
    private String $text;

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
     *     The $text
     */
    public String get$text() {
        return $text;
    }

    /**
     * 
     * @param $text
     *     The $text
     */
    public void set$text(String $text) {
        this.$text = $text;
    }

}
