package Weight_Constrained_Graph_Tree_Partition_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private Graph graph;
    private List<Node> tour;
    private boolean[] visited;
    public Ant(Graph graph) {
        this.graph = graph;
        this.tour = new ArrayList<>();
        this.visited = new boolean[graph.getNodes().size()];
        initialize();
    }
    private void initialize() {
        int startNode = new Random().nextInt(graph.getNodes().size());
        tour.add(new ArrayList<>(graph.getNodes().values()).get(startNode));
        visited[startNode] = true;
    }
    public void move() {
        System.out.println("Moving...");
        while (tour.size() < graph.getNodes().size()) {
            Node currentNode = tour.get(tour.size() - 1);
            Node nextNode = selectNextNode(currentNode);
            if (nextNode != null) {
                System.out.println("Moving to node: " + nextNode.getNodeId());
                tour.add(nextNode);
                visited[nextNode.getNodeId()] = true;
            } else {
                System.out.println("No unvisited neighbors found.");
                break;
            }
        }
    }
    private Node selectNextNode(Node currentNode) {
        List<Node> unvisitedNeighbors = getUnvisitedNeighbors(currentNode);
        if (unvisitedNeighbors.isEmpty()) {
            System.out.println("All neighbors are visited.");
            return null;
        }
        double totalProbability = 0.0;
        double[] probabilities = new double[unvisitedNeighbors.size()];
        for (int i = 0; i < unvisitedNeighbors.size(); i++) {
            Node neighbor = unvisitedNeighbors.get(i);
            double pheromone = currentNode.getPheromone(neighbor);
            double distance = currentNode.getDistanceTo(neighbor);
            probabilities[i] = pheromone / distance;
            totalProbability += probabilities[i];
        }
        double randomValue = Math.random() * totalProbability;
        double cumulativeProbability = 0.0;
        System.out.println("Random Value: " + randomValue);
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            System.out.println("Cumulative Probability: " + cumulativeProbability);
            if (cumulativeProbability >= randomValue) {
                return unvisitedNeighbors.get(i);
            }
        }
        return null;
    }
    private List<Node> getUnvisitedNeighbors(Node node) {
        List<Node> unvisitedNeighbors = new ArrayList<>();
        System.out.println("Visited array size: " + visited.length);
        for (Node neighbor : node.getNeighbors()) {
            int neighborId = neighbor.getNodeId();
            System.out.println("Checking neighbor with ID: " + neighborId);
            if (neighborId >= 0 && neighborId < visited.length && !visited[neighborId]) {
                unvisitedNeighbors.add(neighbor);
            } else {
                System.out.println("Neighbor " + neighborId + " is visited or out of bounds.");
            }
        }
        return unvisitedNeighbors;
    }
    public List<Node> getTour() {
        return tour;
    }
    public void reset() {
        tour.clear();
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        initialize();
    }
}