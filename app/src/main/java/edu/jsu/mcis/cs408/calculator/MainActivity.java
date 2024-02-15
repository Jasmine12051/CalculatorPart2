package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final int KEYS_HEIGHT = 4;
    private final int KEYS_WIDTH = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initLayout();
    }

    private void initLayout() {
        ConstraintLayout layout = binding.layout;
        ConstraintSet set = new ConstraintSet();

        TextView tv = createTextView();
        layout.addView(tv);

        set.clone(layout);
        set.connect(tv.getId(), ConstraintSet.START, binding.guideWest.getId(), ConstraintSet.END, 0);
        set.connect(tv.getId(), ConstraintSet.END, binding.guideEast.getId(), ConstraintSet.START, 0);
        set.connect(tv.getId(), ConstraintSet.TOP, binding.guideNorth.getId(), ConstraintSet.BOTTOM, 0);
        set.constrainWidth(tv.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(tv.getId(), ConstraintSet.WRAP_CONTENT);
        set.applyTo(layout);

        int[][] horizontals = new int[KEYS_HEIGHT][KEYS_WIDTH];
        int[][] verticals = new int[KEYS_WIDTH][KEYS_HEIGHT];

        for (int row = 0; row < KEYS_HEIGHT; ++row) {
            for (int col = 0; col < KEYS_WIDTH; ++col) {
                int id = View.generateViewId();
                String[] btnLabelsArray = getResources().getStringArray(R.array.btnLabels);
                String[] btnTagsArray = getResources().getStringArray(R.array.btnTags);

                Button key = new Button(this);
                key.setId(id);
                key.setTag(btnTagsArray[row * KEYS_WIDTH + col]);
                key.setText(btnLabelsArray[row * KEYS_WIDTH + col]);
                key.setTextSize(24);
                layout.addView(key);

                horizontals[row][col] = id;
                verticals[col][row] = id;

                set.connect(key.getId(), ConstraintSet.TOP, tv.getId(), ConstraintSet.BOTTOM, 16); // Adjust the spacing
                set.connect(key.getId(), ConstraintSet.START, binding.guideWest.getId(), ConstraintSet.END, col * 8); // Adjust the spacing
            }
        }

        for (int row = 0; row < KEYS_HEIGHT; ++row) {
            set.createHorizontalChain(binding.guideWest.getId(), ConstraintSet.LEFT, binding.guideEast.getId(), ConstraintSet.RIGHT, horizontals[row], null, ConstraintSet.CHAIN_PACKED);
        }

        for (int col = 0; col < KEYS_WIDTH; ++col) {
            set.createVerticalChain(tv.getId(), ConstraintSet.BOTTOM, binding.guideSouth.getId(), ConstraintSet.BOTTOM, verticals[col], null, ConstraintSet.CHAIN_PACKED);
        }
    }


    private TextView createTextView() {
        int id = View.generateViewId();
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setTag("TextView" + id);
        tv.setText(R.string.defaultTextInput);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        tv.setTextSize(48);
        return tv;
    }
}
