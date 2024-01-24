package Partition_Problem;
// Created: September 2022
public class PartitionProblem {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numAnts = 5;
        int numIterations = 100;
        AntColony antColony = new AntColony(numbers, numAnts, numIterations);
        int[] partition = antColony.solve();
        System.out.println("Partition 1: " + arrayToString(partition));
        System.out.println("Partition 2: " + arrayToString(antColony.getRemainingItems()));
    }
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}