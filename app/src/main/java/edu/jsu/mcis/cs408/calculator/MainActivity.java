package edu.jsu.mcis.cs408.calculator;

import static java.lang.Integer.getInteger;

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
    private final int CHAIN_LENGTH = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initLayout();
    }

    private void initLayout() {
        int[] viewIds = new int[CHAIN_LENGTH];
        ConstraintLayout layout = binding.layout;
        int guideWestId = R.id.guideWest;
        int guideEastId = R.id.guideEast;
        int guideNorthId = R.id.guideNorth;
        ConstraintSet set = new ConstraintSet();

        TextView tv = createTextView();
        layout.addView(tv);

        set.clone(layout);
        set.connect(tv.getId(), ConstraintSet.START, guideWestId, ConstraintSet.END, 0);
        set.connect(tv.getId(), ConstraintSet.END, guideEastId, ConstraintSet.START, 0);
        set.connect(tv.getId(), ConstraintSet.TOP, guideNorthId, ConstraintSet.BOTTOM, 0);
        set.constrainWidth(tv.getId(), ConstraintSet.MATCH_CONSTRAINT);
        set.constrainHeight(tv.getId(), ConstraintSet.WRAP_CONTENT);
        set.applyTo(layout);

        for (int i = 0; i < CHAIN_LENGTH; ++i) {
            Button btn = createButton(i);
            viewIds[i] = btn.getId();
            layout.addView(btn);
            set.clone(layout);
            for(int id : viewIds){
                if(id == 9){
                    set.connect(id, ConstraintSet.LEFT, guideWestId, ConstraintSet.RIGHT, 0);
                    set.connect(id, ConstraintSet.TOP, tv.getId(), ConstraintSet.BOTTOM, 0);
                    set.applyTo(layout);
                }
                else{
                    set.connect(id, ConstraintSet.RIGHT, guideEastId, ConstraintSet.LEFT, 0);
                    set.applyTo(layout);
                }
            }
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

    private Button createButton(int index) {
        String[] btnLabelsArray = getResources().getStringArray(R.array.btnLabels);
        String[] btnTagsArray = getResources().getStringArray(R.array.btnTags);

        Button btn = new Button(this);
        int id = View.generateViewId();
        btn.setId(id);
        btn.setTag(btnTagsArray[index]);
        btn.setText(btnLabelsArray[index]);
        btn.setTextSize(24);
        return btn;
    }
}