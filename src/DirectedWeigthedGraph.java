import java.util.*;

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
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (j!=i) costMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public DirectedWeigthedGraph(int capacity){
        this.capacity = capacity;
        this.vertexes = new Object[capacity];
        this.costMatrix = new int[capacity][capacity];
        this.nVertex = 0;
        this.nEdges = 0;
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (j!=i) costMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
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

            if (costMatrix[pos][i] != Integer.MAX_VALUE){
                costMatrix[pos][i] = Integer.MAX_VALUE;
                nEdges --;
            }
            if (costMatrix[i][pos] != Integer.MAX_VALUE){
                costMatrix[i][pos] = Integer.MAX_VALUE;
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

    private Object getVertex(int v){
        return vertexes[v];
    }

    private Object[] getAdy(Object v){
        int t = getVertexPosition(v);
        Object result[] = new Object[nVertex];
        for (int i = 0; i < nVertex; i++) {
            if (costMatrix[t][i] != 0) result[i] = getVertex(i);
            }
        return result;
    }

    public Iterator<Object> plainSearch(){
        return Arrays.asList(vertexes).iterator();
    }

    public Iterator<Object> DFS(Object starting_elem){
        List<Object> list = new ArrayList<>();
        int n = getVertexPosition(starting_elem);
        boolean[] visited = new boolean[nVertex];
        DFS(n,visited,list);
        return list.iterator();
    }

    private void DFS(int n, boolean[] visited, List<Object> list) {
        visited[n] = true;
        list.add(vertexes[n]);

        for (int i = 0; i < costMatrix[n].length; i++) {
            int j = costMatrix[n][i];
            if(j<Integer.MAX_VALUE){
                if(!visited[i]){
                    DFS(i,visited,list);
                }
            }
        }
    }

    public Iterator<Object> BFS(Object starting_elem){
        List<Object> list = new ArrayList<>();
        boolean visited[] = new boolean[nVertex];
        int sn = getVertexPosition(starting_elem);
        visited[sn] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(sn);
        while (queue.size()!=0){
            sn = queue.poll();
            list.add(vertexes[sn]);
            for (int i = 0; i < costMatrix[sn].length; i++) {
                int n = costMatrix[sn][i];
                if(n<Integer.MAX_VALUE){
                    if(!visited[i]){
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }
        return list.iterator();
    }

    // Devuelve un nuevo arreglo con el costo nuevo de los vertices desde el origen asignado

//    public int[] Dijkstra(int origen){
//            int flag[] = new int[nVertex], distance[] = new int[nVertex];
//            int i,minpos=1,k,c,minimum;
//
//            for(i=0;i<=nVertex-1;i++)
//            {
//                flag[i]=0;
//                distance[i]=costMatrix[origen][i];
//            }
//            c=1;
//            while(c<=nVertex)
//            {
//                minimum=99;
//                for(k=0;k<=nVertex;k++)
//                {
//                    if(distance[k]<minimum && flag[k]!=1)
//                    {
//                        minimum=distance[i];
//                        minpos=k;
//                    }
//                }
//                flag[minpos]=1;
//                c++;
//                for(k=0;k<=nVertex;k++)
//                {
//                    if(distance[minpos]+costMatrix[minpos][k] <  distance[k] && flag[k]!=1 )
//                        distance[k]=distance[minpos]+costMatrix[minpos][k];
//                }
//            }
//            return distance;
//        }
}
