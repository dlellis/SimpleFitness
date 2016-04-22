package fit.simplefitness.models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daltonellis on 4/21/16.
 */
public class Workout extends SugarRecord {


    public String name;
    public List<String> exes = new ArrayList<String>();


    public Workout(){

    }

    public Workout(String name, List<String> exes){
        this.name = name;
        this.exes = exes;

    }


    public String toString(){
        return "Name: " + this.name;
    }

    public void adding(String addr){
        exes.add(addr);

    }



}
