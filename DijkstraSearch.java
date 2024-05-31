import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distances = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> previous = new HashMap<>();
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public void search(Vertex<V> start) {
        Queue<Vertex<V>> currentQueue = new LinkedList<>();
        Queue<Vertex<V>> nextQueue = new LinkedList<>();

        distances.put(start, 0.0);
        currentQueue.add(start);
        previous.put(start, null);

        while (!currentQueue.isEmpty()) {
            Vertex<V> current = currentQueue.poll();
            double currentDistance = distances.get(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDistance = currentDistance + weight;

                if (newDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    nextQueue.add(neighbor);
                }
            }

            if (currentQueue.isEmpty()) {
                Queue<Vertex<V>> temp = currentQueue;
                currentQueue = nextQueue;
                nextQueue = temp;
            }
        }
    }

    @Override
    public void search(Vertex<V> start, Vertex<V> target) {
        search(start);
    }

    @Override
    public Map<Vertex<V>, Vertex<V>> getPaths() {
        return previous;
    }
}
