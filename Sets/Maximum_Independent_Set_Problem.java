// Maximum Independent Set Problem using Ant Colony Optimization
// Created: September 2022
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;
public class Maximum_Independent_Set_Problem {
    static class Graph {
        private int vertices;
        private List<List<Integer>> adjacencyList;
        Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }
        void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
    }
    static class Ant {
        private int currentPosition;
        private Set<Integer> visitedVertices;
        Ant(int startPosition) {
            this.currentPosition = startPosition;
            this.visitedVertices = new HashSet<>();
            this.visitedVertices.add(startPosition);
        }
    }
    static class ACOAlgorithm {
        private static final double ALPHA = 1.0;
        private static final double BETA = 2.0;
        private static final double EVAPORATION_RATE = 0.5;
        private static final double INITIAL_PHEROMONE = 1.0;
        private static final double Q0 = 0.9;
        private Graph graph;
        private double[][] pheromone;
        private int numberOfAnts;
        ACOAlgorithm(Graph graph, int numberOfAnts) {
            this.graph = graph;
            this.numberOfAnts = numberOfAnts;
            this.pheromone = new double[graph.vertices][graph.vertices];
            for (int i = 0; i < graph.vertices; i++) {
                for (int j = 0; j < graph.vertices; j++) {
                    this.pheromone[i][j] = INITIAL_PHEROMONE;
                }
            }
        }
        int[] findMaximumIndependentSet(int maxIterations) {
            int[] bestSolution = null;
            double bestFitness = Double.MIN_VALUE;
            for (int iteration = 0; iteration < maxIterations; iteration++) {
                int[] solution = constructSolution();
                double fitness = evaluateSolution(solution);
                if (fitness > bestFitness) {
                    bestFitness = fitness;
                    bestSolution = solution.clone();
                }
                updatePheromones(solution);
            }
            return bestSolution;
        }
        private int[] constructSolution() {
            int[] solution = new int[graph.vertices];
            List<Ant> ants = initializeAnts();
            for (int i = 0; i < graph.vertices; i++) {
                for (Ant ant : ants) {
                    int nextVertex = selectNextVertex(ant);
                    ant.currentPosition = nextVertex;
                    ant.visitedVertices.add(nextVertex);
                    solution[nextVertex] = 1;
                }
            }
            return solution;
        }
        private List<Ant> initializeAnts() {
            List<Ant> ants = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < numberOfAnts; i++) {
                int startPosition = random.nextInt(graph.vertices);
                ants.add(new Ant(startPosition));
            }
            return ants;
        }
        private int selectNextVertex(Ant ant) {
            Random random = new Random();
            List<Integer> neighbors = graph.adjacencyList.get(ant.currentPosition);
            if (random.nextDouble() < Q0) {
                int selectedVertex = exploit(ant, neighbors);
                if (selectedVertex != -1) {
                    return selectedVertex;
                }
            }
            List<Integer> unvisitedNeighbors = explore(ant, neighbors);
            if (!unvisitedNeighbors.isEmpty()) {
                int randomIndex = random.nextInt(unvisitedNeighbors.size());
                return unvisitedNeighbors.get(randomIndex);
            }
            return ant.currentPosition;
        }
        private int exploit(Ant ant, List<Integer> neighbors) {
            double maxPheromone = Double.MIN_VALUE;
            int selectedVertex = -1;
            for (int neighbor : neighbors) {
                if (!ant.visitedVertices.contains(neighbor)) {
                    double pheromoneValue = pheromone[ant.currentPosition][neighbor];
                    if (pheromoneValue > maxPheromone) {
                        maxPheromone = pheromoneValue;
                        selectedVertex = neighbor;
                    }
                }
            }
            return selectedVertex;
        }
        private List<Integer> explore(Ant ant, List<Integer> neighbors) {
            List<Integer> unvisitedNeighbors = new ArrayList<>();
            for (int neighbor : neighbors) {
                if (!ant.visitedVertices.contains(neighbor)) {
                    unvisitedNeighbors.add(neighbor);
                }
            }
            return unvisitedNeighbors;
        }
        private double evaluateSolution(int[] solution) {
            int fitness = 0;
            for (int value : solution) {
                if (value == 1) {
                    fitness++;
                }
            }
            return fitness;
        }
        private void updatePheromones(int[] solution) {
            for (int i = 0; i < graph.vertices; i++) {
                for (int j = 0; j < graph.vertices; j++) {
                    if (solution[i] == 1 || solution[j] == 1) {
                        pheromone[i][j] = (1 - EVAPORATION_RATE) * pheromone[i][j];
                    } else {
                        pheromone[i][j] = (1 - EVAPORATION_RATE) * pheromone[i][j] + EVAPORATION_RATE * INITIAL_PHEROMONE;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int numberOfVertices = 15;
        Graph graph = new Graph(numberOfVertices);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 9);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
        graph.addEdge(10, 11);
        graph.addEdge(11, 12);
        graph.addEdge(12, 13);
        graph.addEdge(13, 14);
        int numberOfAnts = 10;
        ACOAlgorithm acoAlgorithm = new ACOAlgorithm(graph, numberOfAnts);
        int maxIterations = 200;
        int[] solution = acoAlgorithm.findMaximumIndependentSet(maxIterations);
        System.out.println("Maximum Independent Set:");
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1) {
                System.out.print(i + " ");
            }
        }
    }
}