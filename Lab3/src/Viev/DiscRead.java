package Viev;

import Model.Cage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DiscRead {
    public DiscRead(){}

    public List<Cage> MainFunction(){
        String temp = "";

        try
        {
            FileReader reader = new FileReader("data.txt");

            BufferedReader breader = new BufferedReader(new FileReader("data.txt"));

            int a = (int)reader.read();
            for (int i = 0; i <a ; i++) {
                temp = breader.readLine();

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
