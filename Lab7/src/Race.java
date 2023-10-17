import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Race {

    public static int START_POINT = 20;
    public volatile static boolean IsFinish = false;
    public volatile static boolean IsInterrupted = false;

    static JFrame frame = new JFrame("Button races");
    static Container pane = frame.getContentPane();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        UIManager.put("swing.boldMetal", Boolean.FALSE);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            addComponentsToPane(pane);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    private static void addComponentsToPane(Container pane) throws InterruptedException {

        pane.setLayout(null);

        RaceOn(pane);
        RestartButton(pane);
        /*List<Car> cars = new ArrayList<>();
        Random random = new Random();


        for (int i = 0; i < 5; i++) {
            int finalI = i;
            int speed = random.nextInt(1, 5);
            Color col = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            JButton button = new JButton(Integer.toString(finalI));
            button.setBounds(START_POINT, 20 + finalI * 100, 50, 50);
            button.setBackground(col);
            pane.add(button);

            cars.add(new Car(speed, finalI, col, button));
        }
        for (Car car : cars) {
            car.start();
        }*/

        /*JButton SecDown = new JButton("Restart");
        SecDown.setBounds(800,700,200,50);
        pane.add(SecDown);
        SecDown.addActionListener(e -> {
            RaceOn(pane);
        });*/

    }

    public static void RaceOn(Container pane){
        List<Car> cars = new ArrayList<>();
        List<JButton> buttons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            int speed = random.nextInt(1, 5);
            Color col = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            JButton button = new JButton(Integer.toString(finalI));
            button.setBounds(START_POINT, 20 + finalI * 100, 50, 50);
            button.setBackground(col);
            pane.add(button);

            cars.add(new Car(speed, finalI, col, button));
        }
        for (Car car : cars) {
            car.start();
        }
    }

    public static void RestartButton(Container pane){
        JButton SecDown = new JButton("Restart");
        SecDown.setBounds(800,700,200,50);
        pane.add(SecDown);
        SecDown.addActionListener(e -> {
            IsInterrupted = true;
            pane.removeAll();
            pane.repaint();
            RestartButton(pane);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            IsInterrupted = false;
            RaceOn(pane);
        });
    }
}
