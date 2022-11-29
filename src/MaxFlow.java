import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {
	int TOTAL_NODES = 8;
	int[][] graph;
	int s;
	int t;

	MaxFlow(int[][] graph, int s, int t){
		this.graph = graph;
		this.s = s;
		this.t = t;
	}

	public int MaximumFlow() {
		int[][] residualGraph = new int[TOTAL_NODES][TOTAL_NODES]; // residual graph is identical to graph to start

		// residualGraph is a copy of graph at the beginning
		for (int i = 0; i < TOTAL_NODES; i++)
			System.arraycopy(graph[i], 0, residualGraph[i], 0, TOTAL_NODES);

		int[] path = new int[TOTAL_NODES]; //used to store augmented path
		int maxFlow = 0;

		while (BFS(residualGraph, s, t, path)) {
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
		for (int i = t; i != s; i = path[i]) {
			node = path[i];
			pathFlow = Math.min(pathFlow, residualGraph[node][i]);
		}

		System.out.print("\tflow: " + pathFlow + "\n");

		// Update residual graph with new flow
		for (int i = t; i != s; i = path[i]) {
			node = path[i];
			residualGraph[node][i] -= pathFlow;
			residualGraph[i][node] += pathFlow;
		}
		return pathFlow;
	}

	//https://favtutor.com/blogs/breadth-first-search-java
	//https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	public boolean BFS(int[][] rGraph, int s, int t, int[] path) {
		Queue<Integer> queue = new LinkedList<>(); //Queue used for BFS

		// Visited array used to mark nodes as found by BFS. Set to false to start
		boolean[] visited = new boolean[TOTAL_NODES];
		for (int i = 0; i < TOTAL_NODES; i++) {
			visited[i] = false;
		}

		// BFS started by adding s to queue and visiting it
		queue.add(s);
		visited[s] = true;

		int sourceNode;
		while (queue.size() != 0) { // while there are nodes in the queue
			sourceNode = queue.poll();
			for (int destinationNode = 0; destinationNode < TOTAL_NODES; destinationNode++) {
				// If the destination has not been visited and there is an edge from source to the destination
				if (!visited[destinationNode] && rGraph[sourceNode][destinationNode] > 0) {
					if (destinationNode == t) { // If the path is complete (arrived at sink node)
						path[destinationNode] = sourceNode;
						printPath(path);
						return true;
					}
					queue.add(destinationNode);
					path[destinationNode] = sourceNode;
					visited[destinationNode] = true;
				}
			}
		}
		return false;
	}

	void printPath(int[] path) {
		int[] pathOrdered = new int[TOTAL_NODES];
		int counter = 0;
		for (int node = t; node != s; node = path[node]) {
			pathOrdered[counter] = node;
			counter++;
		}
		pathOrdered[counter] = 0; // node s is added to each path last

		int nodeIndex;
		System.out.print("Path:");
		for (int i = counter; i >= 0; i--) {
			nodeIndex = pathOrdered[i] + 1;

			if (nodeIndex == 1) System.out.print(" s");
			else if (nodeIndex == 8) System.out.print(" t");
			else System.out.print(" " + nodeIndex);
		}
	}


}
