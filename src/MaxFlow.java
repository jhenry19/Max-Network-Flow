import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {
	int TOTAL_NODES;
	int[][] graph; // 2D array adjacency matrix hold the edges in the graph and their weights
	int source;
	int sink;

	// Default constructor initialized needed variables
	MaxFlow(int[][] graph, int source, int sink){
		this.graph = graph;
		this.source = source;
		this.sink = sink;
		TOTAL_NODES = graph.length;
	}

	/**
	 * Determines the maximum flow possible through a directed graph. Uses the Ford-Fulkerson algorithm.
	 * @return and int representing the maximum amount of flow through the directed graph
	 */
	public int MaximumFlow() {
		// Generates a residual graph, identical to the graph at the beginning
		int[][] residualGraph = new int[TOTAL_NODES][TOTAL_NODES];
		for (int i = 0; i < TOTAL_NODES; i++)
			System.arraycopy(graph[i], 0, residualGraph[i], 0, TOTAL_NODES);


		int[] path = new int[TOTAL_NODES]; // used to store augmented path
		int maxFlow = 0; // totals the amount of flows through each augment path

		// While an augmented path exits in the residual graph
		while (BFS(residualGraph, path)) {
			// Max flow is updated with the amount of the flow through the augmented path
			maxFlow += augment(path, residualGraph);
		}
		return maxFlow;
	}

	/**
	 * Determines the bottleneck of an augmenting path and updates the residual graph with that flow expressed
	 * @param path the current path found by BFS
	 * @param residualGraph the residual graph of the flow
	 * @return an int of how much flow the augmented path can allow
	 */
	int augment(int[] path, int[][] residualGraph){
		int node;
		int pathFlow = Integer.MAX_VALUE;

		// Work backwards through the augmented path to find the edge with the lowest capacity (bottleneck)
		for (int i = sink; i != source; i = path[i]) {
			node = path[i];
			pathFlow = Math.min(pathFlow, residualGraph[node][i]);
		}

		// prints the flow of this augmented path
		System.out.print("\tflow: " + pathFlow + "\n");

		// Update residual graph with new flow
		for (int i = sink; i != source; i = path[i]) {
			node = path[i];
			residualGraph[node][i] -= pathFlow;
			residualGraph[i][node] += pathFlow;
		}
		return pathFlow;
	}

	//https://favtutor.com/blogs/breadth-first-search-java
	//https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	public boolean BFS(int[][] rGraph, int[] augPath) {
		Queue<Integer> queue = new LinkedList<>(); //Queue used for BFS

		// Visited array used to mark nodes as found by BFS. Set to false to start
		boolean[] visited = new boolean[TOTAL_NODES];
		for (int i = 0; i < TOTAL_NODES; i++) {
			visited[i] = false;
		}

		// BFS started by adding s to queue and visiting it
		queue.add(source);
		visited[source] = true;

		int currNode;
		while (queue.size() != 0) { // while there are nodes in the queue
			currNode = queue.poll();
			for (int adjacentNode = 0; adjacentNode < TOTAL_NODES; adjacentNode++) {
				// If the adjacent node has not been visited and the nodes are adjacent (there is an edge connecting them)
				if (!visited[adjacentNode] && rGraph[currNode][adjacentNode] > 0) {
					if (adjacentNode == sink) { // If the path reaches the sink node (making it a valid augmented path)
						augPath[adjacentNode] = currNode;
						printPath(augPath); // Prints the path as per assignment requirements
						return true;
					}
					// else continues through BFS
					queue.add(adjacentNode);
					augPath[adjacentNode] = currNode;
					visited[adjacentNode] = true;
				}
			}
		}
		return false;
	}

	/**
	 * Prints augmented paths as they are found
	 */
	void printPath(int[] path) {
		int[] pathOrdered = new int[TOTAL_NODES];
		int counter = 0;
		// Saves the nodes in the path in reverse order (start with sink node)
		for (int node = sink; node != source; node = path[node]) {
			pathOrdered[counter] = node;
			counter++;
		}
		pathOrdered[counter] = 0; // node s is added to each path last

		int nodeIndex;
		System.out.print("Path:");
		for (int i = counter; i >= 0; i--) {
			nodeIndex = pathOrdered[i] + 1; // adjusted so printing matches node indices on homework

			if (nodeIndex == 1) System.out.print(" s");      // s is printed for source node
			else if (nodeIndex == 8) System.out.print(" t"); // t is printed for sink node
			else System.out.print(" " + nodeIndex);          // node index is printed for all others
		}
	}
}
