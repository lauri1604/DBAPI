package dbapi.models.characters;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Character {

    @JsonProperty("image")
    private String image;

    @JsonProperty("deletedAt")
    private Object deletedAt;

    @JsonProperty("race")
    private String race;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("affiliation")
    private String affiliation;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private int id;

    @JsonProperty("ki")
    private String ki;

    @JsonProperty("maxKi")
    private String maxKi;

    public String getImage() {
        return image;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getKi() {
        return ki;
    }

    public String getMaxKi() {
        return maxKi;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id = '" + id + '\'' + " " +
                "name = '" + name + '\'' +  " " +
                "ki = '" + ki + '\'' + " " +
                "maxKi = '" + maxKi + '\'' +  " " +
                "race='" + race + '\'' + " " +
                "gender='" + gender + '\'' + " " +
                "description='" + description + '\'' + " " +
                "image='" + image + '\'' +  " " +
                "affiliation='" + affiliation + '\'' + " " +
                "deletedAt='" + deletedAt + '\'' + " " +
                "}";
    }
}