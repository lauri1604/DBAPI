package dbapi.models.planets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetsItem {

    @JsonProperty("image")
    private String image;

    @JsonProperty("deletedAt")
    private Object deletedAt;

    @JsonProperty("isDestroyed")
    private boolean isDestroyed;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private int id;

    public String getImage(){
        return image;
    }

    public Object getDeletedAt(){
        return deletedAt;
    }

    public boolean isIsDestroyed(){
        return isDestroyed;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return
                "PlanetsItem{" +
                        "id = '" + id + '\'' +
                        "name = '" + name + '\'' +
                        "isDestroyed = '" + isDestroyed + '\'' +
                        "description = '" + description + '\'' +
                        "image = '" + image + '\'' +
                        "deletedAt = '" + deletedAt + '\'' +
                        "}";
    }
}