package Viev;

import Model.*;

import java.util.*;

public class MainMenu {

    static MenuItem[] items = {new Output(),new Input(),new DiskRecord()};
    public static void RenderMenu(MenuItem... items){
        int i = 0;
        System.out.flush();
        System.out.println("Choose Menu");
        for (MenuItem item:items) {

            System.out.println(i+": "+item.ItemName);
        }
    }

    public static void ChooseMenu(List<Cage> cages,MenuItem... items){
        Scanner in = new Scanner(System.in);
        System.out.println("Input number of menu\n");
        int i = in.nextInt();
        items[i].MainFunction(cages);

    }

    public static void Menu(List<Cage> cages){
        RenderMenu(items);
        ChooseMenu(cages,items);
    }

    public static void main(String[] args)
    {
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

        Menu(Cages);
    }
}
