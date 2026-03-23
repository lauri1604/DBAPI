package dbapi.models.planets;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta{

	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("links")
	private Links links;

	@JsonProperty("items")
	private List<PlanetsItem> items;

	@JsonProperty("totalItems")
	private int totalItems;

	@JsonProperty("itemsPerPage")
	private int itemsPerPage;

	@JsonProperty("totalPages")
	private int totalPages;

	@JsonProperty("currentPage")
	private int currentPage;

	@JsonProperty("itemCount")
	private int itemCount;

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}

	public List<PlanetsItem> getItems(){
		return items;
	}

	public int getTotalItems(){
		return totalItems;
	}

	public int getItemsPerPage(){
		return itemsPerPage;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public int getItemCount(){
		return itemCount;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			",items = '" + items + '\'' + 
			",totalItems = '" + totalItems + '\'' + 
			",itemsPerPage = '" + itemsPerPage + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",currentPage = '" + currentPage + '\'' + 
			",itemCount = '" + itemCount + '\'' + 
			"}";
		}
}