import java.util.List;
import java.util.Objects;

public class Hoofed extends Creature{
    public Hoofed(double weight, int age){
        super(weight, age);
    }
    @Override
    public void Move(List<Cage> Cages) {
        for (Cage cage:Cages) {
            if (Objects.equals(cage.GetFor(), "Model.Hoofed")) cage.putCreature(this);
        }
    }

    public String ToString(){
        return "Class - Model.Hoofed, Age - " + this.age + " Weight - " + this.weight;
    }
}
