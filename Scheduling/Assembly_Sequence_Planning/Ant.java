package Assembly_Sequence_Planning;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Ant {
    private int currentIndex;
    private List<Integer> visitedTasks;
    private AssemblySequence assemblySequence;
    public Ant(AssemblySequence assemblySequence) {
        this.currentIndex = 0;
        this.visitedTasks = new ArrayList<>();
        this.assemblySequence = assemblySequence;
    }
    public void constructSolution(double[][] pheromoneLevels, double alpha, double beta) {
        int numTasks = assemblySequence.getTasks().size();
        Random random = new Random();
        while (!visitedAllTasks()) {
            int nextTask = chooseNextTask(pheromoneLevels, alpha, beta);
            move(nextTask);
        }
    }
    private boolean visitedAllTasks() {
        return visitedTasks.size() == assemblySequence.getTasks().size();
    }
    private int chooseNextTask(double[][] pheromoneLevels, double alpha, double beta) {
        List<Integer> availableTasks = getUnvisitedTasks();
        double[] probabilities = calculateProbabilities(availableTasks, pheromoneLevels, alpha, beta);
        int chosenIndex = selectTask(probabilities);
        return availableTasks.get(chosenIndex);
    }
    private List<Integer> getUnvisitedTasks() {
        List<Integer> unvisitedTasks = new ArrayList<>();
        for (int i = 0; i < assemblySequence.getTasks().size(); i++) {
            if (!visitedTasks.contains(i)) {
                unvisitedTasks.add(i);
            }
        }
        return unvisitedTasks;
    }
    private double[] calculateProbabilities(List<Integer> tasks, double[][] pheromoneLevels, double alpha, double beta) {
        double[] probabilities = new double[tasks.size()];
        double totalProbability = 0;
        for (int i = 0; i < tasks.size(); i++) {
            int taskIndex = tasks.get(i);
            double pheromone = pheromoneLevels[currentIndex][taskIndex];
            double attractiveness = 1.0;
            probabilities[i] = Math.pow(pheromone, alpha) * attractiveness;
            totalProbability += probabilities[i];
        }
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= totalProbability;
        }
        return probabilities;
    }
    private int selectTask(double[] probabilities) {
        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }
        return probabilities.length - 1;
    }
    private void move(int nextTaskIndex) {
        visitedTasks.add(nextTaskIndex);
        currentIndex = nextTaskIndex;
    }
    public boolean hasVisited(int taskIndex) {
        return visitedTasks.contains(assemblySequence.getTasks().get(taskIndex));
    }
}