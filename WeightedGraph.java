import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Set<Vertex<V>> getVertices() {
        return new HashSet<>(vertices.values());
    }


    public void addEdge(V fromData, V toData, double weight) {
        Vertex<V> fromVertex = vertices.get(fromData);
        Vertex<V> toVertex = vertices.get(toData);

        if (fromVertex == null || toVertex == null) {
            throw new IllegalArgumentException("One or both vertices not found");
        }

        fromVertex.addAdjacentVertex(toVertex, weight);
        toVertex.addAdjacentVertex(fromVertex, weight); // Assuming the graph is undirected
    }
}
