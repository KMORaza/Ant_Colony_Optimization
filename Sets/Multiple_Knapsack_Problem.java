// Multiple Knapsack Problem using Ant Colony Optimization
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class Item {
    int weight;
    int value;
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
class Ant {
    List<Integer> selectedItems;
    public Ant() {
        this.selectedItems = new ArrayList<>();
    }
}
class KnapsackProblem {
    int numberOfAnts;
    int numberOfItems;
    List<Item> items;
    double[][] pheromoneMatrix;
    Ant[] ants;
    int maxWeight;
    double alpha;
    double beta;
    double evaporationRate;
    double localSearchProbability;
    Random random;
    public KnapsackProblem(int numberOfAnts, int numberOfItems, List<Item> items, int maxWeight,
                           double alpha, double beta, double evaporationRate, double localSearchProbability) {
        this.numberOfAnts = numberOfAnts;
        this.numberOfItems = numberOfItems;
        this.items = items;
        this.maxWeight = maxWeight;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.localSearchProbability = localSearchProbability;
        this.random = new Random();
        pheromoneMatrix = new double[numberOfItems][numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            for (int j = 0; j < numberOfItems; j++) {
                pheromoneMatrix[i][j] = 1.0;
            }
        }
        ants = new Ant[numberOfAnts];
        for (int i = 0; i < numberOfAnts; i++) {
            ants[i] = new Ant();
        }
    }
    public void runACO(int iterations) {
        for (int iteration = 0; iteration < iterations; iteration++) {
            for (Ant ant : ants) {
                selectItems(ant);
                if (random.nextDouble() < localSearchProbability) {
                    localSearch(ant);
                }
            }
            updatePheromones();
            Ant bestAnt = findBestAnt();
            System.out.println("Iteration " + (iteration + 1) + ": Best solution - " + bestAnt.selectedItems +
                    " Total Value: " + calculateTotalValue(bestAnt));
            clearAnts();
        }
    }
    private void selectItems(Ant ant) {
        int remainingWeight = maxWeight;
        while (remainingWeight > 0) {
            int selectedItem = rouletteWheelSelection(ant, remainingWeight);
            ant.selectedItems.add(selectedItem);
            remainingWeight -= items.get(selectedItem).weight;
        }
    }
    private int rouletteWheelSelection(Ant ant, int remainingWeight) {
        double[] probabilities = new double[numberOfItems];
        double totalProbability = 0.0;
        for (int i = 0; i < numberOfItems; i++) {
            if (!ant.selectedItems.contains(i) && items.get(i).weight <= remainingWeight) {
                double pheromone = pheromoneMatrix[i][i];
                double heuristic = items.get(i).value / (double) items.get(i).weight;
                probabilities[i] = Math.pow(pheromone, alpha) * Math.pow(heuristic, beta);
                totalProbability += probabilities[i];
            }
        }
        double randomValue = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numberOfItems; i++) {
            if (probabilities[i] > 0) {
                cumulativeProbability += probabilities[i];
                if (cumulativeProbability >= randomValue) {
                    return i;
                }
            }
        }
        return random.nextInt(numberOfItems);
    }
    private void localSearch(Ant ant) {
        if (ant.selectedItems.size() < 2) {
            return;
        }
        int index1 = random.nextInt(ant.selectedItems.size());
        int index2;
        do {
            index2 = random.nextInt(ant.selectedItems.size());
        } while (index1 == index2);
        int temp = ant.selectedItems.get(index1);
        ant.selectedItems.set(index1, ant.selectedItems.get(index2));
        ant.selectedItems.set(index2, temp);
    }
    private void updatePheromones() {
        for (int i = 0; i < numberOfItems; i++) {
            for (int j = 0; j < numberOfItems; j++) {
                pheromoneMatrix[i][j] *= (1 - evaporationRate);
            }
        }
        for (Ant ant : ants) {
            double antPheromoneDelta = 1.0 / calculateTotalValue(ant);
            for (int i : ant.selectedItems) {
                pheromoneMatrix[i][i] += antPheromoneDelta;
            }
        }
    }
    private Ant findBestAnt() {
        Ant bestAnt = ants[0];
        for (Ant ant : ants) {
            if (calculateTotalValue(ant) > calculateTotalValue(bestAnt)) {
                bestAnt = ant;
            }
        }
        return bestAnt;
    }
    private int calculateTotalValue(Ant ant) {
        int totalValue = 0;
        for (int i : ant.selectedItems) {
            totalValue += items.get(i).value;
        }
        return totalValue;
    }
    private void clearAnts() {
        for (Ant ant : ants) {
            ant.selectedItems.clear();
        }
    }
}
public class Multiple_Knapsack_Problem {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(2, 5));
        items.add(new Item(3, 8));
        items.add(new Item(5, 13));
        items.add(new Item(7, 21));
        int numberOfAnts = 5;
        int maxWeight = 10;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        double localSearchProbability = 0.3;
        KnapsackProblem knapsackProblem = new KnapsackProblem(numberOfAnts, items.size(), items, maxWeight,
                alpha, beta, evaporationRate, localSearchProbability);
        knapsackProblem.runACO(100);
    }
}