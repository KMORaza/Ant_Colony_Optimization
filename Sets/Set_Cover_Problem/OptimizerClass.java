package Set_Cover_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
public class OptimizerClass {
    private SetCoverProblem problem;
    private int numAnts;
    private double evaporationRate;
    private double alpha;
    private double beta;
    private double pheromoneInit;
    public OptimizerClass(SetCoverProblem problem, int numAnts, double evaporationRate, double alpha, double beta, double pheromoneInit) {
        this.problem = problem;
        this.numAnts = numAnts;
        this.evaporationRate = evaporationRate;
        this.alpha = alpha;
        this.beta = beta;
        this.pheromoneInit = pheromoneInit;
    }
    public Solution solve(int maxIterations) {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant());
        }
        double[][] pheromoneMatrix = new double[problem.getUniverseSize()][problem.getSets().size()];
        initializePheromone(pheromoneMatrix);
        Solution bestSolution = null;
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (Ant ant : ants) {
                constructSolution(ant, pheromoneMatrix);
            }
            updatePheromones(pheromoneMatrix, ants);
            Solution currentSolution = getBestSolution(ants);
            if (bestSolution == null || currentSolution.getSelectedSets().size() < bestSolution.getSelectedSets().size()) {
                bestSolution = currentSolution;
            }
            evaporatePheromones(pheromoneMatrix);
        }
        return bestSolution;
    }
    private void initializePheromone(double[][] pheromoneMatrix) {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] = pheromoneInit;
            }
        }
    }
    private void constructSolution(Ant ant, double[][] pheromoneMatrix) {
        Random random = new Random();
        Set<Integer> remainingElements = new HashSet<>(problem.getUniverseSize());
        for (int i = 0; i < problem.getUniverseSize(); i++) {
            remainingElements.add(i);
        }
        while (!remainingElements.isEmpty()) {
            int selectedElement = selectElement(ant, pheromoneMatrix, remainingElements);
            ant.visitElement(selectedElement);
            removeCoveredSets(selectedElement, remainingElements);
        }
    }
    private int selectElement(Ant ant, double[][] pheromoneMatrix, Set<Integer> remainingElements) {
        return -1;
    }
    private void removeCoveredSets(int selectedElement, Set<Integer> remainingElements) {
        for (Set<Integer> set : problem.getSets()) {
            if (set.contains(selectedElement)) {
                remainingElements.removeAll(set);
            }
        }
    }
    private Solution getBestSolution(List<Ant> ants) {
        Solution bestSolution = null;
        for (Ant ant : ants) {
            List<Set<Integer>> selectedSets = new ArrayList<>();
            for (Set<Integer> set : problem.getSets()) {
                if (ant.getVisitedElements().containsAll(set)) {
                    selectedSets.add(set);
                }
            }
            Solution currentSolution = new Solution(selectedSets);
            if (bestSolution == null || currentSolution.getSelectedSets().size() < bestSolution.getSelectedSets().size()) {
                bestSolution = currentSolution;
            }
        }
        return bestSolution;
    }
    private void updatePheromones(double[][] pheromoneMatrix, List<Ant> ants) {
        for (Ant ant : ants) {
            Set<Integer> visitedElements = ant.getVisitedElements();
            for (int i = 0; i < pheromoneMatrix.length; i++) {
                for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                    if (visitedElements.contains(i)) {
                        pheromoneMatrix[i][j] += 1.0 / ant.getVisitedElements().size();
                    }
                }
            }
        }
    }
    private void evaporatePheromones(double[][] pheromoneMatrix) {
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }
    }
}