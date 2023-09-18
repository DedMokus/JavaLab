public class Aquarium extends Cage {
    public Aquarium(){super("Waterfowl");}
    @Override
    public String ToString(){
        StringBuilder str = new StringBuilder();
        str.append("Aquarium:\n");
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString()).append("\n");
        }
        return str.toString();
    }
}
