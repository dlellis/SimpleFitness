package fit.simplefitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fit.simplefitness.models.Exercise;
import fit.simplefitness.models.Workout;

public class ViewWorkout extends AppCompatActivity {

    private ListView mExListView;
    private Integer intValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent mIntent = getIntent();
        String testm = mIntent.getStringExtra(MainActivity.extra_message);
        intValue = Integer.parseInt(testm);
        Log.d("intValue", testm);
        UpdateUI();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void UpdateUI(){
        mExListView = (ListView) findViewById(R.id.view_workout);
        List<String> test = Workout.findById(Workout.class, intValue).toList();
        List<Exercise> exercises = new ArrayList<Exercise>();
        for (String e : test){
            try {
                Integer n = Integer.parseInt(e);
                Log.d("update", n.toString());
                exercises.add(Exercise.findById(Exercise.class,n));

            }
            catch (NumberFormatException j){

            }

            Log.d("example",e);
        }
        if (!exercises.isEmpty()) {
            android.widget.ListAdapter mAdapter = new ListAdapter_view_exercise(this, R.layout.content_view_workout, exercises);
            mExListView.setAdapter(mAdapter);
        }

    }

}
