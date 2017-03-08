package com.example.yanggu.pathoflowestcost.solution;

import com.example.yanggu.pathoflowestcost.model.PathMatrix;

public class SolutionBundle {
    private boolean terminates;
    private int cost;

    public SolutionBundle(SolutionPath solutionPath, PathMatrix pathMatrix) {
        this.solutionPath = solutionPath;
    }

    public SolutionPath getSolutionPath() {
        return solutionPath;
    }

    public int getCost() {
        return cost;
    }

    public boolean isTerminates() {
        return terminates;
    }

    private SolutionPath solutionPath;

}
