package Redundancy_Allocation_Problem;
// Created: September 2022
public class Solution {
    private int[] redundancyAssignment;
    public Solution(int size) {
        this.redundancyAssignment = new int[size];
    }
    public int getRedundancyAssignment(int node) {
        return redundancyAssignment[node];
    }
    public void setRedundancyAssignment(int node, int redundancy) {
        redundancyAssignment[node] = redundancy;
    }
}