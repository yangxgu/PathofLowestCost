package com.example.yanggu.pathoflowestcost.solution;

import com.example.yanggu.pathoflowestcost.model.PathMatrix;
import com.example.yanggu.pathoflowestcost.model.Step;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tester {

    private PathMatrix pathMatrix;

    public Tester(PathMatrix pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    public SolutionPath navigate() {

        List<SolutionPath> listOfPaths = new ArrayList<SolutionPath>();

        for (int i = 0; i < pathMatrix.getHeight(); i++) {
            // goes down 1st row
            listOfPaths.add(iterate(new SolutionPath(pathMatrix), 0, i));
        }

        System.out.println("1234 navigate's path list size is " + listOfPaths.size());

        return getBestPath(listOfPaths);

    }

    //This is a recursive function. It takes a SolutionPath and an element designation in the form of
    //an x, y pair.

    private SolutionPath iterate(SolutionPath path, int x, int y) {
        System.out.println("1234 iterate");
        System.out.println("1234 x is " + x + ", y is " + y);
        if (terminates(path))
            return path;

        //tries out current cell to be added. If it adds up to more than 50 then this path is returned.

        int test = path.getCost() + pathMatrix.getValue(x, y);
        if (test > 50)
            return path;
        System.out.println("x is " + x);
        System.out.println("width is " + pathMatrix.getWidth());

        System.out.println("1234 path size is " + path.size());

        // If the current path isn't terminal, and doesn't add up to over 50, we add the current cell
        Step step = new Step();
        step.setRow(y + 1);
        step.setCost(pathMatrix.getValue(x, y));
        path.add(step);


        System.out.println("1234 path size is " + path.size());

        System.out.println("navigated path size is " + path.size() + ", navigated path cost is " + path.getCost());

        if (terminates(path))
            return path;


        List<SolutionPath> listOfPaths = new ArrayList<SolutionPath>();

        // looks at the rows above, level, and below

        for (int i = -1; i < 2; i++) {
            listOfPaths.add(iterate(path.clone(), x + 1, normalizeIndex(y + i)));
        }

        System.out.println("first path cost is" + listOfPaths.get(0).getCost());
        System.out.println("1234 first path length is " + listOfPaths.get(0).size());

        return getBestPath(listOfPaths);
    }

    public boolean terminates(SolutionPath solutionPath) {
        System.out.println("Path size is " + solutionPath.size() + ", matrix width is " + pathMatrix.getWidth());
        return solutionPath.size() == pathMatrix.getWidth();
    }

    /*
    private static SolutionPath iterate(PathMatrix pathMatrix, Path path) {

    }*/

    private int normalizeIndex(int test) {
        if (test < 0)
            return pathMatrix.getHeight() - 1;
        if (test > pathMatrix.getHeight() - 1)
            return 0;
        return test;
    }

    private SolutionPath getBestPath(List<SolutionPath> solutionPathList) {
        if (solutionPathList != null && solutionPathList.size() > 0) {
            filterByLength(solutionPathList);
            System.out.println("1234 filtering by length leaves us with " + solutionPathList.size() + " paths");
            filterByCost(solutionPathList);
            System.out.println("1234 filtering by cost leaves us with " + solutionPathList.size() + " paths");
            return solutionPathList.get(0);
        }

        return null;
    }

    private List<SolutionPath> filterByLength(List<SolutionPath> solutionPathList) {
        int longest = 0;

        for (SolutionPath solutionPath : solutionPathList) {
            if (solutionPath.size() > longest)
                longest = solutionPath.size();
        }

        Iterator it = solutionPathList.iterator();

        while (it.hasNext()) {
            if (((SolutionPath) it.next()).size() < longest)
                it.remove();
        }

        return solutionPathList;
    }

    private List<SolutionPath> filterByCost(List<SolutionPath> solutionPathList) {
        //I use 51 for lowest because no path is ever supposed to get that high
        int lowest = 51;

        for (SolutionPath solutionPath : solutionPathList) {
            if (solutionPath.getCost() < lowest)
                lowest = solutionPath.getCost();
        }

        System.out.println("1234 lowest cost is " + lowest);
        Iterator it = solutionPathList.iterator();

        while (it.hasNext()) {
            if (((SolutionPath) it.next()).getCost() > lowest)
                it.remove();
        }

        return solutionPathList;
    }

}
