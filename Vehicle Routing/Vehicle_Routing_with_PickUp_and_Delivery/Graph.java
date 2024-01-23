package Vehicle_Routing_with_PickUp_and_Delivery;
// Created: September 2022
import java.util.HashMap;
import java.util.Map;
public class Graph {
    private Map<Integer, Node> nodes;
    public Graph() {
        this.nodes = new HashMap<>();
    }
    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }
    public Node getNode(int id) {
        return nodes.get(id);
    }
    public Map<Integer, Node> getNodes() {
        return nodes;
    }
}