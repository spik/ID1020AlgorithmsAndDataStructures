package lab5;

import java.util.Scanner;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;
public class Paths {

	public static void main(String[] args) {

		Graph graph = DataSource.load();

		Scanner in = new Scanner(System.in);
		System.out.println("Press 1 to test Connected Graph, press 2 to test Shortest Path or press 3 to test Dijkstra's Algorithm");
		int whichClass = in.nextInt();
		in.close();

		//Test ConnectedGraph
		if(whichClass == 1)
		{
			ConnectedGraph connectedGraph = new ConnectedGraph(graph);

			//We call the class ConnectedGrapgh to get the count, i.e. the number of components
			int count = connectedGraph.count();

			//If the number of components is 0, all vertices are connected and the graph has only one component
			if (count == 0)
			{
				System.out.println("The graph is fully connected");
			}

			//The number of components is >0, so the graph is not fully connected
			else
			{
				System.out.println("The graph is not fully connected.");
				System.out.println("Number of components: " + count);
			}
		}

		//Test ShortestPath
		if(whichClass == 2)
		{
			Vertex sourceVertex = new Vertex();
			Vertex destinationVertex = new Vertex();
			
			int sourceInt = 0;
			int destinationInt = 0;

			//Loop trough all the vertices to find the requested vertices
			for (Vertex i: graph.vertices())
			{
				if (i.label.equals("Renyn"))
				{
					sourceVertex = i;
					sourceInt = i.id;
				}

				if (i.label.equals("Parses"))
				{
					destinationVertex = i;
					destinationInt = i.id;
				}
			}

			ShortestPath shortestPath = new ShortestPath();
			shortestPath.breadthFirstSearch(graph, sourceInt);

			//If there exists a path between the source and the destination
			if (shortestPath.hasPathTo(destinationInt)) 
			{
				//Print the shortest path
				System.out.println("Distance from " + sourceVertex + " to " + destinationVertex + " is: " + shortestPath.distTo(destinationInt));
				
				System.out.println(sourceInt);
				
				//This loop prints all vertices in our shortest path
				for (int i : shortestPath.pathTo(destinationInt)) 
				{   
						System.out.println(i);
				}
				System.out.println();
			}

			else 
			{
				System.out.println(sourceInt + " and " + destinationInt + " are not connected");
			}
		}
		
		//Test DijkstrasAlgorithm
		if(whichClass == 3)
		{
			Vertex sourceVertex = new Vertex();
			Vertex destinationVertex = new Vertex();
			
			int sourceInt = 0;
			int destinationInt = 0;

			//Loop trough all the vertices to find the requested vertices
			for (Vertex i: graph.vertices())
			{
				if (i.label.equals("Renyn"))
				{
					sourceVertex = i;
					sourceInt = i.id;
				}

				if (i.label.equals("Parses"))
				{
					destinationVertex = i;
					destinationInt = i.id;
				}
			}
			
			DijkstrasAlgorithm da = new DijkstrasAlgorithm(graph, sourceInt);
			
			//If there exists a path between the source and the destination
			if (da.hasPathTo(destinationInt)) 
			{
				//Print the shortest path
				System.out.println("Distance from " + sourceVertex + " to " + destinationVertex + " is: " + da.distTo(destinationInt));
				
				//This loop prints all vertices in our shortest path
				for (Edge e : da.pathTo(destinationInt)) 
				{   
						System.out.println(e);
				}
				System.out.println();
			}

			else 
			{
				System.out.println(sourceInt + " and " + destinationInt + " are not connected");
			}
		}
	}
}
