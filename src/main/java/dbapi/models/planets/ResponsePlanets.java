package dbapi.models.planets;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsePlanets {

	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("links")
	private Links links;

	@JsonProperty("items")
	private List<Planet> items;

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setItems(List<Planet> items){
		this.items = items;
	}

	public List<Planet> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePlanets{" +
			"meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}