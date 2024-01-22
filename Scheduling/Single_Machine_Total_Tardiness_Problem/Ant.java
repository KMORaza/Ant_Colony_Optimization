package Single_Machine_Total_Tardiness_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private List<Job> solution;
    private double[][] pheromones;
    private double alpha;
    private double beta;
    private Random random;
    public Ant(double[][] pheromones, double alpha, double beta) {
        this.solution = new ArrayList<>();
        this.pheromones = pheromones;
        this.alpha = alpha;
        this.beta = beta;
        this.random = new Random();
    }
    public List<Job> getSolution() {
        return solution;
    }
    public void constructSolution(List<Job> jobs) {
        int remainingJobs = jobs.size();
        List<Job> availableJobs = new ArrayList<>(jobs);
        while (remainingJobs > 0) {
            double[] probabilities = calculateProbabilities(availableJobs);
            int selectedJobIndex = selectJob(probabilities);
            Job selectedJob = availableJobs.get(selectedJobIndex);
            solution.add(selectedJob);
            availableJobs.remove(selectedJob);
            remainingJobs--;
        }
    }
    private double[] calculateProbabilities(List<Job> availableJobs) {
        double[] probabilities = new double[availableJobs.size()];
        double totalProbability = 0.0;
        for (int i = 0; i < availableJobs.size(); i++) {
            Job job = availableJobs.get(i);
            if (job.getId() >= 0 && job.getId() < pheromones.length) {
                double pheromone = Math.pow(pheromones[job.getId()][i], alpha);
                double heuristic = 1.0 / job.getDueDate();
                probabilities[i] = pheromone * heuristic;
                totalProbability += probabilities[i];
            } else {
                System.err.println("Invalid job ID: " + job.getId());
            }
        }
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= totalProbability;
        }
        return probabilities;
    }
    private int selectJob(double[] probabilities) {
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }
        return probabilities.length - 1;
    }
}