package Viev;

import Model.Cage;
import Model.Creature;

import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.List;

public class DiskRecord extends MenuItem{

    public DiskRecord() {
        super("Record to disk");
    }

    @Override
    public void MainFunction(List<Cage> cages) {
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
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
