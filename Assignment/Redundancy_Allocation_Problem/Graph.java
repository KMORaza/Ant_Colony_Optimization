package Redundancy_Allocation_Problem;
// Created: September 2022
public class Graph {
    private int[][] adjacencyMatrix;
    public Graph(int size, int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }
    public int size() {
        return adjacencyMatrix.length;
    }
    public int getDistance(int node1, int node2) {
        return adjacencyMatrix[node1][node2];
    }
}