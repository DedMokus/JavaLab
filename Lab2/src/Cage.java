import java.util.ArrayList;
import java.util.List;
public abstract class Cage {
    protected int volume = 0;

    protected String For;
    List<Creature> CagedAnimals = new ArrayList<Creature>();

    public Cage(String ForCre){
        this.For = ForCre;
    }


    public void putCreature(Creature creature){
        this.CagedAnimals.add(creature);
    }

    public String ToString(){
        StringBuilder str = new StringBuilder();
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString());
        }
        return str.toString();
    }

    public String GetFor(){
        return For;
    }
}
