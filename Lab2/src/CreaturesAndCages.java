import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

public class CreaturesAndCages {
    public static void main(String[] args){
        List<Cage> Cages = new ArrayList<Cage>(Arrays.asList(new Aquarium(),new GridCage(),new IfraCage(),new OpenCage()));
        Iterator<Cage> iterCage = Cages.iterator();

        Waterfowl Fish = new Waterfowl(0.1,1);
        Hoofed Horse1 = new Hoofed(10,5);
        Feathered Bird = new Feathered(2,10);
        ColdBlooded Lizard = new ColdBlooded(1,1);

        List<Creature> Creatures = new ArrayList<Creature>(Arrays.asList(Fish,Horse1,Bird,Lizard));
        Iterator<Creature> iterCreature = Creatures.iterator();

        while (iterCage.hasNext()){
            iterCage.next();
            while (iterCreature.hasNext()){
                iterCreature.next().Move(Cages);
            }
        }

        for (Cage cage:Cages) {
            System.out.println(cage.ToString());
        }
    }
}
