package Viev;

import Model.Cage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Menu {

    Properties properties;
    String login;
    String password;
    String group;
    boolean log;
    boolean checkout;
    public Menu(File config)
    {
        try {
            properties.load(new FileReader(config));
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            group = properties.getProperty("group");
            log = Boolean.parseBoolean(properties.getProperty("log","false"));
            checkout = Boolean.parseBoolean(properties.getProperty("checkout","false"));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void RenderMenu(List<Cage> cages)
    {
        int i = 0;
        System.out.flush();
        System.out.println("Choose Menu:");
        System.out.println("1. Output data");
        System.out.println("2. New animal");
        System.out.println("3. Data load");
        System.out.println("4. Data record");

        System.out.println("\n");

        Scanner in = new Scanner(System.in);
        System.out.println("Input number of menu\n");
        i = in.nextInt();

        switch (i){
            case 1:
                Output(cages);
            case 2:
                Input(cages);
            case 3:
                cages = DiscRead();
            case 4:
                DiscRecord(cages);
        }



    }

    public static void Output(List<Cage> cages)
    {

    }

    public static void Input(List<Cage> cages)
    {

    }

    public static List<Cage> DiscRead()
    {

        return null;
    }

    public static void DiscRecord(cages)
    {

    }





}
