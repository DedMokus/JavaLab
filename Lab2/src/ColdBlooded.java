import java.util.List;
import java.util.Objects;

public class ColdBlooded extends Creature{
    public ColdBlooded(double weight, int age){
        super(weight, age);
    }
    @Override
    public void Move(List<Cage> Cages) {
        for (Cage cage:Cages) {
            if (Objects.equals(cage.GetFor(), "Model.ColdBlooded")) cage.putCreature(this);
        }
    }

    public String ToString(){
        return "Class - Model.ColdBlooded, Age - " + this.age + " Weight - " + this.weight;
    }
}
