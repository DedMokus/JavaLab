package Model;

public class GridCage extends Cage{
    public GridCage(){
        super("Feathered");
    }

    @Override
    public String ToString(){
        StringBuilder str = new StringBuilder();
        str.append("GridCage:\n");
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString()).append("\n");
        }
        return str.toString();
    }
}
