package lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class ConnectedGraph {
	
	public boolean [] isVisited;
	//public int[] cameFrom;
	public int[] componentNumber;
	private int count;
	
	public ConnectedGraph (Graph graph)
	{
		//All indices of the array are false by default 
		isVisited = new boolean[graph.numberOfVertices()];
		
		//All componentNumbers of the array are 0 by default 
		componentNumber = new int[graph.numberOfVertices()];
		
		//Loop through all vertices in the graph
		for (int currentVertex = 0; currentVertex < graph.numberOfVertices(); currentVertex++)
		{
			//If the vertex is not yet visited
			if(!isVisited[currentVertex])
			{
				//Call dfs with the current vertex 
				depthFirstSearch(graph, currentVertex);
				
				//When we return from the search, all vertices connected to the current vertex are visited
				//And has been marked with the current count. Now increment the count for next search
				count++;
			}
		}
	}
	
	public void depthFirstSearch(Graph graph, int currentVertex)
	{
		//We mark the current vertex as visited
		isVisited[currentVertex] = true;
		
		//We set the current vertex component number to the current count
		componentNumber[currentVertex] = count;

		//Loop through all edges adj to the current vertex
		for(Edge currentEdge: graph.adj(currentVertex))
		{
			//If the vertex at the end of the edge currentEdge, i.e. the int "to", is not visited
			if(!isVisited[currentEdge.to])
			{
				//We make a recursive call to dfs to repeat all steps for the next vertex
				depthFirstSearch(graph, currentEdge.to);
				//cameFrom[currentEdge.from]=currentVertex;
			}
		}
	}
	
	//This method returns the count, i.e. the number of components in the graph
	public int count()
	{ 
		return count; 
	}

}
