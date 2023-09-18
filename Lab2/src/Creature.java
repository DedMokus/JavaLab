import java.util.List;

public abstract class Creature {
    protected double weight;
    protected int age;

    public Creature(double weight,int age){
        this.age = age;
        this.weight = weight;

    }

    public void Move(List<Cage> Cages){

    }

    public String ToString(){
        return "";
    }
}
