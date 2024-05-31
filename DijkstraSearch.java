import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distances = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> paths = new HashMap<>();
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public void search(Vertex<V> start) {
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        distances.put(start, 0.0);
        paths.put(start, null);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll();
            double currentDistance = distances.get(current);

            for (Edge<V> edge : graph.getEdges(current)) {
                Vertex<V> adjacent = edge.getDest();
                double weight = edge.getWeight();
                double distanceThroughU = currentDistance + weight;

                if (distanceThroughU < distances.getOrDefault(adjacent, Double.MAX_VALUE)) {
                    distances.put(adjacent, distanceThroughU);
                    paths.put(adjacent, current);
                    priorityQueue.add(adjacent);
                }
            }
        }
    }

    @Override
    public void search(Vertex<V> start, Vertex<V> target) {
        search(start);
    }

    @Override
    public Map<Vertex<V>, Vertex<V>> getPaths() {
        return paths;
    }
}
