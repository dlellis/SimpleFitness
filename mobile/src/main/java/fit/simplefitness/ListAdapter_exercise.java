package fit.simplefitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fit.simplefitness.models.Exercise;


public class ListAdapter_exercise extends ArrayAdapter<Exercise> {

    public ListAdapter_exercise(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter_exercise(Context context, int resource, List<Exercise> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.exercises, null);
        }

        Exercise p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.exercise_name);


            if (tt1 != null) {
                tt1.setText(p.name);

            }

        }
            return v;
        }

    }