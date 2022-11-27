import java.util.ArrayList;

public class Node {
	int index;
	ArrayList<Edge> outgoing;
	ArrayList<Edge> incoming;

	Node(int index){
		this.index = index;
	}

	/**
	 * Add edge to this nodes incoming nodes and the destinations nodes outgoing nodes
	 */
	void addEdge(Node destination, int weight) {
		Edge newEdge = new Edge(this, destination, weight);
		this.outgoing.add(newEdge);
		destination.incoming.add(newEdge);
	}
}
