package edu.jsu.mcis.cs408.calculator;

import static java.lang.Integer.getInteger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
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

        for(int i =0; i< CHAIN_LENGTH; ++i){

            int id = View.generateViewId();
            Button btn = new Button(this);
            btn.setId(id);
            btn.setTag("textView" + i);
            //btn.setText(getInteger((R.array.btnLabels)[i]) + i);
            btn.setTextSize(24);
            layout.addView(btn);
            viewIds[i] = id;
        }
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        for(int id : viewIds){
            set.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            set.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        }

        set.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, viewIds, null, ConstraintSet.CHAIN_PACKED);

        set.applyTo(layout);
    }
}