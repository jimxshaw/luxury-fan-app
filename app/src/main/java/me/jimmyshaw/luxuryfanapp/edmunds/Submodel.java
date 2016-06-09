
package me.jimmyshaw.luxuryfanapp.edmunds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Submodel {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("niceName")
    @Expose
    private String niceName;

    /**
     * @return The body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return The modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName The modelName
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return The niceName
     */
    public String getNiceName() {
        return niceName;
    }

    /**
     * @param niceName The niceName
     */
    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

}
