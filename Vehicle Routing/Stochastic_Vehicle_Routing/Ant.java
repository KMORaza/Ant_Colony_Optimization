package Stochastic_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Integer> tour;
    private boolean[] visitedNodes;
    private StochasticVRP problem;
    public Ant(StochasticVRP problem) {
        this.tour = new ArrayList<>();
        this.visitedNodes = new boolean[problem.getNumNodes()];
        this.problem = problem;
    }
    public List<Integer> getTour() {
        return tour;
    }
    public boolean[] getVisitedNodes() {
        return visitedNodes;
    }
}