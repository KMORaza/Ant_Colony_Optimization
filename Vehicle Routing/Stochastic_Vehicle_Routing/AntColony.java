package Stochastic_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class AntColony {
    private StochasticVRP problem;
    private int numAnts;
    private List<Ant> ants;
    private double[][] pheromoneMatrix;
    private double alpha;
    private double beta;
    private double rho;
    private List<Integer> bestTour;
    private double bestCost;
    public AntColony(StochasticVRP problem, int numAnts, double alpha, double beta, double rho) {
        this.problem = problem;
        this.numAnts = numAnts;
        this.ants = new ArrayList<>();
        this.pheromoneMatrix = new double[problem.getNumNodes()][problem.getNumNodes()];
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
        initializeAnts();
    }
    private void initializeAnts() {
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(problem));
        }
    }
    public void runACO(int numIterations) {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            for (Ant ant : ants) {
                moveAnt(ant);
            }
            updatePheromones();
            evaluateSolutions();
            resetAnts();
        }
    }
    private void moveAnt(Ant ant) {
        int currentNode = chooseNextNode(ant);
        ant.getTour().add(currentNode);
        ant.getVisitedNodes()[currentNode] = true;
    }
    private int chooseNextNode(Ant ant) {
        double[] probabilities = calculateProbabilities(ant);
        double randomValue = Math.random();
        double cumulativeProbability = 0;
        for (int i = 0; i < problem.getNumNodes(); i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability && !ant.getVisitedNodes()[i]) {
                return i;
            }
        }
        return getRandomUnvisitedNode(ant);
    }
    private double[] calculateProbabilities(Ant ant) {
        double[] probabilities = new double[problem.getNumNodes()];
        double totalProbability = 0;
        List<Integer> tour = ant.getTour();
        if (!tour.isEmpty()) {
            int lastIndex = tour.get(tour.size() - 1);
            for (int i = 0; i < problem.getNumNodes(); i++) {
                if (!ant.getVisitedNodes()[i]) {
                    double pheromone = Math.pow(pheromoneMatrix[lastIndex][i], alpha);
                    double heuristic = 1.0 / problem.getDistanceMatrix()[lastIndex].get(i);
                    probabilities[i] = pheromone * heuristic;
                    totalProbability += probabilities[i];
                }
            }
            if (totalProbability != 0) {
                for (int i = 0; i < problem.getNumNodes(); i++) {
                    probabilities[i] /= totalProbability;
                }
            } else {
                Arrays.fill(probabilities, 1.0 / problem.getNumNodes());
            }
        } else {
            Arrays.fill(probabilities, 1.0 / problem.getNumNodes());
        }
        return probabilities;
    }
    private int getRandomUnvisitedNode(Ant ant) {
        List<Integer> unvisitedNodes = new ArrayList<>();
        for (int i = 0; i < problem.getNumNodes(); i++) {
            if (!ant.getVisitedNodes()[i]) {
                unvisitedNodes.add(i);
            }
        }
        if (unvisitedNodes.isEmpty()) {
            return 0;
        } else {
            int randomIndex = new Random().nextInt(unvisitedNodes.size());
            return unvisitedNodes.get(randomIndex);
        }
    }
    private void updatePheromones() {
        for (int i = 0; i < problem.getNumNodes(); i++) {
            for (int j = 0; j < problem.getNumNodes(); j++) {
                pheromoneMatrix[i][j] *= (1 - rho);
            }
        }
        for (Ant ant : ants) {
            double pheromoneDeposit = 1.0 / ant.getTour().size();
            for (int i = 0; i < ant.getTour().size() - 1; i++) {
                int fromNode = ant.getTour().get(i);
                int toNode = ant.getTour().get(i + 1);
                pheromoneMatrix[fromNode][toNode] += pheromoneDeposit;
                pheromoneMatrix[toNode][fromNode] += pheromoneDeposit;
            }
        }
    }
    private void evaluateSolutions() {
        double bestCost = Double.MAX_VALUE;
        List<Integer> bestTour = null;
        for (Ant ant : ants) {
            List<Integer> tour = ant.getTour();
            double tourCost = calculateTourCost(tour);
            System.out.println("Ant Tour: " + tour + ", Cost: " + tourCost);
            if (tourCost < bestCost) {
                bestCost = tourCost;
                bestTour = new ArrayList<>(tour);
            }
        }
        System.out.println("Best Tour: " + bestTour + ", Best Cost: " + bestCost);
    }
    public List<Integer> getBestTour() {
        return bestTour;
    }
    public double getBestCost() {
        return bestCost;
    }
    private void updateBestSolution(Ant ant) {
        double tourCost = calculateTourCost(ant.getTour());
        if (bestTour == null || tourCost < bestCost) {
            bestTour = new ArrayList<>(ant.getTour());
            bestCost = tourCost;
        }
    }
    private double calculateTourCost(List<Integer> tour) {
        double tourCost = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int fromNode = tour.get(i);
            int toNode = tour.get(i + 1);
            tourCost += problem.getDistanceMatrix()[fromNode].get(toNode);
        }
        tourCost += problem.getDistanceMatrix()[tour.get(tour.size() - 1)].get(tour.get(0));
        return tourCost;
    }
    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }
    private void resetAnts() {
        for (Ant ant : ants) {
            ant.getTour().clear();
            Arrays.fill(ant.getVisitedNodes(), false);
        }
    }
}