package fit.simplefitness;

/**
 * Created by daltonellis on 4/22/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fit.simplefitness.models.Workout;



public class ListAdapter_workout extends ArrayAdapter<Workout> {

    public ListAdapter_workout(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter_workout(Context context, int resource, List<Workout> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.workouts, null);
        }

        Workout p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.workout_list);


            if (tt1 != null) {
                tt1.setText(p.name);

            }

        }
        return v;
    }


}