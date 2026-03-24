package dbapi.models.planets;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Planet {

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

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setIsDestroyed(boolean isDestroyed){
		this.isDestroyed = isDestroyed;
	}

	public boolean isIsDestroyed(){
		return isDestroyed;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Planet{" +
                    "id = '" + id + '\'' + " " +
                    "name = '" + name + '\'' +  " " +
                    "isDestroyed = '" + isDestroyed + '\'' + " " +
                    "description = '" + description + '\'' + " " +
                    "image = '" + image + '\'' + " " +
                    "deletedAt = '" + deletedAt + '\'' + " "+
			"}";
		}
}