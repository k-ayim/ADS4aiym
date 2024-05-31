import java.util.Map;

public interface Search<V> {
    void search(Vertex<V> start);
    void search(Vertex<V> start, Vertex<V> target);
    Map<Vertex<V>, Vertex<V>> getPaths();
}
