package Viev;

import Model.*;

import java.util.List;
import java.util.Scanner;

public class Input extends MenuItem{

    public Input() {
        super("Input new animal");
    }

    @Override
    public void MainFunction(List<Cage> cages) {
        System.out.flush();
        System.out.println("Input animal class");
        Scanner in = new Scanner(System.in);
        String Aclass = in.next();

        System.out.println("Input animal weight");
        double weight = in.nextDouble();
        System.out.println("Input animal age");
        int age = in.nextInt();
        int i = 0;
        switch (Aclass) {
            case "ColdBlooded" -> {
                i = cages.lastIndexOf(new IfraCage());
                cages.get(i).putCreature(new ColdBlooded(weight, age));
            }
            case "Feathered" -> {
                i = cages.lastIndexOf(new GridCage());
                cages.get(i).putCreature(new Feathered(weight, age));
            }
            case "Hoofed" -> {
                i = cages.lastIndexOf(new OpenCage());
                cages.get(i).putCreature(new Hoofed(weight, age));
            }
            case "Waterfowl" -> {
                i = cages.lastIndexOf(new Aquarium());
                cages.get(i).putCreature(new Waterfowl(weight, age));
            }
            default -> System.out.println("Incorrect class name!");
        }
    }
}
