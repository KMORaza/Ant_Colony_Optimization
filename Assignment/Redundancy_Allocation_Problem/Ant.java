package Redundancy_Allocation_Problem;
// Created: September 2022
import java.util.Random;
public class Ant {
    private Solution currentSolution;
    private Graph graph;
    private Random random;
    public Ant(Graph graph) {
        this.graph = graph;
        this.currentSolution = new Solution(graph.size());
        this.random = new Random();
    }
    public void constructSolution(PheromoneMatrix pheromones) {
        int numNodes = graph.size();
        boolean[] visitedNodes = new boolean[numNodes];
        int currentNode = random.nextInt(numNodes);
        visitedNodes[currentNode] = true;
        currentSolution.setRedundancyAssignment(currentNode, random.nextInt(2) + 1);
        for (int step = 1; step < numNodes; step++) {
            int nextNode = selectNextNode(currentNode, visitedNodes, pheromones.getPheromones(currentNode));
            visitedNodes[nextNode] = true;
            currentSolution.setRedundancyAssignment(nextNode, random.nextInt(2) + 1);
            currentNode = nextNode;
        }
    }
    private int selectNextNode(int currentNode, boolean[] visitedNodes, double[] pheromones) {
        double[] probabilities = calculateProbabilities(currentNode, visitedNodes, pheromones);
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (!visitedNodes[i] && randomValue <= cumulativeProbability) {
                return i;
            }
        }
        for (int i = 0; i < visitedNodes.length; i++) {
            if (!visitedNodes[i]) {
                return i;
            }
        }
        return -1;
    }
    private double[] calculateProbabilities(int currentNode, boolean[] visitedNodes, double[] pheromones) {
        double[] probabilities = new double[graph.size()];
        double totalProbability = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (!visitedNodes[i]) {
                probabilities[i] = pheromones[i] / (graph.getDistance(currentNode, i) + 1);
                totalProbability += probabilities[i];
            }
        }
        for (int i = 0; i < graph.size(); i++) {
            probabilities[i] /= totalProbability;
        }
        return probabilities;
    }
    public Solution getSolution() {
        return currentSolution;
    }
    public double getSolutionFitness() {
        int totalRedundancy = 0;
        for (int node = 0; node < graph.size(); node++) {
            totalRedundancy += currentSolution.getRedundancyAssignment(node);
        }
        return totalRedundancy;
    }
}