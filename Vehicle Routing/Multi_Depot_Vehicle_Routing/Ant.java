package Multi_Depot_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private int id;
    private List<Customer> tour;
    public Ant(int id) {
        this.id = id;
        this.tour = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public List<Customer> getTour() {
        return tour;
    }
    public void addToTour(Customer customer) {
        tour.add(customer);
    }
}