package Viev;

import Model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscRead {
    public DiscRead(){}

    public List<Cage> MainFunction(){
        String temp = "";

        List<Cage> Cages = new ArrayList<Cage>(Arrays.asList(new Aquarium(),new GridCage(),new IfraCage(),new OpenCage()));

        try
        {
            FileReader reader = new FileReader("data.txt");

            BufferedReader breader = new BufferedReader(new FileReader("data.txt"));

            int a = Integer.parseInt(breader.readLine());
            for (int i = 0; i <a ; i++) {
                int CreaCount = Integer.parseInt(breader.readLine());
                for(int j = 0; i < CreaCount; i++){
                    temp = breader.readLine();
                    String[] info = temp.split(", ");
                    switch (info[0]){
                        case "Class - Feathered" -> {
                            int age = Integer.parseInt(info[1]);
                            float weight = Float.parseFloat(info[2]);
                            Cages.get(1).putCreature(new Feathered(weight,age));
                        }
                        case "Class - Waterfowl" -> {
                            int age = Integer.parseInt(info[1]);
                            float weight = Float.parseFloat(info[2]);
                            Cages.get(0).putCreature(new Waterfowl(weight,age));
                        }
                        case "Class - ColdBlooded" ->{
                            int age = Integer.parseInt(info[1]);
                            float weight = Float.parseFloat(info[2]);
                            Cages.get(2).putCreature(new ColdBlooded(weight,age));
                        }
                        case "Class - Hoofed" -> {
                            int age = Integer.parseInt(info[1]);
                            float weight = Float.parseFloat(info[2]);
                            Cages.get(3).putCreature(new Hoofed(weight,age));
                        }
                        default -> {
                            continue;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Cages;
    }
}
