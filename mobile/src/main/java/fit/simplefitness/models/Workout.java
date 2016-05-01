package fit.simplefitness.models;

import android.util.Log;

import com.orm.SugarRecord;

import java.util.Arrays;
import java.util.List;

/**
 * Created by daltonellis on 4/21/16.
 */
public class Workout extends SugarRecord {


    public String name;
    public String exes;




    public Workout(){

    }

    public Workout(String name, List<String> x){
        this.name = name;
        String a = new String();
        for (String d : x){
            List<Exercise> l = Exercise.find(Exercise.class, "name = ?", d);
            String fin = l.get(0).getId().toString();
            Log.d("fin", fin);
            a = a+","+fin;

        }
        this.exes = a;
        Log.d("what happened", this.exes);
    }


    public List<String> toList(){
        List<String> x = Arrays.asList(this.exes.split(","));
        for (String i : x){
            Log.d("toList", i);
        }
       return x;
    }



    public String toString(){
        return "Exercises: " + this.exes;
    }




}
