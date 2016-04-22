package fit.simplefitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.orm.query.Select;

import java.util.List;

import fit.simplefitness.models.Workout;


public class MainActivity extends AppCompatActivity {




    private ListView mWkListView;
    public void createExercise(View view){
        Intent intent = new Intent(this, CreateWorkout.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.save_save);
        item.setVisible(false);
        this.invalidateOptionsMenu();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void UpdateUI(){
        mWkListView = (ListView) findViewById(R.id.workout_lView);
        List<Workout> workouts = Select.from(Workout.class).orderBy("id").list();
        android.widget.ListAdapter mAdapter = new ListAdapter_workout(this, R.layout.content_workout, workouts);
        mWkListView.setAdapter(mAdapter);

    }
}
