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

public class CreateExercise extends AppCompatActivity {
    private static final String TAG = "CreateExerciseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
                                String task = String.valueOf(taskEditText.getText());
                                String reps = String.valueOf(taskNumReps.getText());
                                String miles = String.valueOf(taskNumMiles.getText());
                                String sets = String.valueOf(taskNumSets.getText());
                                Log.d(TAG, "Exercise to add: " + task);
                                Log.d(TAG, "Number of miles: " + miles);
                                Log.d(TAG, "Number of reps: " + reps);
                                Log.d(TAG, "Number of sets: " + sets);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
