import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Car extends Thread {

    int Speed;
    int ID;
    Color COLOR;
    JButton button;


    public Car(int speed,int ID,Color color,JButton button){
        this.COLOR = color;
        this.ID = ID;
        this.Speed = speed;
        this.button = button;
    }

    @Override
    public void run() {
        do{
            if(button.getLocation().x+button.getWidth()>1200){
                System.out.println("Thread stop");
                Race.IsFinish = true;
                Race.pane.setBackground(COLOR);
                this.Final();
                return;
            }
            if(Race.IsFinish){
                System.out.println("Thread finish stop");
                return;
            }
            if(Race.IsInterrupted){
                System.out.println("Thread interrupted");
                return;
            }
            else{
                button.setLocation(button.getLocation().x + Speed,button.getLocation().y);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }while (true);
    }

    public void Final(){
        JFrame frame = new JFrame("Button races");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pane = frame.getContentPane();

        JButton ar = new JButton(String.format("Number %d Win!",this.ID));
        ar.setFont(new Font("Arial",Font.BOLD,60));
        ar.setContentAreaFilled(false);
        ar.setBorderPainted(false);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        pane.add(ar,c);

        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
