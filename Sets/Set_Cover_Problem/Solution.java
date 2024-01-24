package Set_Cover_Problem;
// Created: September 2022
import java.util.List;
import java.util.Set;
public class Solution {
    private List<Set<Integer>> selectedSets;
    public Solution(List<Set<Integer>> selectedSets) {
        this.selectedSets = selectedSets;
    }
    public List<Set<Integer>> getSelectedSets() {
        return selectedSets;
    }
}