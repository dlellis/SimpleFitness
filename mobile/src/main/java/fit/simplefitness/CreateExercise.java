package fit.simplefitness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Add a new exercise")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                Log.d(TAG, "Exercise to add: " + task);
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
