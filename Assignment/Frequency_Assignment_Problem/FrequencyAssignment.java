package Frequency_Assignment_Problem;
// Created: September 2022
public class FrequencyAssignment {
    public static void main(String[] args) {
        int numNodes = 5;
        double[][] heuristicValues = new double[numNodes][numNodes];
        int numAnts = 10;
        int numIterations = 100;
        double decay = 0.1;
        double alpha = 1.0;
        double beta = 2.0;
        AntColony antColony = new AntColony(numAnts, numIterations, decay, alpha, beta, heuristicValues);
        antColony.run();
        antColony.printSolution();
    }
}