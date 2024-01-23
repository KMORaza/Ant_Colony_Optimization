package Split_Delivery_Vehicle_Routing;
// Created: September 2022
import java.util.Arrays;
import java.util.Random;
public class Ant {
    private int[] tour;
    private boolean[] visitedNodes;
    private Random random;
    public Ant(int numNodes) {
        tour = new int[numNodes];
        visitedNodes = new boolean[numNodes];
        random = new Random();
    }
    public void visitNode(int nodeIndex) {
        tour[nodeIndex] = nodeIndex;
        visitedNodes[nodeIndex] = true;
    }
    public boolean hasVisitedNode(int nodeIndex) {
        return visitedNodes[nodeIndex];
    }
    public int[] getTour() {
        return Arrays.copyOf(tour, tour.length);
    }
    public void clearTour() {
        Arrays.fill(visitedNodes, false);
    }
    public int getRandomNode(int currentNode) {
        int nextNode;
        do {
            nextNode = random.nextInt(visitedNodes.length);
        } while (visitedNodes[nextNode] || nextNode == currentNode);
        return nextNode;
    }
}