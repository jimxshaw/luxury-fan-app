
package me.jimmyshaw.lexusfanapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Style {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("submodel")
    @Expose
    private Submodel submodel;
    @SerializedName("trim")
    @Expose
    private String trim;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The submodel
     */
    public Submodel getSubmodel() {
        return submodel;
    }

    /**
     * @param submodel The submodel
     */
    public void setSubmodel(Submodel submodel) {
        this.submodel = submodel;
    }

    /**
     * @return The trim
     */
    public String getTrim() {
        return trim;
    }

    /**
     * @param trim The trim
     */
    public void setTrim(String trim) {
        this.trim = trim;
    }

}
