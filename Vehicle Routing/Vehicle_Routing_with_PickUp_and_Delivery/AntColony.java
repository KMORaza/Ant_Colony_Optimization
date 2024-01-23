package Vehicle_Routing_with_PickUp_and_Delivery;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AntColony {
    private Graph graph;
    private int numAnts;
    private double[][] pheromones;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private Random random;
    private List<Integer> bestTour;
    private double bestTourLength;
    public AntColony(Graph graph, int numAnts, double alpha, double beta, double evaporationRate) {
        this.graph = graph;
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.random = new Random();
        this.bestTour = new ArrayList<>();
        this.bestTourLength = Double.POSITIVE_INFINITY;
        initializePheromones();
    }
    public List<Integer> getBestTour() {
        return bestTour;
    }
    public double getBestTourLength() {
        return bestTourLength;
    }
    public void runACO(int numIterations) {
        for (int iteration = 0; iteration < numIterations; iteration++) {
            List<Ant> ants = createAnts();
            List<List<Integer>> antTours = new ArrayList<>();
            for (Ant ant : ants) {
                traverseAnt(ant);
                antTours.add(ant.getTour());
                updateBestTour(ant);
            }
            updatePheromones(antTours);
        }
    }
    private void initializePheromones() {
        int numNodes = graph.getNodes().size();
        pheromones = new double[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                pheromones[i][j] = 1.0;
            }
        }
    }
    private void updateBestTour(Ant ant) {
        double tourLength = calculateTourLength(ant.getTour());
        if (tourLength < bestTourLength) {
            bestTourLength = tourLength;
            bestTour = new ArrayList<>(ant.getTour());
        }
    }
    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();

        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(graph.getNodes().size()));
        }
        return ants;
    }
    private void traverseAnt(Ant ant) {
        int startNode = random.nextInt(graph.getNodes().size());
        ant.visitNode(startNode);

        while (ant.getTour().size() < graph.getNodes().size()) {
            int nextNode = selectNextNode(ant);
            ant.visitNode(nextNode);
        }
    }
    private int selectNextNode(Ant ant) {
        int currentNode = ant.getTour().get(ant.getTour().size() - 1);
        double[] probabilities = calculateProbabilities(currentNode, ant);
        double rand = random.nextDouble();
        double cumulativeProbability = 0;
        for (int i = 0; i < graph.getNodes().size(); i++) {
            cumulativeProbability += probabilities[i];
            if (rand <= cumulativeProbability && !ant.hasVisited(i)) {
                return i;
            }
        }
        for (int i = 0; i < graph.getNodes().size(); i++) {
            if (!ant.hasVisited(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No unvisited node to select.");
    }
    private double[] calculateProbabilities(int currentNode, Ant ant) {
        int numNodes = graph.getNodes().size();
        double[] probabilities = new double[numNodes];
        double total = 0.0;
        for (int i = 0; i < numNodes; i++) {
            if (!ant.hasVisited(i)) {
                probabilities[i] = Math.pow(pheromones[currentNode][i], alpha) *
                        Math.pow(1.0 / calculateDistance(currentNode, i), beta);
                total += probabilities[i];
            }
        }
        for (int i = 0; i < numNodes; i++) {
            probabilities[i] /= total;
        }
        return probabilities;
    }
    private double calculateDistance(int node1, int node2) {
        Node n1 = graph.getNode(node1);
        Node n2 = graph.getNode(node2);
        return Math.sqrt(Math.pow(n1.getX() - n2.getX(), 2) + Math.pow(n1.getY() - n2.getY(), 2));
    }
    private void updatePheromones(List<List<Integer>> antTours) {
        evaporatePheromones();
        for (List<Integer> tour : antTours) {
            double tourLength = calculateTourLength(tour);
            depositPheromones(tour, 1.0 / tourLength);
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().size(); j++) {
                pheromones[i][j] *= (1.0 - evaporationRate);
            }
        }
    }
    private void depositPheromones(List<Integer> tour, double pheromoneAmount) {
        for (int i = 0; i < tour.size() - 1; i++) {
            int fromNode = tour.get(i);
            int toNode = tour.get(i + 1);
            pheromones[fromNode][toNode] += pheromoneAmount;
            pheromones[toNode][fromNode] += pheromoneAmount;
        }
    }
    private double calculateTourLength(List<Integer> tour) {
        double length = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            length += calculateDistance(tour.get(i), tour.get(i + 1));
        }
        return length;
    }
}