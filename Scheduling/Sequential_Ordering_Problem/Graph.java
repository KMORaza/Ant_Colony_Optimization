package Sequential_Ordering_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Graph {
    private List<Node> nodes;
    private double[][] pheromoneLevels;
    public Graph(List<Node> nodes) {
        this.nodes = nodes;
        initializePheromoneLevels();
    }
    private void initializePheromoneLevels() {
        int numNodes = nodes.size();
        pheromoneLevels = new double[numNodes][numNodes];
        double initialPheromone = 1.0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                pheromoneLevels[i][j] = initialPheromone;
            }
        }
    }
    public List<Node> getNodes() {
        return nodes;
    }
    public double getPheromoneLevel(int i, int j) {
        return pheromoneLevels[i][j];
    }
    public void updatePheromoneLevel(int i, int j, double delta) {
        pheromoneLevels[i][j] += delta;
    }
}