package lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class ShortestPath {

	public boolean [] isVisited;
	public int[] cameFrom;
	private static final int INFINITY = Integer.MAX_VALUE;
	private int[] distTo;      

	//Instance method
	public void breadthFirstSearch(Graph graph, int source)
	{
		isVisited = new boolean[graph.numberOfVertices()];
		distTo = new int[graph.numberOfVertices()];
		cameFrom = new int[graph.numberOfVertices()];
		bfs(graph, source);
	}

	public void bfs(Graph graph, int source)
	{
		Queue <Integer> queue = new Queue<Integer>();

		//Loop through all vertices and mark their distance as INFINITY
		for (int currentVertex = 0; currentVertex < graph.numberOfVertices(); currentVertex++)
			distTo[currentVertex] = INFINITY;

		//Set the distance to the first vertex, the source, to 0. 
		distTo[source] = 0;

		//Put the first vertex, source, in the queue
		queue.enqueue(source);

		//Mark the first vertex as visited
		isVisited[source] = true;

		//As long as the queue is not empty
		while(!queue.isEmpty())
		{
			//pull off the next vertex from the queue and call it currentVertex
			//The first loop, this will be the source vertex
			int currentVertex = queue.dequeue();

			//For every edge adj to currentVertex
			for(Edge currentEdge : graph.adj(currentVertex))
			{
				//Create an int nextVertex and set its value to the vertex "to" of the edge. 
				int nextVertex = currentEdge.to;

				//If nextVertex is not yet visited
				if(!isVisited[nextVertex])
				{
					//Note that we reached nextVertex from currentVertex, i.e. we came from the current vertex
					cameFrom[nextVertex] = currentVertex;

					//increment distance 
					distTo[nextVertex] = distTo[currentVertex] + 1;

					//Mark the vertex as visited
					isVisited[nextVertex] = true;

					//Put the vertex in the queue
					queue.enqueue(nextVertex);
				}
			}
		}
	}

	//Returns the distance from the source to the vertex
	public int distTo(int v) 
	{
		return distTo[v];
	}

	//Returns if the vertex is visited. If it is, we know there exists a path to it. 
	public boolean hasPathTo(int v) {
		return isVisited[v];
	}

	//Returns the shortest path from the source to the destination vertex
	public Iterable<Integer> pathTo(int destinationVertex) 
	{
		//No path exists between the source vertex and the destination vertex
		if (!hasPathTo(destinationVertex)) 
			return null;
		
		Stack<Integer> path = new Stack<Integer>();
		int currentVertex;

		//As long as we the distance to currentVertex is not 0, i.e. we are not at the source, 
		//we push currentVertex in the stack containing all nodes in the path
		//and then go back in the path to the node we came from. 
		for (currentVertex = destinationVertex; distTo[currentVertex] != 0; currentVertex = cameFrom[currentVertex])
			path.push(currentVertex);

		return path;
	}
}
