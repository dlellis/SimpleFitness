package fit.simplefitness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import fit.simplefitness.models.Exercise;
import fit.simplefitness.models.Workout;

public class CreateWorkout extends AppCompatActivity {
    private static final String TAG = "CreateExerciseActivity";
    private List<String> wkList = new ArrayList<String>();
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
        View parent = (View) view.getParent();
        TextView exTextView = (TextView) parent.findViewById(R.id.exercise_name);
        TextView button = (TextView) parent.findViewById(R.id.task_add);
        if (button.getText().equals("Add")){
            Log.d("Which one?", "Looks like you'll be adding");
            String ex = String.valueOf(exTextView.getText());
            Log.d("Name of Ex", ex);
            wkList.add(ex);
            button.setText("Remove");
        }

        else {
            Log.d("Which one?", "Looks like you'll be removing");
            String ex = String.valueOf(exTextView.getText());
            wkList.remove(ex);
            button.setText("Add");

        }




    }

    public void create(){

        EditText editText = (EditText) findViewById(R.id.name_workout);
        String wkName = editText.getText().toString();
        Workout wk = new Workout(wkName, wkList);
        wk.save();
        Log.d("create", "pressed");


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_save:
                Log.d("test","first");
                create();
                Intent intent = new Intent(CreateWorkout.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void create_odd(){
        Log.d("worked","real");

    }



}
