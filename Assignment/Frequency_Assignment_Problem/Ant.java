package Frequency_Assignment_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Ant {
    private double alpha;
    private double beta;
    private double[][] heuristicValues;
    private double[][] pheromones;
    private List<Integer> visitedNodes;
    private int numNodes;
    public Ant(double alpha, double beta, double[][] heuristicValues, double[][] pheromones) {
        this.alpha = alpha;
        this.beta = beta;
        this.heuristicValues = heuristicValues;
        this.pheromones = pheromones;
        this.numNodes = heuristicValues.length;
        this.visitedNodes = new ArrayList<>();
    }
    public double[][] getPheromones() {
        double[][] antPheromones = new double[numNodes][numNodes];
        for (int i = 0; i < visitedNodes.size() - 1; i++) {
            int from = visitedNodes.get(i);
            int to = visitedNodes.get(i + 1);
            antPheromones[from][to] = 1.0 / calculateTotalCost();
        }
        return antPheromones;
    }
    public List<Integer> getVisitedNodes() {
        return visitedNodes;
    }
    public void tour() {
        int startNode = 0;
        visitedNodes.add(startNode);
        while (visitedNodes.size() < numNodes) {
            int nextNode = selectNextNode();
            visitedNodes.add(nextNode);
        }
    }
    private int selectNextNode() {
        int currentNode = visitedNodes.get(visitedNodes.size() - 1);
        if (visitedNodes.size() == numNodes) {
            return visitedNodes.get(0);
        }
        double[] probabilities = calculateProbabilities(currentNode);
        if (Arrays.stream(probabilities).allMatch(p -> p == 0.0)) {
            return visitedNodes.get(0);
        }
        int nextNode = selectNodeByProbability(probabilities);
        while (visitedNodes.contains(nextNode)) {
            nextNode = selectNodeByProbability(probabilities);
        }
        return nextNode;
    }
    private double[] calculateProbabilities(int currentNode) {
        double[] probabilities = new double[numNodes];
        double total = 0.0;
        for (int i = 0; i < numNodes; i++) {
            if (!visitedNodes.contains(i)) {
                probabilities[i] = Math.pow(pheromones[currentNode][i], alpha)
                        * Math.pow(heuristicValues[currentNode][i], beta);
                total += probabilities[i];
            }
        }
        if (total == 0.0) {
            return probabilities;
        }
        for (int i = 0; i < numNodes; i++) {
            probabilities[i] /= total;
        }
        return probabilities;
    }
    private int selectNodeByProbability(double[] probabilities) {
        double rand = Math.random();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numNodes; i++) {
            cumulativeProbability += probabilities[i];
            if (rand <= cumulativeProbability) {
                return i;
            }
        }
        return -1;
    }
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (int i = 0; i < visitedNodes.size() - 1; i++) {
            int from = visitedNodes.get(i);
            int to = visitedNodes.get(i + 1);
            totalCost += heuristicValues[from][to];
        }
        return totalCost;
    }
}