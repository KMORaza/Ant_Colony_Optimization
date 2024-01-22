package Sequential_Ordering_Problem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// Created: September 2022
public class Ant {
    private int currentPosition;
    private List<Node> visitedNodes;
    public Ant(int initialPosition) {
        this.currentPosition = initialPosition;
        this.visitedNodes = new ArrayList<>();
        visitedNodes.add(new Node(initialPosition));
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public List<Node> getVisitedNodes() {
        return visitedNodes;
    }
    public void move(Graph graph) {
        Random random = new Random();
        List<Node> neighboringNodes = graph.getNodes();
        int nextIndex = random.nextInt(neighboringNodes.size());
        Node nextNode = neighboringNodes.get(nextIndex);
        currentPosition = nextNode.getNodeId();
        visitedNodes.add(nextNode);
    }
}

