public class Edge {
	int weight;
	Node source;
	Node destination;

	Edge (Node s, Node d, int w) {
		source = s;
		destination = d;
		weight = w;
	}
}
