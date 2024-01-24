package Generalized_Assignment_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private List<Integer> assignment;
    private double[][] pheromones;
    private double alpha;
    private double beta;
    private Random random;
    public Ant(int numJobs, int numAgents, double[][] pheromones, double alpha, double beta) {
        this.assignment = new ArrayList<>();
        this.pheromones = pheromones;
        this.alpha = alpha;
        this.beta = beta;
        this.random = new Random();
        initializeAssignment(numJobs);
    }
    private void initializeAssignment(int numJobs) {
        for (int i = 0; i < numJobs; i++) {
            assignment.add(-1);
        }
    }
    public void performAntAssignment(GeneralizedAssignment problem) {
        for (int i = 0; i < assignment.size(); i++) {
            int selectedAgent = selectAgent(problem, i);
            assignment.set(i, selectedAgent);
        }
    }
    public List<Integer> getAssignment() {
        return assignment;
    }
    private int selectAgent(GeneralizedAssignment problem, int job) {
        double[] probabilities = calculateProbabilities(problem, job);
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int agent = 0; agent < problem.getNumAgents(); agent++) {
            cumulativeProbability += probabilities[agent];
            if (randomValue <= cumulativeProbability) {
                return agent;
            }
        }
        return problem.getNumAgents() - 1;
    }
    private double[] calculateProbabilities(GeneralizedAssignment problem, int job) {
        double[] probabilities = new double[problem.getNumAgents()];
        double totalProbability = 0.0;
        for (int agent = 0; agent < problem.getNumAgents(); agent++) {
            if (!isJobAssigned(job, agent)) {
                probabilities[agent] = calculateProbability(problem, job, agent);
                totalProbability += probabilities[agent];
            }
        }
        if (totalProbability > 0.0) {
            for (int agent = 0; agent < problem.getNumAgents(); agent++) {
                probabilities[agent] /= totalProbability;
            }
        }
        return probabilities;
    }
    private boolean isJobAssigned(int job, int agent) {
        return assignment.contains(agent) && assignment.indexOf(agent) < job;
    }
    private double calculateProbability(GeneralizedAssignment problem, int job, int agent) {
        double pheromoneFactor = Math.pow(pheromones[job][agent], alpha);
        double heuristicFactor = Math.pow(1.0 / problem.getCost(job, agent), beta);
        return pheromoneFactor * heuristicFactor;
    }
}