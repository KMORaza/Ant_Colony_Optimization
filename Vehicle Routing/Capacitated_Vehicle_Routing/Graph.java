package Capacitated_Vehicle_Routing;
// Created: September 2022
import java.util.HashMap;
public class Graph {
    private int numCities;
    private double[][] distances;
    public Graph(int numCities, double[][] distances) {
        this.numCities = numCities;
        this.distances = distances;
    }
    public int getNumCities() {
        return numCities;
    }
    public double getDistance(int city1, int city2) {
        return distances[city1][city2];
    }
}