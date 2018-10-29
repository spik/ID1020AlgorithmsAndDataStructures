package lab5;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class DijkstrasAlgorithm {

	private Edge[] cameFrom;
	private double[] distTo;
	private IndexMinPQ<Double> pq;

	public DijkstrasAlgorithm(Graph graph, int source)
	{
		cameFrom = new Edge [graph.numberOfVertices()];
		distTo = new double [graph.numberOfVertices()];
		pq = new IndexMinPQ<Double>(graph.numberOfVertices());

		//Loop through all vertices and mark their distance as INFINITY
		for (int currentVertex = 0; currentVertex < graph.numberOfVertices(); currentVertex++)
			distTo[currentVertex] = Double.POSITIVE_INFINITY;

		//Set the distance to the the source to 0.0. 
		distTo[source] = 0.0;

		//Put the source on the queue
		pq.insert(source, 0.0);

		//As long as the queue is not empty
		while(!pq.isEmpty())
		{
			//Take the edge closest to the source off the priority queue 
			//Always choose to work with the vertex with the shortest distance
			//that way we know there are no shorter paths to that vertex. 
			int currentVertex = pq.delMin();

			//Relax all the edges adj to the current vertex
			for(Edge currentEdge : graph.adj(currentVertex))
			{
				relax(currentEdge);
			}
		}
	}

	public void relax (Edge edge)
	{
		int vertex1 = edge.from;
		int vertex2 = edge.to;

		//If the distance to vertex2 is bigger than the distance to vertex1 + the weight of the edge
		//we have found a shorter path to vertex2. 
		if(distTo[vertex2] > distTo[vertex1] + edge.weight)
		{
			//Update distance to vertex2 to be the shorter path
			distTo[vertex2] = distTo[vertex1] + edge.weight;

			//update cameFrom so that cameFrom vertex2 is now the current edge
			cameFrom[vertex2] = edge;

			//If vertex2 is already on the queue, we have now found a path that is shorter, and we need to update the distance
			if (pq.contains(vertex2))
			{
				//distTo[vertex2] is now the shorter distance (as we updated the distance above)
				pq.decreaseKey(vertex2, distTo[vertex2]);
			}
			
			//vertex2 is not yet in the queue, so we insert it. 
			else
			{
				pq.insert(vertex2, distTo[vertex2]);
			}
		}
	}

	//Returns the distance from the source to the vertex
	public double distTo(int v) 
	{
		return distTo[v];
	}

	//Returns if the vertex is visited, i.e. if the vertex distance is not marked as infinity. 
	//If it is visited, we know there exists a path to it. 
	public boolean hasPathTo(int currentVertex) 
	{
		return distTo[currentVertex] < Double.POSITIVE_INFINITY;
	}

	//Returns the shortest path from the source to the destination
	public Iterable<Edge> pathTo(int destination) 
	{
		if (!hasPathTo(destination)) 
			return null;

		Stack<Edge> path = new Stack<Edge>();
		Edge currentEdge;

		//Go through all the edges from the destination as long as we have another edge
		//and then go back in the path to the edge we came from. 
		for (currentEdge = cameFrom[destination]; currentEdge != null; currentEdge = cameFrom[currentEdge.from])
			path.push(currentEdge);

		return path;
	}

}
