package Generalized_Assignment_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class AntColony {
    private int numAnts;
    private List<Ant> ants;
    private GeneralizedAssignment problem;
    private double[][] pheromones;
    private double decayFactor;
    private double alpha;
    private double beta;
    private Ant bestAnt;
    public AntColony(int numAnts, GeneralizedAssignment problem, double initialPheromone, double decayFactor, double alpha, double beta) {
        this.numAnts = numAnts;
        this.problem = problem;
        this.decayFactor = decayFactor;
        this.alpha = alpha;
        this.beta = beta;
        initializePheromones(initialPheromone);
        initializeAnts();
    }
    private void initializePheromones(double initialPheromone) {
        pheromones = new double[problem.getNumJobs()][problem.getNumAgents()];
        for (int i = 0; i < problem.getNumJobs(); i++) {
            for (int j = 0; j < problem.getNumAgents(); j++) {
                pheromones[i][j] = initialPheromone;
            }
        }
    }
    private void initializeAnts() {
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(problem.getNumJobs(), problem.getNumAgents(), pheromones, alpha, beta));
        }
    }
    public void runACO(int numIterations) {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            updateAnts();
            updatePheromones();
            updateBestAnt();
        }
    }
    private void updateAnts() {
        for (Ant ant : ants) {
            ant.performAntAssignment(problem);
        }
    }
    private void updatePheromones() {
        evaporatePheromones();
        depositPheromones();
    }
    private void evaporatePheromones() {
        for (int i = 0; i < problem.getNumJobs(); i++) {
            for (int j = 0; j < problem.getNumAgents(); j++) {
                pheromones[i][j] *= (1.0 - decayFactor);
            }
        }
    }
    private void depositPheromones() {
        for (Ant ant : ants) {
            List<Integer> assignment = ant.getAssignment();
            double pheromoneDeposit = 1.0 / calculateTotalCost(assignment);
            for (int i = 0; i < assignment.size(); i++) {
                int agent = assignment.get(i);
                if (agent != -1) {
                    pheromones[i][agent] += pheromoneDeposit;
                }
            }
        }
    }
    private void updateBestAnt() {
        for (Ant ant : ants) {
            if (bestAnt == null || getTotalCost(ant.getAssignment()) < getTotalCost(bestAnt.getAssignment())) {
                bestAnt = ant;
            }
        }
    }
    private double getTotalCost(List<Integer> assignment) {
        return 0.0;
    }
    public Ant getBestAnt() {
        return bestAnt;
    }
    private double calculateTotalCost(List<Integer> assignment) {
        double totalCost = 0.0;
        for (int i = 0; i < assignment.size(); i++) {
            int agent = assignment.get(i);
            if (agent != -1) {
                totalCost += problem.getCost(i, agent);
            }
        }
        return totalCost;
    }
}