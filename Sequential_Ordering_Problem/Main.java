package Sequential_Ordering_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        int numNodes = 5;
        int numAnts = 10;
        double evaporationRate = 0.1;
        double pheromoneDeposit = 1.0;
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node(i));
        }
        Graph graph = new Graph(nodes);
        AntColony antColony = new AntColony(graph, numAnts, evaporationRate, pheromoneDeposit);
        int numIterations = 100;
        for (int iteration = 0; iteration < numIterations; iteration++) {
            antColony.simulateAnts();
        }
        printAntPaths(antColony.getAnts());
        printPheromoneLevels(graph);
    }
    private static void printAntPaths(List<Ant> ants) {
        System.out.println("Ant Paths:");
        for (int i = 0; i < ants.size(); i++) {
            Ant ant = ants.get(i);
            List<Node> path = ant.getVisitedNodes();
            System.out.print("ANT " + i + ": ");
            for (Node node : path) {
                System.out.print(node.getNodeId() + " ");
            }
            System.out.println();
        }
    }
    private static void printPheromoneLevels(Graph graph) {
        System.out.println("\nPheromone Levels:");
        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().size(); j++) {
                System.out.println("EDGE (" + i + " -> " + j + "): " + graph.getPheromoneLevel(i, j));
            }
        }
    }
}