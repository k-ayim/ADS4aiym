import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<V>>> adjacencyList;

    public WeightedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }
    public Map<Vertex<V>, Double> getAdjacentVertices(Vertex<V> vertex) {
        Map<Vertex<V>, Double> map = new HashMap<>();
        if (adjacencyList.containsKey(vertex)) {
            for (Edge<V> edge : adjacencyList.get(vertex)) {
                map.put(edge.getDest(), edge.getWeight());
            }
        }
        return map;
    }
    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        adjacencyList.get(source).add(new Edge<>(source, dest, weight));
        source.addAdjacentVertex(dest, weight);
        adjacencyList.get(dest).add(new Edge<>(dest, source, weight));
        dest.addAdjacentVertex(source, weight);
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Vertex<V>> getVertices() {
        return adjacencyList.keySet();
    }
}
