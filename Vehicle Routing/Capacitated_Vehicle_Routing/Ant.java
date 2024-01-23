package Capacitated_Vehicle_Routing;
// Created: September 2022
import java.util.ArrayList;
public class Ant {
    private ArrayList<Integer> tour;
    private double tourLength;
    public Ant() {
        this.tour = new ArrayList<>();
        this.tourLength = 0.0;
    }
    public ArrayList<Integer> getTour() {
        return tour;
    }
    public void addToTour(int city) {
        tour.add(city);
    }
    public double getTourLength() {
        return tourLength;
    }
    public void setTourLength(double tourLength) {
        this.tourLength = tourLength;
    }
}