package Redundancy_Allocation_Problem;
// Created: September 2022
public class RedundancyAllocation {
    private Graph graph;
    private PheromoneMatrix pheromoneMatrix;
    private int numAnts;
    private int numIterations;
    public RedundancyAllocation(Graph graph, int numAnts, int numIterations) {
        this.graph = graph;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.pheromoneMatrix = new PheromoneMatrix(graph.size());
    }
    public void solve() {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            Ant[] ants = initializeAnts();
            for (Ant ant : ants) {
                ant.constructSolution(pheromoneMatrix);
            }
            updatePheromones(ants);
            System.out.println("Iteration " + (iteration + 1) + ":");
            for (Ant ant : ants) {
                System.out.println("Ant solution: " + ant.getSolution());
            }
            System.out.println("Pheromones after iteration:");
            pheromoneMatrix.printPheromones();
        }
    }
    private Ant[] initializeAnts() {
        Ant[] ants = new Ant[numAnts];
        for (int i = 0; i < numAnts; i++) {
            ants[i] = new Ant(graph);
        }
        return ants;
    }
    private void updatePheromones(Ant[] ants) {
        double evaporationRate = 0.5;
        pheromoneMatrix.evaporatePheromones(evaporationRate);
        for (Ant ant : ants) {
            double pheromoneChange = 1.0 / ant.getSolutionFitness();
            pheromoneMatrix.updatePheromones(ant.getSolution(), pheromoneChange);
        }
    }
}