import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        else throw new IllegalStateException(); //Habria que agrandar los arreglos
    }

    @Override
    public void addEdge(int start, int end, int cost) {
        if (!containsEdge(start, end)) {
            costMatrix[start][end] = cost; //Directed graph -> so it only adds in one combination of a and b
            nEdges++;
        }
        else throw new NoSuchElementException();
    }

    @Override
    public void removeVertex(Object v) {
        int pos = getVertexPosition(v);
        if (pos == -1)
            throw new NoSuchElementException();
        //Erase every track the vertex was involved in
        for (int i = 0; i < costMatrix.length; i++) {

            if (costMatrix[pos][i] != 0){
                costMatrix[pos][i] = 0;
                nEdges --;
            }
            if (costMatrix[i][pos] != 0){
                costMatrix[i][pos] = 0;
                nEdges --;
            }
        }
        nVertex --;

    }

    private int getVertexPosition(Object v) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i].equals(v))
                return i;
        }
        return -1;
    }

    @Override
    public void removeEdge(int start, int end) {
        if (!containsEdge(start, end))
            throw new NoSuchElementException();
        costMatrix[start][end] = 0;
    }

    @Override
    public boolean containsVertex(Object v) {
        return getVertexPosition(v) != -1;
    }

    @Override
    public boolean containsEdge(int a, int b) {
        if (vertexes[a] == null|| vertexes[b] == null)
            throw new IllegalArgumentException();
        return costMatrix[a][b] != 0;
    }

    @Override
    public List<Object> getVertexes() {
        return Arrays.asList(vertexes);
    }

    @Override
    public int edges() {
        return nEdges;
    }

    @Override
    public int order() {
        return nVertex;
    }

}
