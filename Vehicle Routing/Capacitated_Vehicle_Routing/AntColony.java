package Capacitated_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.Random;
public class AntColony {
    private int numAnts;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private double[][] pheromones;
    private Graph graph;
    private ArrayList<Ant> ants;
    public AntColony(int numAnts, double alpha, double beta, double evaporationRate, Graph graph) {
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.graph = graph;
        this.pheromones = new double[graph.getNumCities()][graph.getNumCities()];
        for (int i = 0; i < graph.getNumCities(); i++) {
            for (int j = 0; j < graph.getNumCities(); j++) {
                pheromones[i][j] = 1.0;
            }
        }
        this.ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant());
        }
    }
    public ArrayList<Ant> getAnts() {
        return ants;
    }
    public void runACO(int numIterations) {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            ants = initializeAnts();
            for (Ant ant : ants) {
                buildAntTour(ant);
                updatePheromones(ant);
            }
            evaporatePheromones();
        }
    }
    private ArrayList<Ant> initializeAnts() {
        ArrayList<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant());
        }
        return ants;
    }
    private void buildAntTour(Ant ant) {
        int startCity = 0;
        ant.addToTour(startCity);
        int currentCity = startCity;
        for (int i = 1; i < graph.getNumCities(); i++) {
            int nextCity = selectNextCity(ant, currentCity);
            ant.addToTour(nextCity);
            ant.setTourLength(ant.getTourLength() + graph.getDistance(currentCity, nextCity));
            currentCity = nextCity;
        }
        ant.addToTour(startCity);
        ant.setTourLength(ant.getTourLength() + graph.getDistance(currentCity, startCity));
    }
    private int selectNextCity(Ant ant, int currentCity) {
        double[] probabilities = calculateProbabilities(ant, currentCity);
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < graph.getNumCities(); i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }
        return -1;
    }
    private double[] calculateProbabilities(Ant ant, int currentCity) {
        double[] probabilities = new double[graph.getNumCities()];
        double total = 0.0;
        for (int i = 0; i < graph.getNumCities(); i++) {
            if (!ant.getTour().contains(i)) {
                double pheromone = Math.pow(pheromones[currentCity][i], alpha);
                double attractiveness = 1.0 / graph.getDistance(currentCity, i);
                probabilities[i] = pheromone * attractiveness;
                total += probabilities[i];
            }
        }
        for (int i = 0; i < graph.getNumCities(); i++) {
            probabilities[i] /= total;
        }
        return probabilities;
    }
    private void updatePheromones(Ant ant) {
        double pheromoneDeposit = 1.0 / ant.getTourLength();
        for (int i = 0; i < ant.getTour().size() - 1; i++) {
            int fromCity = ant.getTour().get(i);
            int toCity = ant.getTour().get(i + 1);
            pheromones[fromCity][toCity] += pheromoneDeposit;
            pheromones[toCity][fromCity] += pheromoneDeposit;
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < graph.getNumCities(); i++) {
            for (int j = 0; j < graph.getNumCities(); j++) {
                pheromones[i][j] *= (1.0 - evaporationRate);
            }
        }
    }
}
