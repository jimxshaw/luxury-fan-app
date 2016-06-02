
package me.jimmyshaw.lexusfanapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Models {

    @SerializedName("models")
    @Expose
    private List<Model> models = new ArrayList<Model>();
    @SerializedName("modelsCount")
    @Expose
    private Integer modelsCount;

    /**
     * @return The models
     */
    public List<Model> getModels() {
        return models;
    }

    /**
     * @param models The models
     */
    public void setModels(List<Model> models) {
        this.models = models;
    }

    /**
     * @return The modelsCount
     */
    public Integer getModelsCount() {
        return modelsCount;
    }

    /**
     * @param modelsCount The modelsCount
     */
    public void setModelsCount(Integer modelsCount) {
        this.modelsCount = modelsCount;
    }

}
