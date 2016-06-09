
package me.jimmyshaw.luxuryfanapp.edmunds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Year {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("styles")
    @Expose
    private List<Style> styles = new ArrayList<Style>();

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
     * @return The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return The styles
     */
    public List<Style> getStyles() {
        return styles;
    }

    /**
     * @param styles The styles
     */
    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

}
