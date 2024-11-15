import java.util.*;

public class TriestBase implements DataStreamAlgo {

	private double M;
	private int D;
	private double t;
	private Graph graph;
	private final double NUMERATOR;

	/*
     * Constructor.
     * The parameter samsize denotes the size of the sample, i.e., the number of
     * edges that the algorithm can store.
     */
	public TriestBase(int samsize) {
		M = (double) samsize;
		NUMERATOR = M * (M - 1) * (M - 2);
		t = 1.0;
		D = 0;
		graph = new Graph();

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

		if (t <= M) {
			graph.addEdge(u, v);
			D += graph.getNumNewTrianglesByEdge(u, v);
		}

		else {
			Random random = new Random();
			double bias = random.nextDouble();

			if (bias < M / t) {
				int numEdges = graph.getNumEdges();
				int i = random.nextInt(numEdges);
				Integer[] removedEdge = graph.removeRandomEdge(i);
				D -= graph.getNumNewTrianglesByEdge(removedEdge[0], removedEdge[1]);
				graph.addEdge(u, v);
				D += graph.getNumNewTrianglesByEdge(u, v);
			}
		}
		t++;
	}

	public int getEstimate() {
	    if (t <= M) {
	    	return D;
		}

	    double pit = NUMERATOR / (t * (t - 1) * (t - 2));
		double DD = (double) D;

		return (int) (DD / pit);
	}
}
