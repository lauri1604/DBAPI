package dbapi.models.characters;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta{

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
			"totalItems = '" + totalItems + '\'' + 
			",itemsPerPage = '" + itemsPerPage + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",currentPage = '" + currentPage + '\'' + 
			",itemCount = '" + itemCount + '\'' + 
			"}";
		}
}