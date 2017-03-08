package com.example.yanggu.pathoflowestcost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dimensions extends Activity {

    EditText enterWidth, enterHeight;
    TextView warning;
    Activity activity;
    Button enterTheMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimensions_screen);

        // Initialize text fragment that displays intro text.

        enterWidth = (EditText)findViewById(R.id.matrix_width_field);
        enterHeight = (EditText)findViewById(R.id.matrix_height_field);
        warning = (TextView) findViewById(R.id.warning);
        activity = this;

        enterTheMatrix = (Button)findViewById(R.id.enter_matrix_button);

        enterTheMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = Integer.parseInt(enterWidth.getText().toString());
                int height = Integer.parseInt(enterHeight.getText().toString());

                if (width < 1 || height < 1) {
                    warning.setText("Both height and width have to be at least 1");
                }
                else {
                    Intent i = new Intent(activity, EntryGrid.class);
                    i.putExtra("width", width);
                    i.putExtra("height", height);
                    warning.setText("");
                    startActivity(i);
                }
            }
        });
    }
}
