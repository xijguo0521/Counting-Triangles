import java.util.*;

public class TriestImpr implements DataStreamAlgo {

	private double D;
	private double M;
	private double t;
	private double denominator;
	private Graph graph;

    /*
     * Constructor.
     * The parameter samsize denotes the size of the sample, i.e., the number of
     * edges that the algorithm can store.
     */
	public TriestImpr(int samsize) {
		M = (double) samsize;
		denominator = M * (M - 1);
		graph = new Graph();
		D = 0.0;
		t = 1.0;
	}

	public void handleEdge(Edge edge) {
		int u = edge.u;
		int v = edge.v;

		if (u == v)
			return;

		Set<Integer> newEdge = new HashSet<>();
		newEdge.add(u);
		newEdge.add(v);

		if (graph.getEdges().contains(newEdge))
			return;

		int n = graph.getNumNewTrianglesByEdge(u, v);
		double eta = ((t - 1) * (t - 2)) / denominator;
		for (int i = 0; i < n; i++)
			D += eta;


		if (t <= M) {
			graph.addEdge(u, v);
		}

		else {
			Random random = new Random();
			double bias = random.nextDouble();
			if (bias < M / t) {
				int numEdges = graph.getNumEdges();
				int i = random.nextInt(numEdges);
				graph.removeRandomEdge(i);
				graph.addEdge(u, v);
			}
		}
		t++;
	}


	public int getEstimate() {
		return (int) D;
	}
}
