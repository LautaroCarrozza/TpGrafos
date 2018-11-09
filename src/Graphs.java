public interface Graphs {
    void addVertex(Object o);
    void addEdge(int a, int b, int cost);
    void removeVertex(Object v);
    void removeEdge(int a, int b);
    boolean containsVertex(Object v);
    boolean containsEdge(int a, int b);
    int order();
}
