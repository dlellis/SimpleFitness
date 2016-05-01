package fit.simplefitness.models;

import com.orm.SugarRecord;

/**
 * Created by daltonellis on 4/20/16.
 */
public class Exercise extends SugarRecord {


        public String name;
        public String miles;
        public String reps;
        public String sets;


        public Exercise(){

        }

        public Exercise(String name, String miles, String reps, String sets){
            this.name = name;
            this.reps = reps;
            this.sets = sets;
            this.miles = miles;
        }


        public String toString(){
            return "Name: " + this.name + " Miles: " + this.miles + " Reps: " + this.reps + " Sets: " + this.sets;
        }






}
