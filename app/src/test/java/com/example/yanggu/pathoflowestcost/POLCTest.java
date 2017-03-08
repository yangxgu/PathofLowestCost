package com.example.yanggu.pathoflowestcost;

import com.example.yanggu.pathoflowestcost.model.PathMatrix;
import com.example.yanggu.pathoflowestcost.model.Step;
import com.example.yanggu.pathoflowestcost.solution.Path;
import com.example.yanggu.pathoflowestcost.solution.SolutionPath;
import com.example.yanggu.pathoflowestcost.solution.Tester;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yanggu on 3/1/17.
 */

public class POLCTest {
    @Test
    public void matrixesAreCorrect() throws Exception {
        //testTrivial();
        //testRow();
        //testColumn();
        //testOverrun();
        //testNoPath();
        testNegativePath();
    }

    public void testTrivial() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> firstColumn = new ArrayList<Integer>();
        firstColumn.add(1);
        pathMatrix.addColumn(firstColumn);
        Tester tester = new Tester(pathMatrix);
        SolutionPath derivedPath = tester.navigate();
        Step testStep = new Step();
        testStep.setCost(1);
        testStep.setRow(1);
        SolutionPath testPath = new SolutionPath(pathMatrix);
        testPath.add(testStep);
        assertTrue(tester.terminates(derivedPath));
        assertTrue(derivedPath.equals(testPath));
        assertTrue(derivedPath.getCost() == 1);
    }

    public void testRow() {
        PathMatrix pathMatrix = new PathMatrix();

        for (int i = 0; i < 5; i++) {
            List<Integer> column = new ArrayList<Integer>();
            column.add(1);
            pathMatrix.addColumn(column);
        }

        Tester tester = new Tester(pathMatrix);
        SolutionPath derivedPath = tester.navigate();

        SolutionPath testPath = new SolutionPath(pathMatrix);
        for (int i = 0; i < 5; i++) {
            Step step = new Step();
            step.setRow(1);
            step.setCost(1);
            testPath.add(step);
        }

        System.out.println("1234 derived path cost is " + derivedPath.getCost());
        System.out.println("1234 test path cost is " + testPath.getCost());

        assertTrue(derivedPath.getCost() == testPath.getCost());

        for (int i = 0; i < 5; i++) {
            assertTrue(derivedPath.get(i).getRow() == testPath.get(i).getRow());
        }

    }

    public void testColumn() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> listStep = new ArrayList<Integer>();

        listStep.add(5);
        listStep.add(8);
        listStep.add(5);
        listStep.add(3);
        listStep.add(5);

        pathMatrix.addColumn(listStep);

        Tester tester = new Tester(pathMatrix);

        SolutionPath derivedPath = tester.navigate();

        assertTrue(derivedPath.getCost() == 3);
    }

    public void testOverrun() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(69);
        list1.add(51);
        list1.add(60);

        pathMatrix.addColumn(list1);

        Tester tester = new Tester(pathMatrix);

        SolutionPath derivedPath = tester.navigate();

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 0);

    }

    public void testNoPath() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(19);
        list1.add(21);
        list1.add(20);

        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(10);
        list2.add(23);
        list2.add(12);

        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(19);
        list3.add(20);
        list3.add(20);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(10);
        list4.add(19);
        list4.add(11);

        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        Tester tester = new Tester(pathMatrix);

        SolutionPath derivedPath = tester.navigate();

        System.out.println("length of derived path is " +derivedPath.getCost());

        assertTrue(derivedPath.getCost() == 48);
        assertTrue(derivedPath.size() == 3);
    }

    public void testNegativePath() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(6);
        list1.add(-5);
        list1.add(3);
        list1.add(9);


        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(3);
        list2.add(2);
        list2.add(-2);
        list2.add(-1);


        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(-5);
        list3.add(4);
        list3.add(6);
        list3.add(-2);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(9);
        list4.add(10);
        list4.add(10);
        list4.add(10);


        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        Tester tester = new Tester(pathMatrix);

        SolutionPath derivedPath = tester.navigate();

        System.out.println("length of derived path is " +derivedPath.getCost());

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 4);
        assertTrue(derivedPath.get(0).getRow() == 2);
        assertTrue(derivedPath.get(1).getRow() == 3);
        assertTrue(derivedPath.get(2).getRow() == 4);
        assertTrue(derivedPath.get(3).getRow() == 1);



    }


}
