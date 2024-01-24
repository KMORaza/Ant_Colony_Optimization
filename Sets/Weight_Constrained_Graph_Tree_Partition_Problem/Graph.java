package Weight_Constrained_Graph_Tree_Partition_Problem;
// Created: September 2022
import java.util.HashMap;
import java.util.Map;
public class Graph {
    private Map<Integer, Node> nodes;
    public Graph() {
        this.nodes = new HashMap<>();
    }
    public void addNode(int nodeId, int weight) {
        nodes.put(nodeId, new Node(nodeId, weight));
    }
    public void addEdge(int sourceNodeId, int targetNodeId) {
        Node sourceNode = nodes.get(sourceNodeId);
        Node targetNode = nodes.get(targetNodeId);
        sourceNode.addNeighbor(targetNode);
        targetNode.addNeighbor(sourceNode);
    }
    public Map<Integer, Node> getNodes() {
        return nodes;
    }
}