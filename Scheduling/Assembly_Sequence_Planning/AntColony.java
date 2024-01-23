package Assembly_Sequence_Planning;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AntColony {
    private int numAnts;
    private int numIterations;
    private double evaporationRate;
    private double alpha;
    private double beta;
    private AssemblySequence assemblySequence;
    private double[][] pheromoneLevels;
    private int currentIteration;
    public AntColony(int numAnts, int numIterations, double evaporationRate, double alpha, double beta, AssemblySequence assemblySequence) {
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.evaporationRate = evaporationRate;
        this.alpha = alpha;
        this.beta = beta;
        this.assemblySequence = assemblySequence;
        initializePheromoneLevels();
        this.currentIteration = 0;
    }
    private void initializePheromoneLevels() {
        int numTasks = assemblySequence.getTasks().size();
        pheromoneLevels = new double[numTasks][numTasks];
        Random random = new Random();
        double initialPheromone = 1.0 / numTasks;
        for (int i = 0; i < numTasks; i++) {
            for (int j = 0; j < numTasks; j++) {
                if (i != j) {
                    pheromoneLevels[i][j] = initialPheromone;
                }
            }
        }
    }
    public void run() {
        for (currentIteration = 0; currentIteration < numIterations; currentIteration++) {
            List<Ant> ants = createAnts();
            for (Ant ant : ants) {
                ant.constructSolution(pheromoneLevels, alpha, beta);
            }
            updatePheromoneLevels(ants);
            evaporation();
        }
    }
    public int getCurrentIteration() {
        return currentIteration;
    }
    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(assemblySequence));
        }
        return ants;
    }
    private void updatePheromoneLevels(List<Ant> ants) {
        double pheromoneDecay = 0.1;
        double pheromoneDeposit = 1.0;
        for (int i = 0; i < pheromoneLevels.length; i++) {
            for (int j = 0; j < pheromoneLevels[i].length; j++) {
                pheromoneLevels[i][j] *= (1.0 - pheromoneDecay);
                for (Ant ant : ants) {
                    if (ant.hasVisited(i) && ant.hasVisited(j)) {
                        pheromoneLevels[i][j] += pheromoneDeposit;
                    }
                }
            }
        }
    }
    private void evaporation() {
        double evaporationRate = 0.05;
        for (int i = 0; i < pheromoneLevels.length; i++) {
            for (int j = 0; j < pheromoneLevels[i].length; j++) {
                pheromoneLevels[i][j] *= (1.0 - evaporationRate);
            }
        }
    }
    public AssemblySequence getBestSolution() {
        double maxPheromone = Double.MIN_VALUE;
        int bestStartIndex = -1;
        int bestEndIndex = -1;
        for (int i = 0; i < pheromoneLevels.length; i++) {
            for (int j = 0; j < pheromoneLevels[i].length; j++) {
                if (pheromoneLevels[i][j] > maxPheromone) {
                    maxPheromone = pheromoneLevels[i][j];
                    bestStartIndex = i;
                    bestEndIndex = j;
                }
            }
        }
        List<Task> reconstructedSequence = new ArrayList<>();
        reconstructedSequence.add(assemblySequence.getTasks().get(bestStartIndex));
        while (bestStartIndex != bestEndIndex) {
            bestStartIndex = bestEndIndex;
            reconstructedSequence.add(assemblySequence.getTasks().get(bestEndIndex));
        }
        return new AssemblySequence(reconstructedSequence);
    }
}