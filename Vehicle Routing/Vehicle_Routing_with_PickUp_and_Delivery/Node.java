package Vehicle_Routing_with_PickUp_and_Delivery;
// Created: September 2022
public class Node {
    private int id;
    private double x;
    private double y;
    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public int getId() {
        return id;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}