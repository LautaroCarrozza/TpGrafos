import java.util.NoSuchElementException;

/**
 * Directed Weighted Graph implemented with matrix cost
 *
 */

public class DirectedWeigthedGraph implements Graphs {
    private Object [] vertexes;
    private int [][] costMatrix;
    private int capacity;
    private int nVertex;
    private int nEdges;

    //Default constructor, capacity = 10
    public DirectedWeigthedGraph() {
        this.capacity = 10;
        this.vertexes = new Object[10];
        this.costMatrix = new int[10][10];
        this.nVertex = 0;
        this.nEdges = 0;
    }

    public DirectedWeigthedGraph(int capacity){
        this.capacity = capacity;
        this.vertexes = new Object[capacity];
        this.costMatrix = new int[capacity][capacity];
        this.nVertex = 0;
        this.nEdges = 0;
    }


    @Override
    public void addVertex(Object vertex) {
        if (nVertex < capacity) {
            vertexes[nVertex] = vertex;
            nVertex++;
        }
        else throw new IllegalStateException();
    }

    @Override
    public void addEdge(int a, int b, int cost) {
        if (!containsEdge(a, b))
            costMatrix[a][b] = costMatrix[b][a] = cost;
        else throw new NoSuchElementException();
    }

    @Override
    public void removeVertex(Object v) {
        if (!containsVertex(v))
            throw new NoSuchElementException();


    }

    @Override
    public void removeEdge() {

    }

    @Override
    public boolean containsVertex(Object v) {
        for (Object vertex : vertexes) {
            if (v == vertex)
                return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(int a, int b) {
        if (vertexes[a] == null|| vertexes[b] == null)
            throw new IllegalArgumentException();
        return costMatrix[a][b] != 0;
    }

    @Override
    public int order() {
        return 0;
    }

}
