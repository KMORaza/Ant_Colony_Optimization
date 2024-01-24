package Redundancy_Allocation_Problem;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        int graphSize = 5;
        int[][] adjacencyMatrix = {
                {0, 1, 2, 3, 4},
                {1, 0, 5, 6, 7},
                {2, 5, 0, 8, 9},
                {3, 6, 8, 0, 10},
                {4, 7, 9, 10, 0}
        };
        Graph graph = new Graph(graphSize, adjacencyMatrix);
        int numAnts = 10;
        int numIterations = 100;
        RedundancyAllocation problem = new RedundancyAllocation(graph, numAnts, numIterations);
        problem.solve();
    }
}
