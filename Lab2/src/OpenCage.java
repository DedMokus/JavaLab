public class OpenCage extends Cage{
    public OpenCage(){
        super("Model.Hoofed");
    }

    @Override
    public String ToString(){
        StringBuilder str = new StringBuilder();
        str.append("Model.OpenCage:\n");
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString()).append("\n");
        }
        return str.toString();
    }
}
