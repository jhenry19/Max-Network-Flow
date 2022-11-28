public class Edge {
	int capacity;
	int flow;
	Node source;
	Node destination;

	Edge (Node s, Node d, int w) {
		source = s;
		destination = d;
		capacity = w;
		flow = 0;
	}

	Edge (Node s, Node d, int w, int f) {
		source = s;
		destination = d;
		capacity = w;
		flow = f;
	}

}
