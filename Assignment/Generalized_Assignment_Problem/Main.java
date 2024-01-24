package Generalized_Assignment_Problem;
// Created: September 2022
import java.util.List;
public class Main {
    public static void main(String[] args) {
        int numJobs = 5;
        int numAgents = 3;
        double[][] costMatrix = {
                {5.0, 8.0, 6.0},
                {4.0, 2.0, 7.0},
                {9.0, 3.0, 1.0},
                {2.0, 6.0, 4.0},
                {8.0, 7.0, 3.0}
        };
        GeneralizedAssignment problem = new GeneralizedAssignment(numJobs, numAgents, costMatrix);
        int numAnts = 10;
        double initialPheromone = 1.0;
        double decayFactor = 0.1;
        double alpha = 1.0;
        double beta = 2.0;
        AntColony antColony = new AntColony(numAnts, problem, initialPheromone, decayFactor, alpha, beta);
        int numIterations = 100;
        antColony.runACO(numIterations);
        List<Integer> bestSolution = antColony.getBestAnt().getAssignment();
        System.out.println("Best Solution = " + bestSolution);
    }
}