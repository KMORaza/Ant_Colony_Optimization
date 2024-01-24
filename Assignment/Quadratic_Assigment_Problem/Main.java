package Quadratic_Assigment_Problem;
// Created: September 2022
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of locations: ");
        int numOfLocations = scanner.nextInt();
        System.out.print("Enter the size of the problem instance (e.g., small, medium, large): ");
        String instanceSize = scanner.next();
        int[][] distances = generateRandomMatrix(numOfLocations);
        int[][] flows = generateRandomMatrix(numOfLocations);
        AntColony antColony = new AntColony(numOfLocations, distances, flows, 20, 0.1, 0.5);
        int[] solution = antColony.solve();
        System.out.println("Optimal Solution: " + Arrays.toString(solution));
        System.out.println("Objective Value: " + antColony.calculateObjectiveValue(solution));
        int numRuns = 10;
        double totalObjectiveValue = 0;
        for (int run = 1; run <= numRuns; run++) {
            AntColony currentRun = new AntColony(numOfLocations, distances, flows, 20, 0.1, 0.5);
            int[] currentSolution = currentRun.solve();
            double currentObjectiveValue = currentRun.calculateObjectiveValue(currentSolution);
            System.out.println("Run " + run + ": Objective Value - " + currentObjectiveValue);
            totalObjectiveValue += currentObjectiveValue;
        }
        System.out.println("Average Objective Value over " + numRuns + " runs: " +
                (totalObjectiveValue / numRuns));
    }
    private static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        return matrix;
    }
}