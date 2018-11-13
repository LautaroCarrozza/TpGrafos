import java.util.*;

public class Graph2 {

    private int vertexQuantity;
    private Map<Node, Set<Edge>> vertexMap;

    Graph2() {
        vertexQuantity = 0;
        vertexMap = new HashMap<>();
    }

    public void addEdge(Node source, Node destination, double distance) {
        if (vertexMap.containsKey(source)) {
            Edge map_edge = new Edge(source, destination, distance);
            vertexMap.get(source).add(map_edge);
            source.addEdge(map_edge);
        } else {
            System.out.println("Source vertex not found..");
        }
    }

    public void addVertex(Node V) {
        if (!vertexMap.containsKey(V)) {
            vertexMap.put(V, new HashSet<Edge>());
            System.out.println("Vertex Added");
            ++vertexQuantity;
        } else
            System.out.println("Vertex already added");
    }

    ArrayList<Node> getNeighbor(Node vertex) {
        return new ArrayList<Node>(vertex.getNeighbors());
    }

    // to get length between vertexes directly connected to each other
    double getDistance(Node source, Node destination){

        for(Edge edge : vertexMap.get(source)){
            if(edge.getDestination() == destination)
                return edge.getLength();
        }
        return 0;
    }

    // No of vertexMap in graph
    int numVertices() {
        return vertexQuantity;
    }

    void bfs(Node source) {
        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> visited = new HashSet<Node>();
        ArrayList<String> path = new ArrayList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node);
            Set<Node> neighbor = node.getNeighbors();
            //    System.out.print("Node " +node.getName()+ "\t ");
            for (Node V : neighbor) {
                //   System.out.print(V.getName()+" \t");
                if (!visited.contains(V)) {
                    queue.add(V);
                    visited.add(V);
                    path.add(V.getName());
                }
            }
            //   System.out.println("");
        }
        System.out.println("BFS \t" + path.toString());
    }

    private class Node {

        private Set<Edge> edges;
        private String name;

        public Node(String name) {
            this.name = name;
            edges = new HashSet<>();
        }

        void addEdge(Edge edge) {
            edges.add(edge);
        }

        String getName() {
            return this.name;
        }

        Set<Node> getNeighbors() {
            Set<Node> neighbor = new HashSet<Node>();
            for (Edge edge : edges) {
                neighbor.add(edge.getDestination());
            }
            return neighbor;
        }

        Set<Edge> getEdges() {
            return this.edges;
        }
    }

    private class Edge {
        private Node source, destination;
        private double length;

        public Edge(Node source, Node destination, double distance) {
            this.source = source;
            this.destination = destination;
            this.length = distance;
        }

        Node getSource() {
            return this.source;
        }

        Node getDestination() {
            return this.destination;
        }

        double getLength() {
            return this.length;
        }
    }
}
