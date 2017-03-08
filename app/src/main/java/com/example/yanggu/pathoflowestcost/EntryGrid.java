package com.example.yanggu.pathoflowestcost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanggu.pathoflowestcost.model.PathMatrix;
import com.example.yanggu.pathoflowestcost.model.Step;
import com.example.yanggu.pathoflowestcost.solution.SolutionPath;
import com.example.yanggu.pathoflowestcost.solution.Tester;

import java.util.ArrayList;
import java.util.List;

public class EntryGrid extends Activity {
    int width, height;

    TextView printout;

    Button runButton;
    List<List<EditText>> editTextGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_grid);

        runButton = (Button) findViewById(R.id.run_tracer);

        width = getIntent().getIntExtra("width", 1);
        height = getIntent().getIntExtra("height", 1);

        printout = (TextView) findViewById(R.id.output_header);

        createGrid();

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printOut(startTest());
            }
        });
    }

    private void printOut(SolutionPath solutionPath) {
        String s;

        s = solutionPath.terminates() ? "Yes\n" : "No\n";
        s += solutionPath.getCost() + "\n";
        s += "[";

        for (Step step : solutionPath) {
            s += step.getRow() + " ";
        }
        s += "]";

        printout.setText(s);
    }

    private void createGrid() {
        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.grid_section);
        editTextGrid = new ArrayList<List<EditText>>();

        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            List<EditText> column = new ArrayList<EditText>();
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int heightIndex = 0; heightIndex < height; heightIndex++) {

                EditText editText = new EditText(this);
                editText.setTextSize(30);
                editText.setWidth(200);
                editText.setHeight(200);
                column.add(editText);
                linearLayout.addView(editText);
            }
            editTextGrid.add(column);
            gridLayout.addView(linearLayout);
        }
    }

    private SolutionPath startTest() {
        PathMatrix pathmatrix = new PathMatrix();

        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            List<Integer> column = new ArrayList<Integer>();

            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                column.add(Integer.parseInt(editTextGrid.get(widthIndex).get(heightIndex).getText().toString()));
            }

            pathmatrix.addColumn(column);
        }

        return new Tester(pathmatrix).navigate();
    }

}
