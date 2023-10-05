package Viev;

import Model.Cage;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    MenuItem[] items = {new Output(),new Input(),new DiskRecord()};
    public void RenderMenu(MenuItem... items){
        int i = 0;
        System.out.flush();
        System.out.println("Choose Menu");
        for (MenuItem item:items) {

            System.out.println(i+": "+item.ItemName);
        }
    }

    public void ChooseMenu(List<Cage> cages,MenuItem... items){
        Scanner in = new Scanner(System.in);
        System.out.println("Input number of menu\n");
        int i = in.nextInt();
        items[i].MainFunction(cages);
    }

    public void Menu(List<Cage> cages,Output()){
        RenderMenu();
        ChooseMenu(cages);
    }
}
