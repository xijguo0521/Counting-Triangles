import java.util.*;

public class Graph {

    // Key: each vertex; value: a set of neighbors of the vertex
    private Map<Integer, Set<Integer>> vertices;
    private Set<Set<Integer>> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashSet<>();
    }

    public void addEdge(int u, int v) {
        if (vertices.containsKey(u))
            vertices.get(u).add(v);
        else {
            Set<Integer> s1 = new HashSet<>();
            s1.add(v);
            vertices.put(u, s1);
        }

        if (vertices.containsKey(v))
            vertices.get(v).add(u);
        else {
            Set<Integer> s2 = new HashSet<>();
            s2.add(u);
            vertices.put(v, s2);
        }

        Set<Integer> edge = new HashSet<>();
        edge.add(u);
        edge.add(v);

        edges.add(edge);
    }

    public int getNumNewTrianglesByEdge(int u, int v) {
        if (! vertices.containsKey(u) || ! vertices.containsKey(v))
            return 0;

        Set<Integer> UVCommonNeighbors = new HashSet<>();
        UVCommonNeighbors.addAll(vertices.get(u));
        UVCommonNeighbors.retainAll(vertices.get(v));

        return UVCommonNeighbors.size();
    }

    public int getNumEdges() {
        return edges.size();
    }

    public Set<Set<Integer>> getEdges() {
        return edges;
    }

    public Integer[] removeRandomEdge(int i) {
        List<Set<Integer>> l = new ArrayList<>(edges);
        Set<Integer> edge = l.remove(i);
        Integer[] rme = edge.toArray(new Integer[2]);

        edges.remove(edge);
        vertices.get(rme[0]).remove(rme[1]);
        vertices.get(rme[1]).remove(rme[0]);

        return rme;
    }

}
