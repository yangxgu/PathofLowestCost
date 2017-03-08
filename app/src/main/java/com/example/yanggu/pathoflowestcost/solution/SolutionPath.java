package com.example.yanggu.pathoflowestcost.solution;

import com.example.yanggu.pathoflowestcost.model.PathMatrix;
import com.example.yanggu.pathoflowestcost.model.Step;

import java.util.ArrayList;


public class SolutionPath extends ArrayList<Step> {
    private PathMatrix pathMatrix;

    public SolutionPath(PathMatrix pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    @Override
    public SolutionPath clone() {
        SolutionPath solutionPath = new SolutionPath(pathMatrix);

        for (Step step : this) {

            solutionPath.add(step.clone());

        }
        return solutionPath;
    }

    public int getCost() {
        //Creates a 0 value accumulator. If there are no elements a path value of 0 is returned
        int accumulator = 0;
        for (Step step : this) {
            accumulator += step.getCost();
        }

        return accumulator;
    }

    public boolean terminates() {
        return this.size() == pathMatrix.getWidth();
    }


}
