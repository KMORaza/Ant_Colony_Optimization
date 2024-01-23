package Split_Delivery_Vehicle_Routing;
// Created: September 2022
public class Graph {
    private Node[] nodes;
    private double[][] distances;
    public Graph(Node[] nodes) {
        this.nodes = nodes;
        calculateDistances();
    }
    public Node[] getNodes() {
        return nodes;
    }
    private void calculateDistances() {
        int numNodes = nodes.length;
        distances = new double[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                distances[i][j] = calculateDistance(nodes[i], nodes[j]);
            }
        }
    }
    private double calculateDistance(Node node1, Node node2) {
        double deltaX = node1.getX() - node2.getX();
        double deltaY = node1.getY() - node2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    public double getDistance(int node1, int node2) {
        return distances[node1][node2];
    }
}