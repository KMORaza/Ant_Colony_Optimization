package Stochastic_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class StochasticVRP {
    private int numNodes;
    private int numVehicles;
    private List<Double>[] distanceMatrix;
    public StochasticVRP(int numNodes, int numVehicles, List<Double>[] distanceMatrix) {
        this.numNodes = numNodes;
        this.numVehicles = numVehicles;
        this.distanceMatrix = distanceMatrix;
    }
    public int getNumNodes() {
        return numNodes;
    }
    public int getNumVehicles() {
        return numVehicles;
    }
    public List<Double>[] getDistanceMatrix() {
        return distanceMatrix;
    }
}