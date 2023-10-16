package Model;

public class IfraCage extends Cage{
    public IfraCage(){
        super("ColdBlooded");
    }

    @Override
    public String ToString(){
        StringBuilder str = new StringBuilder();
        str.append("InfraCage:\n");
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString()).append("\n");
        }
        return str.toString();
    }
}
