public class Node {

    Object elem;
    int cost;
    boolean visited;
    int index;

    public Node(Object elem, int cost, boolean visited, int index) {
        this.elem = elem;
        this.cost = cost;
        this.visited = visited;
        this.index = index;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Object getElem() {
        return elem;
    }

    public int getCost() {
        return cost;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getIndex() {
        return index;
    }
}
