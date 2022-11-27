import java.util.ArrayList;

public class Runner {
	public static void main(String[] args) {
		System.out.println("Hello");
		ArrayList<Node> graph = new ArrayList<Node>();
		// A node at 0 is created but never used. This is so that .get command will correspond to numbered nodes
		// S node is index 1 and t is index 8
		for (int i = 0; i <= 8; ++i) {
			graph.add(new Node(i));
		}
		// Add edges of the graph to the nodes
		graph.get(1).addEdge(graph.get(2), 10);
		graph.get(1).addEdge(graph.get(3), 5);
		graph.get(1).addEdge(graph.get(4), 15);

		graph.get(2).addEdge(graph.get(5), 9);
		graph.get(2).addEdge(graph.get(6), 15);
		graph.get(2).addEdge(graph.get(3), 4);

		graph.get(3).addEdge(graph.get(6), 8);
		graph.get(3).addEdge(graph.get(4), 4);

		graph.get(4).addEdge(graph.get(7), 30);

		graph.get(5).addEdge(graph.get(8), 10);
		graph.get(5).addEdge(graph.get(6), 15);

		graph.get(6).addEdge(graph.get(8), 10);
		graph.get(6).addEdge(graph.get(7), 15);

		graph.get(7).addEdge(graph.get(3), 6);
		graph.get(7).addEdge(graph.get(8), 10);
	}


}
