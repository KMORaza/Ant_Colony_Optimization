package Frequency_Assignment_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AntColony {
    private int numAnts;
    private int numIterations;
    private double decay;
    private double alpha;
    private double beta;
    private double[][] pheromones;
    private double[][] heuristicValues;
    public AntColony(int numAnts, int numIterations, double decay, double alpha, double beta,
                     double[][] heuristicValues) {
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.decay = decay;
        this.alpha = alpha;
        this.beta = beta;
        this.heuristicValues = heuristicValues;
        this.pheromones = initializePheromones(heuristicValues.length);
    }
    private double[][] initializePheromones(int numNodes) {
        double[][] pheromones = new double[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                pheromones[i][j] = 1.0;
            }
        }
        return pheromones;
    }
    public void run() {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            List<Ant> ants = createAnts();
            updatePheromones(ants);
            decayPheromones();
        }
    }
    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(alpha, beta, heuristicValues, pheromones));
        }
        return ants;
    }
    private void updatePheromones(List<Ant> ants) {
        for (Ant ant : ants) {
            double[][] antPheromones = ant.getPheromones();
            for (int i = 0; i < antPheromones.length; i++) {
                for (int j = 0; j < antPheromones[i].length; j++) {
                    pheromones[i][j] += antPheromones[i][j];
                }
            }
        }
        System.out.println("Updated Pheromones Matrix:");
        for (int i = 0; i < pheromones.length; i++) {
            System.out.println(Arrays.toString(pheromones[i]));
        }
    }
    private void decayPheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1.0 - decay);
            }
        }
    }
    public void printSolution() {
        double bestCost = Double.POSITIVE_INFINITY;
        List<Integer> bestTour = null;
        List<Ant> ants = createAnts();
        for (Ant ant : ants) {
            ant.tour();
            double totalCost = ant.calculateTotalCost();
            if (totalCost < bestCost) {
                bestCost = totalCost;
                bestTour = ant.getVisitedNodes();
            }
        }
        System.out.println("Best Tour: " + bestTour);
        System.out.println("Best Cost: " + bestCost);
    }
}