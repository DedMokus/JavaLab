package Viev;

import Controller.Logger;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


import static Controller.Logger.Level;
import static Viev.DrawGraphic.createAndShowGui;

public class Menu {


    Properties properties = new Properties();
    String loginuser;
    String passworduser;

    String loginroot;
    String passwordroot;
    String group;
    boolean log;
    boolean checkout;
    Logger logger;
    public Menu(Properties prop)
    {
            logger = new Logger(new File("log.txt"),prop);
            logger.Log("Properties loading",Level.INFO);
            properties = prop;
            loginuser = properties.getProperty("users");
            passworduser = properties.getProperty("pwduser");
            loginroot = properties.getProperty("root");
            passwordroot = properties.getProperty("pwdroot");
            group = properties.getProperty("group");
            log = Boolean.parseBoolean(properties.getProperty("log","false"));
            checkout = Boolean.parseBoolean(properties.getProperty("checkout","false"));
    }

    public boolean[] Login(String login,String password)
    {
        if (this.loginuser.contains(login) & this.passworduser.contains(password)){
            System.out.println("User login successful!");
            System.out.println("Welcome, " + login + "!");
            logger.Log("User login successful",Level.INFO);
            return new boolean[]{true, false};
        }
        else{
            if(this.loginroot.contains(login) & this.passwordroot.contains(password)){
                System.out.println("Root login successful!");
                System.out.println("Welcome, " + login + "!");
                logger.Log("Root login successful",Level.INFO);
                return new boolean[]{true, true};
            }
            System.out.println("Incorrect login/password!");
            logger.Log("Incorrect login/password",Level.ERROR);
            return new boolean[]{false, false};
        }
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
            System.out.println("7. Random put Creatures");
            System.out.println("8. Test with graph");

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
                case 7 -> RandomCreatures(cages);
                case 8 -> test(cages);

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



    public void RandomCreatures(List<Cage> cages){

        logger.Log("Random fill begin",Level.DEBUG);

        Scanner in = new Scanner(System.in);
        System.out.println("Input count of creatures in one cage");
        int i = in.nextInt();

        for (Cage cage:cages) {
            long[] time = cage.RandomCreaturePut(i);
            long[] timeMap = cage.RandomCreaturePutMap(i);
        }

        logger.Log("Random fill end",Level.DEBUG);
    }

    public void test(List<Cage> cages){
        logger.Log("Test begin",Level.INFO);

        List<Double> data = new ArrayList<>();

        logger.Log("Add",Level.INFO);
        logger.Log("ArrayList 10",Level.INFO);
        long[] timeArray10 = cages.get(0).RandomCreaturePut(10);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray10[timeArray10.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray10,timeArray10.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeArray10,timeArray10.length-2)).average().getAsDouble());

        logger.Log("ArrayList 100",Level.INFO);
        long[] timeArray100 = cages.get(0).RandomCreaturePut(100);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray100[timeArray100.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray100,timeArray100.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeArray100,timeArray100.length-2)).average().getAsDouble());

        logger.Log("ArrayList 1000",Level.INFO);
        long[] timeArray1000 = cages.get(0).RandomCreaturePut(1000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray1000[timeArray1000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray1000,timeArray1000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeArray1000,timeArray1000.length-2)).average().getAsDouble());

        logger.Log("ArrayList 10000",Level.INFO);
        long[] timeArray10000 = cages.get(0).RandomCreaturePut(10000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray10000[timeArray10000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray10000,timeArray10000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeArray10000,timeArray10000.length-2)).average().getAsDouble());

        logger.Log("ArrayList 100000",Level.INFO);
        long[] timeArray100000 = cages.get(0).RandomCreaturePut(100000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray100000[timeArray100000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray100000,timeArray100000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeArray100000,timeArray100000.length-2)).average().getAsDouble());


        logger.Log("HashMap 10",Level.INFO);
        long[] timeMap10 = cages.get(0).RandomCreaturePutMap(10);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap10[timeMap10.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap10,timeMap10.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeMap10,timeMap10.length-2)).average().getAsDouble());

        logger.Log("HashMap 100",Level.INFO);
        long[] timeMap100 = cages.get(0).RandomCreaturePutMap(100);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap100[timeMap100.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap100,timeMap100.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeMap100,timeMap100.length-2)).average().getAsDouble());

        logger.Log("HashMap 1000",Level.INFO);
        long[] timeMap1000 = cages.get(0).RandomCreaturePutMap(1000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap1000[timeMap1000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap1000,timeMap1000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeMap1000,timeMap1000.length-2)).average().getAsDouble());

        logger.Log("HashMap 10000",Level.INFO);
        long[] timeMap10000 = cages.get(0).RandomCreaturePutMap(10000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap10000[timeMap10000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap10000,timeMap10000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeMap10000,timeMap10000.length-2)).average().getAsDouble());

        logger.Log("HashMap 100000",Level.INFO);
        long[] timeMap100000 = cages.get(0).RandomCreaturePutMap(100000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap100000[timeMap100000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap100000,timeMap100000.length-2)).average().getAsDouble()),Level.INFO);
        data.add(Arrays.stream(Arrays.copyOf(timeMap100000,timeMap100000.length-2)).average().getAsDouble());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui(data,Color.BLUE,"Add time Compare");
            }
        });

        List<Double> data2 = new ArrayList<>();

        logger.Log("Remove",Level.INFO);
        logger.Log("ArrayList 10",Level.INFO);
        timeArray10 = cages.get(0).RandomRemove(10);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray10[timeArray10.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray10,timeArray10.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeArray10,timeArray10.length-2)).average().getAsDouble());

        logger.Log("ArrayList 100",Level.INFO);
        timeArray100 = cages.get(0).RandomRemove(100);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray100[timeArray100.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray100,timeArray100.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeArray100,timeArray100.length-2)).average().getAsDouble());

        logger.Log("ArrayList 1000",Level.INFO);
        timeArray1000 = cages.get(0).RandomRemove(1000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray1000[timeArray1000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray1000,timeArray1000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeArray1000,timeArray1000.length-2)).average().getAsDouble());

        logger.Log("ArrayList 10000",Level.INFO);
        timeArray10000 = cages.get(0).RandomRemove(10000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray10000[timeArray10000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray10000,timeArray10000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeArray10000,timeArray10000.length-2)).average().getAsDouble());

        logger.Log("ArrayList 100000",Level.INFO);
        timeArray100000 = cages.get(0).RandomRemove(100000);
        logger.Log(String.format("%s = %d ns","Add total time",timeArray100000[timeArray100000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeArray100000,timeArray100000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeArray100000,timeArray100000.length-2)).average().getAsDouble());

        logger.Log("HashMap 10",Level.INFO);
        timeMap10 = cages.get(0).RandomRemoveMap(10);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap10[timeMap10.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap10,timeMap10.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeMap10,timeMap10.length-2)).average().getAsDouble());

        logger.Log("HashMap 100",Level.INFO);
        timeMap100 = cages.get(0).RandomRemoveMap(100);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap100[timeMap100.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap100,timeMap100.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeMap100,timeMap100.length-2)).average().getAsDouble());

        logger.Log("HashMap 1000",Level.INFO);
        timeMap1000 = cages.get(0).RandomRemoveMap(1000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap1000[timeMap1000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap1000,timeMap1000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeMap1000,timeMap1000.length-2)).average().getAsDouble());

        logger.Log("HashMap 10000",Level.INFO);
        timeMap10000 = cages.get(0).RandomRemoveMap(10000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap10000[timeMap10000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap10000,timeMap10000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeMap10000,timeMap10000.length-2)).average().getAsDouble());

        logger.Log("HashMap 100000",Level.INFO);
        timeMap100000 = cages.get(0).RandomRemoveMap(100000);
        logger.Log(String.format("%s = %d ns","Add total time",timeMap100000[timeMap100000.length-1]),Level.INFO);
        logger.Log(String.format("%s = %.2f ns","Add median time",Arrays.stream(Arrays.copyOf(timeMap100000,timeMap100000.length-2)).average().getAsDouble()),Level.INFO);
        data2.add(Arrays.stream(Arrays.copyOf(timeMap100000,timeMap100000.length-2)).average().getAsDouble());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui(data2, Color.RED,"Remove time Compare");
            }
        });
        logger.Log("Test end",Level.INFO);
    }
}
