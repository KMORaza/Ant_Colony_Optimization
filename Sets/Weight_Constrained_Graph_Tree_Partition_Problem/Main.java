package Weight_Constrained_Graph_Tree_Partition_Problem;
// Created: September 2022
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode(1, 10);
        graph.addNode(2, 15);
        graph.addNode(3, 20);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        int numAnts = 5;
        AntColony antColony = new AntColony(graph, numAnts);
        antColony.solve();
        List<Node> bestTour = antColony.getGlobalBestTour();
        double bestScore = antColony.getGlobalBestScore();
        System.out.println("Best Tour: " + bestTour);
        System.out.println("Best Score: " + bestScore);
    }
}