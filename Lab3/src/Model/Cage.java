package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Cage {
    protected int volume = 0;

    protected String For;
    List<Creature> CagedAnimals = new ArrayList<Creature>();

    public Cage(String ForCre){
        this.For = ForCre;
    }

    @Override
    public boolean equals(Object obj)
    {
        return Objects.equals(((Cage) obj).For, this.For);
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
