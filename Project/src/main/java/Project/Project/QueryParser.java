package Project.Project;

public class QueryParser {
	
	public String originalSearchQuery;
	public String [] terms;
	public SortType type;
	public boolean isAsc;
	
	public QueryParser(String query)
	{
		
		//If the query, i.e. the user input, contains "orderby"
		if(query.contains("orderby"))
		{
			//Split the query using the key "orderby". Put the two parts of the query in an array. 
			String [] sections = query.split(" orderby ");
			
			//The termsSection is the section of the sections array that contains the words searched for.
			String termsSection = sections[0];
			
			//Make another split using the key " " (whitespace) to but each requested search-word in their own index in the array "terms"
			terms = termsSection.split(" ");
			
			//Perform the same split as above but for the second section of the array that contains the property and direction
			String[] orderSection = sections[1].split(" ");
			
			//Because the orderSection should only contain two indices (properties and direction), throw an exception if this is not the case
			if(orderSection.length != 2)
			{
				throw new IllegalArgumentException("You have to specify property and direction when using the orderby-command");
			}
			
			//Get the value at index [0] in the orderSection array. This value will contain the property. 
			type = SortType.valueOf(orderSection[0]);
			
			//Get the value at index [1] in the orderSection array. This value will contain the direction. 
			isAsc = orderSection[1].equalsIgnoreCase("asc");
		}
		
		//In this case the key "orderby" was not used in the query, set default commands for the unspecified properties
		else
		{
			//We still want to split the search-words used and put them in the "terms" array. 
			terms = query.split(" ");
			
			//Set type to popularity, which now is our default sort property for when no property has been specified
			type = SortType.popularity;
			
			//Set isAsc to "true", the default direction is now ascending
			isAsc = true;
		}
	}
	 
	//The only accepted sort types are popularity, occurrence and relevance. 
	public enum SortType 
	{
		popularity,
	    occurrence, 
	    relevance
	}
}

