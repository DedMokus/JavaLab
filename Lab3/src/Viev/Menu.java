package Viev;

import Controller.Logger;
import Model.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


import static Controller.Logger.Level;

public class Menu {


    Properties properties = new Properties();
    String login;
    String password;
    String group;
    boolean log;
    boolean checkout;
    Logger logger;
    public Menu(Properties prop)
    {
            logger = new Logger(new File("log.txt"),prop);
            logger.Log("Properties loading",Level.INFO);
            properties = prop;
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            group = properties.getProperty("group");
            log = Boolean.parseBoolean(properties.getProperty("log","false"));
            checkout = Boolean.parseBoolean(properties.getProperty("checkout","false"));
    }

    public void RenderMenu(List<Cage> cages)
    {
        logger.Log("RenderMenu begin",Level.DEBUG);
        boolean conti = true;
        while(conti) {
            int i = 0;
            System.out.flush();
            System.out.println("Choose Menu:");
            System.out.println("1. Output data");
            System.out.println("2. New animal");
            System.out.println("3. Data load");
            System.out.println("4. Data record");
            System.out.println("5. Exit");

            System.out.println("\n");

            Scanner in = new Scanner(System.in);
            System.out.println("Input number of menu\n");
            i = in.nextInt();

            switch (i) {
                case 1 -> Output(cages);
                case 2 -> Input(cages);
                case 3 -> cages = DiskRead();
                case 4 -> DiskRecord(cages);
                case 5 -> conti = false;

                default -> System.out.println("Input correct int!");
            }

        }
        logger.Log("RenderMenu end",Level.DEBUG);
    }

    public void Output(List<Cage> cages)
    {
        logger.Log("Output method begin",Level.DEBUG);
        System.out.flush();
        Iterator<Cage> iter = cages.iterator();
        for (Cage cage:cages) {
            System.out.println(cage.ToString());
        }
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.Log("Output method end",Level.DEBUG);
    }

    public void Input(List<Cage> cages)
    {
        logger.Log("Input method start",Level.DEBUG);
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
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.Log("Input method end",Level.DEBUG);
    }

    public List<Cage> DiskRead()
    {

        return null;
    }

    public void DiskRecord(List<Cage> cages)
    {
        logger.Log("Disk Record method begin", Level.DEBUG);
        try(FileWriter recorder = new FileWriter("data.txt",false))
        {
            recorder.append((char)cages.toArray().length);
            for(Cage cage:cages)
            {
                recorder.append(cage.GetFor());
                recorder.append((char)cage.Length());
                for(Creature crea:cage.CagedAnimals){
                    recorder.append(crea.ToString());
                }
            }
            recorder.flush();
            logger.Log("Disk Record method end",Level.DEBUG);
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            logger.Log(ex.getMessage(),Level.ERROR);
        }
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
