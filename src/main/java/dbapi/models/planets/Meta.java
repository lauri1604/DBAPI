package dbapi.models.planets;
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

	public void setTotalItems(int totalItems){
		this.totalItems = totalItems;
	}

	public int getTotalItems(){
		return totalItems;
	}

	public void setItemsPerPage(int itemsPerPage){
		this.itemsPerPage = itemsPerPage;
	}

	public int getItemsPerPage(){
		return itemsPerPage;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public void setItemCount(int itemCount){
		this.itemCount = itemCount;
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