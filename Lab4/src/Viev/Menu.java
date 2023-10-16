package Viev;

import Controller.Logger;
import Model.*;

import java.io.*;
import java.util.*;


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

    public boolean Login(String login,String password)
    {
        if (login.equals(this.login)||password.equals(this.password)){
            System.out.println("Login successful!");
            logger.Log("Login successful",Level.INFO);
            return true;
        }
        else{
            System.out.println("Incorrect login/password!");
            logger.Log("Incorrect login/password",Level.ERROR);
            return false;
        }
    }

    public void RenderMenu(List<Cage> cages)
    {
        logger.Log("RenderMenu begin",Level.DEBUG);
        System.out.println("Welcome, " + login + "!");
        boolean conti = true;
        while(conti) {
            int i = 0;
            System.out.flush();
            System.out.println("Choose Menu:");
            System.out.println("1. Output data");
            System.out.println("2. New animal");
            System.out.println("3. Data from disk");
            System.out.println("4. Data to disk");
            System.out.println("5. Exit");

            System.out.println("\n");

            Scanner in = new Scanner(System.in);
            System.out.println("Input number of menu");
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

    public void RenderMenuDebug(List<Cage> cages)
    {
        logger.Log("RenderMenu begin",Level.DEBUG);
        boolean conti = true;
        while(conti) {
            int i = 0;
            System.out.flush();
            System.out.println("Choose Menu:");
            System.out.println("1. Output data");
            System.out.println("2. New animal");
            System.out.println("3. Data from disk");
            System.out.println("4. Data to disk");
            System.out.println("5. Exit");
            System.out.println("6. Clear data");

            System.out.println("\n");

            Scanner in = new Scanner(System.in);
            System.out.println("Input number of menu");
            i = in.nextInt();

            switch (i) {
                case 1 -> Output(cages);
                case 2 -> Input(cages);
                case 3 -> cages = DiskRead();
                case 4 -> DiskRecord(cages);
                case 5 -> conti = false;
                case 6 -> {
                    cages = null;
                    System.gc();
                }

                default -> System.out.println("Input correct int!");
            }

        }
        logger.Log("RenderMenu end",Level.DEBUG);
    }

    public void Output(List<Cage> cages)
    {
        try {
            logger.Log("Output method begin", Level.DEBUG);
            System.out.flush();
            Iterator<Cage> iter = cages.iterator();
            for (Cage cage : cages) {
                System.out.println(cage.ToString());
            }
            try {
                System.out.println("Enter to continue");
                System.in.read();
            }
            catch (IOException e)
            {
                logger.Log(e.getMessage(),Level.ERROR);
                throw new RuntimeException(e);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Empty array!");
            logger.Log(e.getMessage(),Level.ERROR);
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
            default -> {
                System.out.println("Incorrect class name!");
                logger.Log("Incorrect class name!",Level.ERROR);
            }
        }
        try {
            System.out.println("Enter to continue");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.Log("Input method end",Level.DEBUG);
    }

    public List<Cage> DiskRead()
    {
        logger.Log("Disk Read method begin",Level.DEBUG);
        String temp = "";

        List<Cage> Cages = new ArrayList<Cage>(Arrays.asList(new Aquarium(),new GridCage(),new IfraCage(),new OpenCage()));

        try
        {
            BufferedReader breader = new BufferedReader(new FileReader("data.txt"));

            int a = Integer.parseInt(breader.readLine());
            for (int i = 0; i <a ; i++) {
                int CreaCount = Integer.parseInt(breader.readLine());
                for(int j = 0; j < CreaCount; j++){
                    temp = breader.readLine();
                    String[] info = temp.split(", ");
                    switch (info[0]){
                        case "Class - Feathered" -> {
                            int age = Integer.parseInt(info[1].split(" ")[2]);
                            float weight = Float.parseFloat(info[2].split(" ")[2]);
                            Cages.get(1).putCreature(new Feathered(weight,age));
                        }
                        case "Class - Waterfowl" -> {
                            int age = Integer.parseInt(info[1].split(" ")[2]);
                            float weight = Float.parseFloat(info[2].split(" ")[2]);
                            Cages.get(0).putCreature(new Waterfowl(weight,age));
                        }
                        case "Class - ColdBlooded" ->{
                            int age = Integer.parseInt(info[1].split(" ")[2]);
                            float weight = Float.parseFloat(info[2].split(" ")[2]);
                            Cages.get(2).putCreature(new ColdBlooded(weight,age));
                        }
                        case "Class - Hoofed" -> {
                            int age = Integer.parseInt(info[1].split(" ")[2]);
                            float weight = Float.parseFloat(info[2].split(" ")[2]);
                            Cages.get(3).putCreature(new Hoofed(weight,age));
                        }
                        default -> {}
                    }
                }
            }
        } catch (IOException e) {
            logger.Log(e.getMessage(),Level.ERROR);
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Enter to continue");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.Log("Disk Reader method end",Level.DEBUG);
        return Cages;
    }

    public void DiskRecord(List<Cage> cages)
    {
        logger.Log("Disk Record method begin", Level.DEBUG);

        try(FileWriter recorder = new FileWriter("data.txt",false))
        {
            recorder.append(cages.toArray().length+"\n");
            for(Cage cage:cages)
            {
                recorder.append(cage.Length()+"\n");
                for(Creature crea:cage.CagedAnimals){
                    recorder.append(crea.ToString()+"\n");
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
        catch (NullPointerException e)
        {
            System.out.println("Empty array!");
            logger.Log(e.getMessage(),Level.ERROR);
        }

        try {
            System.out.println("Enter to continue");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void test(List<Cage> cages){
        System.out.println("Netu");
    }
}
