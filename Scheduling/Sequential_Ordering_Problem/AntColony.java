package Sequential_Ordering_Problem;
import java.util.ArrayList;
import java.util.List;
// Created: September 2022
public class AntColony {
    private Graph graph;
    private List<Ant> ants;
    private double evaporationRate;
    private double pheromoneDeposit;
    public AntColony(Graph graph, int numAnts, double evaporationRate, double pheromoneDeposit) {
        this.graph = graph;
        this.evaporationRate = evaporationRate;
        this.pheromoneDeposit = pheromoneDeposit;
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(i % graph.getNodes().size())); // Initial position is distributed among nodes
        }
    }
    public List<Ant> getAnts() {
        return ants;
    }
    public void simulateAnts() {
        for (Ant ant : ants) {
            ant.move(graph);
        }
        updatePheromones();
    }
    private void updatePheromones() {
        evaporatePheromones();
        for (Ant ant : ants) {
            List<Node> visitedNodes = ant.getVisitedNodes();
            for (int i = 0; i < visitedNodes.size() - 1; i++) {
                int current = visitedNodes.get(i).getNodeId();
                int next = visitedNodes.get(i + 1).getNodeId();
                double pheromoneDelta = pheromoneDeposit / graph.getPheromoneLevel(current, next);
                graph.updatePheromoneLevel(current, next, pheromoneDelta);
            }
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().size(); j++) {
                double pheromoneLevel = graph.getPheromoneLevel(i, j);
                pheromoneLevel *= (1 - evaporationRate);
                graph.updatePheromoneLevel(i, j, pheromoneLevel);
            }
        }
    }
}