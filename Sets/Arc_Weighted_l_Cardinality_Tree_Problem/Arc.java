package Arc_Weighted_l_Cardinality_Tree_Problem;
// Created: September 2022
public class Arc {
    private Node source;
    private Node destination;
    private double pheromoneLevel;
    private double weight;
    public Arc(Node source, Node destination, double pheromoneLevel, double weight) {
        this.source = source;
        this.destination = destination;
        this.pheromoneLevel = pheromoneLevel;
        this.weight = weight;
    }
    public Node getSource() {
        return source;
    }
    public void setSource(Node source) {
        this.source = source;
    }
    public Node getDestination() {
        return destination;
    }
    public void setDestination(Node destination) {
        this.destination = destination;
    }
    public double getPheromoneLevel() {
        return pheromoneLevel;
    }
    public void setPheromoneLevel(double pheromoneLevel) {
        this.pheromoneLevel = pheromoneLevel;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}