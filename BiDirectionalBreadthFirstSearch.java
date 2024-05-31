import java.util.*;

public class BiDirectionalBreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> paths = new HashMap<>();
    private Vertex<V> meetingPoint = null;
    private WeightedGraph<V> graph;

    public BiDirectionalBreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public void search(Vertex<V> start) {
        throw new UnsupportedOperationException("Single-source search is not supported in BiDirectionalBreadthFirstSearch.");
    }

    @Override
    public void search(Vertex<V> start, Vertex<V> target) {
        paths.clear();
        meetingPoint = null;

        if (start.equals(target)) {
            paths.put(start, null);
            return;
        }

        Queue<Vertex<V>> queueFromStart = new LinkedList<>();
        Queue<Vertex<V>> queueFromTarget = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> parentsFromStart = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentsFromTarget = new HashMap<>();

        queueFromStart.add(start);
        queueFromTarget.add(target);
        parentsFromStart.put(start, null);
        parentsFromTarget.put(target, null);

        while (!queueFromStart.isEmpty() && !queueFromTarget.isEmpty()) {
            if (processQueue(queueFromStart, parentsFromStart, parentsFromTarget)) {
                break;
            }
            if (processQueue(queueFromTarget, parentsFromTarget, parentsFromStart)) {
                break;
            }
        }

        if (meetingPoint != null) {
            constructPaths(parentsFromStart, parentsFromTarget, start, target);
        } else {
            System.out.println("No meeting point found. The target is not reachable from the start.");
        }
    }

    private boolean processQueue(Queue<Vertex<V>> queue, Map<Vertex<V>, Vertex<V>> thisParents, Map<Vertex<V>, Vertex<V>> otherParents) {
        if (queue.isEmpty()) return false;

        Vertex<V> current = queue.poll();
        for (Map.Entry<Vertex<V>, Double> entry : graph.getAdjacentVertices(current).entrySet()) {
            Vertex<V> neighbor = entry.getKey();
            if (!thisParents.containsKey(neighbor)) {
                thisParents.put(neighbor, current);
                queue.add(neighbor);
                if (otherParents.containsKey(neighbor)) {
                    meetingPoint = neighbor;
                    return true;
                }
            }
        }
        return false;
    }

    private void constructPaths(Map<Vertex<V>, Vertex<V>> parentsFromStart,
                                Map<Vertex<V>, Vertex<V>> parentsFromTarget, Vertex<V> start, Vertex<V> target) {
        List<Vertex<V>> path = new LinkedList<>();
        Vertex<V> node = meetingPoint;

        while (node != null) {
            path.add(0, node);
            node = parentsFromStart.get(node);
        }

        node = parentsFromTarget.get(meetingPoint);
        while (node != null) {
            path.add(node);
            node = parentsFromTarget.get(node);
        }

        for (int i = 0; i < path.size() - 1; i++) {
            paths.put(path.get(i), path.get(i + 1));
        }
        paths.put(start, path.get(0));
        paths.put(target, path.get(path.size() - 1));
    }

    @Override
    public Map<Vertex<V>, Vertex<V>> getPaths() {
        return paths;
    }
}
