package Model;

import java.time.LocalTime;
import java.util.*;

public abstract class Cage {
    protected int volume = 0;

    public int ID = 1;

    protected String For;
    public List<Creature> CagedAnimals = new ArrayList<Creature>();
    public Map<Integer,Creature> CagedAnimalsMap = new HashMap<>();

    public Cage(String ForCre){
        this.For = ForCre;
    }

    @Override
    public boolean equals(Object obj)
    {
        return Objects.equals(((Cage) obj).For, this.For);
    }


    public void putCreature(Creature creature){
        this.CagedAnimals.add(creature);
    }

    public long[] RandomCreaturePut(int count){

        long[] times = new long[count+1];

        long starttime = System.nanoTime();

        Random randomizer = new Random();
        for(int i = 0; i < count; i++){
            long st = System.nanoTime();
            switch (For){
                case "Hoofed" -> {
                    Creature temp = new Hoofed(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimals.add(temp);
                }
                case "ColdBlooded" -> {
                    Creature temp = new ColdBlooded(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimals.add(temp);
                }
                case "Feathered" -> {
                    Creature temp = new Feathered(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimals.add(temp);
                }
                case "Waterfowl" -> {
                    Creature temp = new Waterfowl(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimals.add(temp);
                }
            }
            long ed = System.nanoTime();
            times[i] = ed-st;
        }

        long endtime = System.nanoTime();
        times[count] = endtime - starttime;
        return times;
    }

    public long[] RandomCreaturePutMap(int count){

        long[] times = new long[count+1];

        long starttime = System.nanoTime();

        Random randomizer = new Random();
        for(int i = 0; i < count; i++){
            long st = System.nanoTime();
            switch (For){
                case "Hoofed" -> {
                    Creature temp = new Hoofed(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimalsMap.put(i,temp);
                }
                case "ColdBlooded" -> {
                    Creature temp = new ColdBlooded(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimalsMap.put(i,temp);
                }
                case "Feathered" -> {
                    Creature temp = new Feathered(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimalsMap.put(i,temp);
                }
                case "Waterfowl" -> {
                    Creature temp = new Waterfowl(randomizer.nextDouble(),randomizer.nextInt());
                    CagedAnimalsMap.put(i,temp);
                }
            }
            long ed = System.nanoTime();
            times[i] = ed-st;
        }

        long endtime = System.nanoTime();
        times[count] = endtime - starttime;
        return times;
    }

    public long[] RandomRemove(int count){
        long[] times = new long[count+1];

        long starttime = System.nanoTime();

        Random randomizer = new Random();

        for(int i = 0; i < count/10; i++) {
            long st = System.nanoTime();

            CagedAnimals.remove(randomizer.nextInt(CagedAnimals.size()/2));

            long ed = System.nanoTime();
            times[i] = ed-st;
        }
        long endtime = System.nanoTime();
        times[count] = endtime - starttime;
        return times;
    }

    public long[] RandomRemoveMap(int count){
        long[] times = new long[count+1];

        long starttime = System.nanoTime();

        Random randomizer = new Random();

        for(int i = 0; i < count/10; i++) {
            long st = System.nanoTime();

            CagedAnimalsMap.remove(randomizer.nextInt(CagedAnimalsMap.size()/2));

            long ed = System.nanoTime();
            times[i] = ed-st;
        }
        long endtime = System.nanoTime();
        times[count] = endtime - starttime;
        return times;
    }

    public void putCreatureMap(Creature creature){
        this.CagedAnimalsMap.put(ID,creature);
        ID++;
    }

    public String ToString(){
        StringBuilder str = new StringBuilder();
        for (Creature crea:CagedAnimals) {
            str.append(" ").append(crea.ToString());
        }
        return str.toString();
    }

    public String ToStringMap(){
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Integer,Creature> entry : CagedAnimalsMap.entrySet() ) {
            str.append(String.format("%d: %s",entry.getKey(),entry.getValue().ToString()));
        }
        return str.toString();
    }

    public int Length(){ return CagedAnimals.toArray().length;}
    public int LengthMap() {return CagedAnimalsMap.size();}

    public String GetFor(){
        return For;
    }
}
