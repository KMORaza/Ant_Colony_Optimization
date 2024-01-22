package Single_Machine_Total_Weighted_Tardiness_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AntColony {
    private List<Ant> ants;
    private List<Job> jobs;
    private double[][] pheromones;
    private double[][] visibility;
    private double alpha;
    private double beta;
    private double rho;
    public AntColony(List<Job> jobs, int numAnts, double alpha, double beta, double rho) {
        this.jobs = jobs;
        this.ants = new ArrayList<>();
        this.pheromones = new double[jobs.size()][jobs.size()];
        this.visibility = new double[jobs.size()][jobs.size()];
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(jobs.size()));
        }
    }
    private void initializePheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] = 1.0;
            }
        }
    }
    private void updatePheromones(List<Ant> ants) {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1.0 - rho);
            }
        }
        for (Ant ant : ants) {
            List<Job> tour = ant.getTour();
            double tourWeight = calculateTourWeight(tour);
            double pheromoneIncrement = 1.0 / tourWeight;
            for (int i = 0; i < tour.size() - 1; i++) {
                int from = tour.get(i).getId();
                int to = tour.get(i + 1).getId();
                pheromones[from][to] += pheromoneIncrement;
            }
        }
    }
    public double calculateTourWeight(List<Job> tour) {
        double weight = 0.0;
        double completionTime = 0.0;
        for (Job job : tour) {
            completionTime += job.getProcessingTime();
            weight += job.getWeight() * Math.max(0, completionTime - job.getProcessingTime());
        }
        return weight;
    }
    private void moveAnts() {
        for (Ant ant : ants) {
            int initialJobId = new Random().nextInt(jobs.size());
            ant.visitJob(jobs.get(initialJobId));
            while (ant.getTour().size() < jobs.size()) {
                int nextJobId = selectNextJob(ant);
                ant.visitJob(jobs.get(nextJobId));
            }
        }
    }
    private int selectNextJob(Ant ant) {
        int currentJobId = ant.getTour().get(ant.getTour().size() - 1).getId();
        double[] probabilities = calculateProbabilities(currentJobId, ant);
        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }
        return -1;
    }
    private double[] calculateProbabilities(int currentJobId, Ant ant) {
        double[] probabilities = new double[jobs.size()];
        double totalProbability = 0.0;
        for (int i = 0; i < jobs.size(); i++) {
            if (!ant.hasVisited(i)) {
                double pheromone = Math.pow(pheromones[currentJobId][i], alpha);
                double visibilityValue = Math.pow(1.0 / jobs.get(i).getWeight(), beta);
                probabilities[i] = pheromone * visibilityValue;
                totalProbability += probabilities[i];
            }
        }
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= totalProbability;
        }
        return probabilities;
    }
    public List<Job> solve(int numIterations) {
        initializePheromones();
        for (int iteration = 0; iteration < numIterations; iteration++) {
            moveAnts();
            updatePheromones(ants);
        }
        return getBestSolution();
    }
    private List<Job> getBestSolution() {
        Ant bestAnt = ants.get(0);
        for (Ant ant : ants) {
            if (calculateTourWeight(ant.getTour()) < calculateTourWeight(bestAnt.getTour())) {
                bestAnt = ant;
            }
        }
        return bestAnt.getTour();
    }
}