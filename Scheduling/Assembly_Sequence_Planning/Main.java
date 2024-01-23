package Assembly_Sequence_Planning;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Task1", 3);
        Task task2 = new Task("Task2", 2);
        Task task3 = new Task("Task3", 5);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        AssemblySequence assemblySequence = new AssemblySequence(tasks);
        AntColony antColony = new AntColony(10, 100, 0.5, 1.0, 2.0, assemblySequence);
        antColony.run();
        AssemblySequence bestSolution = antColony.getBestSolution();
        System.out.println("Best Solution Tasks:");
        for (Task task : bestSolution.getTasks()) {
            System.out.println(task.getTaskId() + " (Difficulty: " + task.getDifficulty() + ")");
        }
        System.out.println("\nAdditional Statistics:");
        System.out.println("Best Solution Cost: " + calculateSolutionCost(bestSolution));
        System.out.println("Total Iterations: " + antColony.getCurrentIteration());
    }
    private static int calculateSolutionCost(AssemblySequence assemblySequence) {
        int totalCost = 0;
        for (Task task : assemblySequence.getTasks()) {
            totalCost += task.getDifficulty();
        }
        return totalCost;
    }
}