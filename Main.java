import java.util.*;

public class Main {
    public static void main(String[] args) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");

        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v2, v3, 2);
        graph.addEdge(v3, v4, 3);
        graph.addEdge(v1, v3, 4);

        BiDirectionalBreadthFirstSearch<String> biDirectionalBFS = new BiDirectionalBreadthFirstSearch<>(graph);
        biDirectionalBFS.search(v1, v4);
        System.out.println("Bi-directional BFS Paths: " + biDirectionalBFS.getPaths());

        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.search(v1);
        System.out.println("Dijkstra Paths: " + getPathString(dijkstra.getPaths(), v1));
    }

    private static <V> String getPathString(Map<Vertex<V>, Vertex<V>> paths, Vertex<V> start) {
        Map<Vertex<V>, List<Vertex<V>>> pathStrings = new HashMap<>();
        for (Vertex<V> key : paths.keySet()) {
            List<Vertex<V>> path = new ArrayList<>();
            for (Vertex<V> at = key; at != null; at = paths.get(at)) {
                path.add(at);
            }
            Collections.reverse(path);
            pathStrings.put(key, path);
        }
        return pathStrings.toString();
    }
}
