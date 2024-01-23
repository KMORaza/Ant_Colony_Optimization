package Multistage_Flowshop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private List<Integer> tour;
    private double tourLength;
    public Ant() {
        this.tour = new ArrayList<>();
        this.tourLength = 0.0;
    }
    public List<Integer> getTour() {
        return tour;
    }
    public void addToTour(int jobId) {
        tour.add(jobId);
    }
    public double getTourLength() {
        return tourLength;
    }
    public void setTourLength(double tourLength) {
        this.tourLength = tourLength;
    }
}