package dbapi.models.characters;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import dbapi.models.Links;
import dbapi.models.Meta;

public class Response{

	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("links")
	private Links links;

	@JsonProperty("items")
	private List<CharactersItem> items;

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}

	public List<CharactersItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}