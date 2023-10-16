package Model;

import java.util.List;
import java.util.Objects;

public class Waterfowl extends Creature {
    public Waterfowl(double weight, int age){
        super(weight, age);
    }
    @Override
    public void Move(List<Cage> Cages) {
        for (Cage cage:Cages) {
            if (Objects.equals(cage.GetFor(), "Waterfowl")) cage.putCreature(this);
        }
    }

    public String ToString(){
        return "Class - Waterfowl, Age - " + this.age + ", Weight - " + this.weight;
    }
}

