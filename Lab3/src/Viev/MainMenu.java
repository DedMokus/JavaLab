package Viev;

import Model.*;

import java.util.*;

public class MainMenu {


    public static void RenderMenu(MenuItem... items){
        int i = 0;
        System.out.flush();
        System.out.println("Choose Menu");
        for (MenuItem item:items) {

            System.out.println(i+": "+item.ItemName);
        }
    }




}
