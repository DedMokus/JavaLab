package Viev;

import Model.Cage;

import javax.swing.*;
import java.util.List;

abstract public class MenuItem {
    final static public int extraWidth = 100;

    public String ItemName = "";

    protected JPanel card = new JPanel() {

    };

    public JPanel getPanel(){
        return card;
    }

    public String getItemName(){
        return ItemName;
    }

    public JPanel FillCard(List<Cage> cages){

        return null;
    }
    /*Менюшки:
    Создание массива зверей
    Запись в файл(мб при выходе)
    Вывод на экран всего
    Добавление нового животного
    Расселение?


*/

}
