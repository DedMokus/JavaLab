package Model;

import java.util.List;
import java.util.Objects;

public class Feathered extends Creature{
    public Feathered(double weight, int age){
        super(weight, age);
    }
    @Override
    public void Move(List<Cage> Cages) {
        for (Cage cage:Cages) {
            if (Objects.equals(cage.GetFor(), "Model.Feathered")) cage.putCreature(this);
        }
    }

    public String ToString(){
        return "Class - Model.Feathered, Age - " + this.age + " Weight - " + this.weight;
    }
}
