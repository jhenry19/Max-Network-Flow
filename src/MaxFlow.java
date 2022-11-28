import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {
	int TOTAL_NODES = 8;

	MaxFlow(){}

	public int MaximumFlow(int graph[][], int s, int t) {
		int residualGraph[][] = new int[TOTAL_NODES][TOTAL_NODES]; // residual graph is identical to graph to start

		// residualGraph is a copy of graph at the beginning
		for (int i = 0; i < TOTAL_NODES; i++)
			for (int z = 0; z < TOTAL_NODES; z++)
				residualGraph[i][z] = graph[i][z];

		int path[] = new int[TOTAL_NODES]; //used to store augmented path
		int maxFlow = 0;
		int node;

		while (BFS(residualGraph, s, t, path)) {
			int pathFlow = Integer.MAX_VALUE;
			for (int i = t; i != s; i = path[i]) {
				node = path[i];
				pathFlow = Math.min(pathFlow, residualGraph[node][i]);
			}

			//update residual graph with reverse edges along graph
			for (int i = t; i != s; i = path[i]) {
				node = path[i];
				residualGraph[node][i] -= pathFlow;
				residualGraph[i][node] += pathFlow;
			}
			maxFlow += pathFlow;
		}
		return maxFlow;
	}

	void augment(){}

	//https://favtutor.com/blogs/breadth-first-search-java
	//https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	public boolean BFS(int rGraph[][], int s, int t, int path[]) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);

		boolean visited[] = new boolean[TOTAL_NODES];
		for (int i = 0; i < TOTAL_NODES; i++) {
			visited[i] = false;
		}
		visited[s] = true;

		while (queue.size() != 0) {
			int node = queue.poll();
			for (int i = 0; i < TOTAL_NODES; i++) {
				// If the node has not been visited and there is an edge from node to index i
				if (!visited[i] && rGraph[node][i] > 0) {
					if (i == t) {
						path[i] = node;
						return true;
					}
					queue.add(i);
					path[i] = node;
					visited[i] = true;
				}
			}
		}
		return false;
	}


}
