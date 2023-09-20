package Viev;

import Model.Cage;

import java.util.Iterator;
import java.util.List;

public class Output extends MenuItem{

    public Output(){
        super("Output to display");
    }
    @Override
    public void MainFunction(List<Cage> cages){
        Iterator<Cage> iter = cages.iterator();
        for (Cage cage:cages) {
            System.out.println(cage.ToString());
        }

    }
}
