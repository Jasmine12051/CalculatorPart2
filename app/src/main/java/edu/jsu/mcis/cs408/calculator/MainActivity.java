package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static ActivityMainBinding binding;
    private final int KEYS_HEIGHT = 4;
    private final int KEYS_WIDTH = 5;
    private TextView displayTextview;
    private static edu.jsu.mcis.cs408.calculator.calculatorController calculatorController;
    static class CalculatorClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String tag = view.getTag().toString();
            Toast toast = Toast.makeText(binding.getRoot().getContext(), tag, Toast.LENGTH_SHORT);
            toast.show();
            calculatorController.handleButtonClick(tag);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initLayout();
        calculatorController = new calculatorController(this);

    }


    private void initLayout() {

        ConstraintLayout layout = binding.layout;

        int[][] horizontals = new int[KEYS_HEIGHT][KEYS_WIDTH];
        int[][] verticals = new int[KEYS_WIDTH][KEYS_HEIGHT];

        String[] btnLabelsArray = getResources().getStringArray(R.array.btnLabels);
        String[] btnTagsArray = getResources().getStringArray(R.array.btnTags);

        displayTextview = createTextView();
        layout.addView(displayTextview);
        CalculatorClickHandler click = new CalculatorClickHandler();

        for (int row = 0; row < KEYS_HEIGHT; ++row) {

            for (int col = 0; col < KEYS_WIDTH; ++col) {

                int id = View.generateViewId();

                Button key = new Button(this);
                key.setId(id);
                key.setTag(btnTagsArray[row * KEYS_WIDTH + col]);
                key.setText(btnLabelsArray[row * KEYS_WIDTH + col]);
                key.setTextSize(24);
                layout.addView(key);
                key.setOnClickListener(click);

                LayoutParams params = key.getLayoutParams();
                params.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
                params.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
                key.setLayoutParams(params);

                horizontals[row][col] = id;
                verticals[col][row] = id;


            }
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        set.connect(displayTextview.getId(), ConstraintSet.START, binding.guideWest.getId(), ConstraintSet.END, 0);
        set.connect(displayTextview.getId(), ConstraintSet.END, binding.guideEast.getId(), ConstraintSet.START, 0);
        set.connect(displayTextview.getId(), ConstraintSet.TOP, binding.guideNorth.getId(), ConstraintSet.BOTTOM, 0);
        set.constrainWidth(displayTextview.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(displayTextview.getId(), ConstraintSet.WRAP_CONTENT);

        //set.connect(key.getId(), ConstraintSet.TOP, tv.getId(), ConstraintSet.BOTTOM, 16); // Adjust the spacing
        //set.connect(key.getId(), ConstraintSet.START, binding.guideWest.getId(), ConstraintSet.END, col * 8); // Adjust the spacing

        for (int row = 0; row < KEYS_HEIGHT; ++row) {
            set.createHorizontalChain(binding.guideWest.getId(), ConstraintSet.LEFT, binding.guideEast.getId(), ConstraintSet.RIGHT, horizontals[row], null, ConstraintSet.CHAIN_PACKED);
        }

        for (int col = 0; col < KEYS_WIDTH; ++col) {
            set.createVerticalChain(displayTextview.getId(), ConstraintSet.BOTTOM, binding.guideSouth.getId(), ConstraintSet.BOTTOM, verticals[col], null, ConstraintSet.CHAIN_PACKED);
        }

        set.applyTo(layout);

    }

    private TextView createTextView() {
        int id = View.generateViewId();
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setTag("TextView" + id);
        tv.setText(R.string.defaultTextInput);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        tv.setTextSize(48);
        displayTextview = tv;
        return tv;
    }

    public void updateDisplay(String text) {
        // Update the dynamically created TextView
        displayTextview.setText(text);
    }

}
