package dbapi.models.planets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links{

	@JsonProperty("next")
	private String next;

	@JsonProperty("previous")
	private String previous;

	@JsonProperty("last")
	private String last;

	@JsonProperty("first")
	private String first;

	public String getNext(){
		return next;
	}

	public String getPrevious(){
		return previous;
	}

	public String getLast(){
		return last;
	}

	public String getFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"next = '" + next + '\'' + 
			",previous = '" + previous + '\'' + 
			",last = '" + last + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}