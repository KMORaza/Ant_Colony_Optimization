package Arc_Weighted_l_Cardinality_Tree_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        List<Arc> arcs = new ArrayList<>();
        Graph graph = new Graph(nodes, arcs);
        int numAnts = 10;
        AntColony antColony = new AntColony(numAnts, graph);
        int numIterations = 100;
        antColony.run(numIterations);
        printResults(antColony);
    }
    private static void printResults(AntColony antColony) {
        for (int i = 0; i < antColony.getNumAnts(); i++) {
            Ant ant = antColony.getAnts().get(i);
            List<Node> tour = ant.getTour();
            System.out.println("Ant Tour " + i + ": " + tour);
            double tourLength = antColony.calculateTourLength(tour);
            System.out.println("Ant " + i + " Tour Length: " + tourLength);
        }
    }
}