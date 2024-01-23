package Time_Dependent_Vehicle_Routing;
// Created: September 2022
public class PheromoneMatrix {
    private double[][] pheromones;
    public PheromoneMatrix(int numberOfLocations) {
        this.pheromones = new double[numberOfLocations][numberOfLocations];
    }
    public void updatePheromone(int from, int to, double delta) {
        pheromones[from][to] += delta;
        pheromones[to][from] += delta;
    }
    public void evaporate() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= 0.9;
            }
        }
    }
}