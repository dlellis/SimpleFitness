package fit.simplefitness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.orm.query.Select;

import java.util.List;

import fit.simplefitness.models.Exercise;

public class CreateWorkout extends AppCompatActivity {
    private static final String TAG = "CreateExerciseActivity";

    private ListView mExListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UpdateUI();







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_exercise);
        fab.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                final EditText taskEditText = new EditText(view.getContext());
                final EditText taskNumReps = new EditText(view.getContext());
                final EditText taskNumSets = new EditText(view.getContext());
                final EditText taskNumMiles = new EditText(view.getContext());
                taskNumReps.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                taskNumSets.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                taskNumMiles.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                taskEditText.setHint("Name of Exercise");
                taskNumReps.setHint("Number of Reps");
                taskNumMiles.setHint("Number of Miles");
                taskNumSets.setHint("Number of Sets");


                LinearLayout ll = new LinearLayout(view.getContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(taskEditText);
                ll.addView(taskNumMiles);
                ll.addView(taskNumSets);
                ll.addView(taskNumReps);

                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Add a new exercise")
                        .setMessage("Enter all that apply")
                        .setView(ll)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = String.valueOf(taskEditText.getText());
                                String reps = String.valueOf(taskNumReps.getText());
                                String miles = String.valueOf(taskNumMiles.getText());
                                String sets = String.valueOf(taskNumSets.getText());
                                Log.d(TAG, "Exercise to add: " + name);
                                Log.d(TAG, "Number of miles: " + miles);
                                Log.d(TAG, "Number of reps: " + reps);
                                Log.d(TAG, "Number of sets: " + sets);
                                Exercise ex = new Exercise(name, miles, reps, sets);
                                ex.save();
                                String wkName = "Yay";
//                                List<String> wkList = new ArrayList<String>();

                                Log.d("id", Long.toString(ex.getId()));
//                                Workout wk = new Workout(wkName, )
                                UpdateUI();

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void UpdateUI(){
        mExListView = (ListView) findViewById(R.id.exercises);
        List<Exercise> exercises = Select.from(Exercise.class).orderBy("name").list();
        android.widget.ListAdapter mAdapter = new ListAdapter_exercise(this, R.layout.content_create_workout, exercises);
        mExListView.setAdapter(mAdapter);

    }

    public void addExercise(View view){
        


    }



}
