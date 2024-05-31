import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> paths = new HashMap<>();
    private WeightedGraph<V> graph;
    private boolean foundTarget = false;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public void search(Vertex<V> start, Vertex<V> target) {
        if (start.equals(target)) {
            foundTarget = true;
            paths.put(start, null);
            return;
        }

        Queue<Vertex<V>> queue = new LinkedList<>();
        start.setVisited(true);
        queue.add(start);
        paths.put(start, null);

        while (!queue.isEmpty() && !foundTarget) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    paths.put(neighbor, current);
                    queue.add(neighbor);
                    if (neighbor.equals(target)) {
                        foundTarget = true;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void search(Vertex<V> start) {
        search(start, null);
    }

    @Override
    public Map<Vertex<V>, Vertex<V>> getPaths() {
        return paths;
    }
}
