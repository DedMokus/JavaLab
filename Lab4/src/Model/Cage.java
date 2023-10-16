package Model;

import java.util.*;

public abstract class Cage {
    protected int volume = 0;

    public int ID = 1;

    protected String For;
    public List<Creature> CagedAnimals = new ArrayList<Creature>();
    public Map<Integer,Creature> CagedAnimalsMap = new HashMap<>();

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

    public void putCreatureMap(Creature creature){
        this.CagedAnimalsMap.put(ID,creature);
        ID++;
    }

    public String ToString(){
        StringBuilder str = new StringBuilder();
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString());
        }
        return str.toString();
    }

    public String ToStringMap(){
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Integer,Creature> entry : CagedAnimalsMap.entrySet() ) {
            str.append(String.format("%d: %s",entry.getKey(),entry.getValue().ToString()));
        }
        return str.toString();
    }

    public int Length(){ return CagedAnimals.toArray().length;}
    public int LengthMap() {return CagedAnimalsMap.size();}

    public String GetFor(){
        return For;
    }
}
