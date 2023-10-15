import Controller.Logger;
import Controller.Proper;
import Model.Aquarium;
import Model.Cage;
import Model.ColdBlooded;
import Model.Creature;
import Model.Feathered;
import Model.GridCage;
import Model.Hoofed;
import Model.IfraCage;
import Model.OpenCage;
import Model.Waterfowl;
import Viev.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Prog {

    static Proper prop = new Proper(new File("prog.properties"));
    static Menu menu = new Menu(prop.getProperties());
    static Logger logger = new Logger(new File("log.txt"), prop.getProperties());
    public static void main(String[] args){
        logger.Log("Main app begin", Logger.Level.DEBUG);
        System.out.println(prop.getProperties().toString());
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
        if (Boolean.parseBoolean(prop.getProperties().getProperty("tests"))){
            menu.test(Cages);
        }
        if (Boolean.parseBoolean(prop.getProperties().getProperty("debug"))){
            menu.RenderMenuDebug(Cages);
        }
        else {
            menu.RenderMenu(Cages);
        }
        logger.Log("Main app end", Logger.Level.DEBUG);
    }
}
