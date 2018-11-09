import java.util.List;

/**
 * Basic interface for all {@link Graphs graphs}.
 */
public interface Graphs {

    /**
     * Adds vertex if this graph does not contains it
     * @param o especified value of the vertex to add
     */
    void addVertex(Object o);

    /**
     *Adds a directed edge between to vertexes with an assign cost
     * @param a start vertex reference
     * @param b end vertex reference
     * @param cost cost of edge
     * @throws java.util.NoSuchElementException if the graph does not contain any of the specified vertexes
     */
    void addEdge(int a, int b, int cost);

    /**
     * Removes vertex
     * @param v vertex to remove
     * @throws java.util.NoSuchElementException if the graph does not contain the specified vertex
     */
    void removeVertex(Object v);

    /**
     * Removes directed edge between to vertexes
     * @param a start vertex reference
     * @param b end vertex reference
     * @throws java.util.NoSuchElementException if graph does not contain the specified edge
     */
    void removeEdge(int a, int b);

    /**Return true if contained*/
    boolean containsVertex(Object v);

    /**Return true of contained*/
    boolean containsEdge(int a, int b);

    /**Returns a view of the set vertexes contained in this graph*/
    List<Object> getVertexes ();

    /**Returns number of edges cointained*/
    int edges();

    /**Returns number of vertexes contained*/
    int order();
}
