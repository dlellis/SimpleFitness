package fit.simplefitness;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.orm.query.Select;

import java.util.List;

import fit.simplefitness.models.Workout;


public class MainActivity extends AppCompatActivity {

    public final static String extra_message = "worked";
    public Integer newint;


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
    public void viewWorkout(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        Integer position = listView.getPositionForView(parentRow);
        //position = position+1;
        List<Workout> small = Select.from(Workout.class).orderBy("id").list();
        Workout clicked = small.get(position);
        Intent intent = new Intent(this, ViewWorkout.class);
        newint = clicked.getId().intValue();
        String testm = newint.toString();
        intent.putExtra(extra_message, testm);
        Log.d("findme", newint.toString());
        intent.putExtra("whichex", newint);
        startActivity(intent);
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
        try {
            List<Workout> exercises = Select.from(Workout.class).orderBy("id").list();
            mWkListView = (ListView) findViewById(R.id.workout_lView);
            android.widget.ListAdapter mAdapter = new ListAdapter_workout(this, R.layout.content_workout, exercises);
            mWkListView.setAdapter(mAdapter);

        }
        catch (SQLiteException e) {
            return;
        }

    }


}
