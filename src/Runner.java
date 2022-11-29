import java.util.ArrayList;

public class Runner {
	public static void main(String[] args) {
		int[][] graph = new int[][] {
				{0,10,5,15,0,0,0,0},
				{0,0,4,0,9,15,0,0},
				{0,0,0,4,0,8,0,0},
				{0,0,0,0,0,0,30,0},
				{0,0,0,0,0,16,0,10},
				{0,0,0,0,0,0,10,15},
				{0,0,6,0,0,0,0,10},
				{0,0,0,0,0,0,0,0}
		};
		// Index 0 and 7 are the source and sink node respectably
		MaxFlow m = new MaxFlow(graph,0,7);
		System.out.println("Maximum Flow: " + m.MaximumFlow());
	}
}
