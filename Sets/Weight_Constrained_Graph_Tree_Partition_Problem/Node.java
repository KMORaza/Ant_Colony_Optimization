package Weight_Constrained_Graph_Tree_Partition_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Node {
    private int nodeId;
    private int weight;
    private Map<Integer, Double> pheromones;
    private List<Node> neighbors;
    public Node(int nodeId, int weight) {
        this.nodeId = nodeId;
        this.weight = weight;
        this.pheromones = new HashMap<>();
        this.neighbors = new ArrayList<>();
    }
    public int getNodeId() {
        return nodeId;
    }
    public int getWeight() {
        return weight;
    }
    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
    public List<Node> getNeighbors() {
        return neighbors;
    }
    public double getPheromone(Node neighbor) {
        return pheromones.getOrDefault(neighbor.getNodeId(), 0.0);
    }
    public void setPheromone(Node neighbor, double pheromone) {
        pheromones.put(neighbor.getNodeId(), pheromone);
    }
    public double getDistanceTo(Node neighbor) {
        return Math.abs(weight - neighbor.getWeight());
    }
}