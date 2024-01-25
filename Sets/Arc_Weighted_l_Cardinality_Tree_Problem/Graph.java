package Arc_Weighted_l_Cardinality_Tree_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    public Graph(List<Node> nodes, List<Arc> arcs) {
        this.nodes = nodes;
        this.arcs = arcs;
    }
    public List<Node> getNodes() {
        return nodes;
    }
    public List<Arc> getArcs() {
        return arcs;
    }
    public Arc getArc(Node source, Node destination) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                return arc;
            }
        }
        return null;
    }
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(node)) {
                neighbors.add(arc.getDestination());
            } else if (arc.getDestination().equals(node)) {
                neighbors.add(arc.getSource());
            }
        }
        return neighbors;
    }
}