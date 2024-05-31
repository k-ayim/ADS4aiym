import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        String[] cities = {"Almaty", "Astana", "Shymkent", "Atyrau", "Kostanay", "Kyzylorda"};
        for (String city : cities) {
            graph.addVertex(city);
        }

        graph.addEdge("Almaty", "Astana", 2.0);
        graph.addEdge("Shymkent", "Atyrau", 3.0);
        graph.addEdge("Atyrau", "Astana", 1.0);
        graph.addEdge("Almaty", "Shymkent", 4.0);
        graph.addEdge("Shymkent", "Astana", 2.5);
        graph.addEdge("Astana", "Kostanay", 1.5);
        graph.addEdge("Shymkent", "Kyzylorda", 1.0);

        System.out.println("BFS from Almaty to Kyzylorda:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        bfs.search(graph.getVertex("Almaty"), graph.getVertex("Kyzylorda"));
        printPath(bfs.getPaths(), graph.getVertex("Almaty"), graph.getVertex("Kyzylorda"));

        System.out.println("\nDijkstra's from Almaty to Kyzylorda:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.search(graph.getVertex("Almaty"), graph.getVertex("Kyzylorda"));
        printPath(dijkstra.getPaths(), graph.getVertex("Almaty"), graph.getVertex("Kyzylorda"));
    }

    public static void printPath(Map<Vertex<String>, Vertex<String>> paths, Vertex<String> start, Vertex<String> target) {
        if (paths == null || start == null || target == null || !paths.containsKey(target)) {
            System.out.println("No path found or invalid input.");
            return;
        }

        List<String> path = new ArrayList<>();
        for (Vertex<String> at = target; at != null; at = paths.get(at)) {
            path.add(at.getData());
        }
        Collections.reverse(path);
        System.out.println("Path: " + String.join(" -> ", path));
    }
}
