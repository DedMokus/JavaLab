import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlarmClock  {

    private static final int PREF_W = 1280;
    private static final int PREF_H = 720;

    private static int Minutes = 0;
    private static int Seconds = 0;

    static JButton clockSec = new JButton();
    static JButton clockMin = new JButton();
    static JButton colon = new JButton(":");

    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");


    public AlarmClock() {}


    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Alarm Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());


        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        Insets i = new Insets(2,3,2,3);
        c.insets = i;

        clockMin.setFont(new Font("Arial",Font.PLAIN,30));
        clockMin.setContentAreaFilled(false);
        clockMin.setBorderPainted(false);

        clockMin.setText(Integer.toString(Minutes));
        c.gridx = 0;
        c.gridy = 1;
        pane.add(clockMin,c);


        clockSec.setFont(new Font("Arial",Font.PLAIN,30));
        clockSec.setContentAreaFilled(false);
        clockSec.setBorderPainted(false);

        clockSec.setText(Integer.toString(Seconds));
        clockSec.setBounds(100,100,200,100);
        c.gridx = 2;
        c.gridy = 1;
        pane.add(clockSec,c);


        colon.setFont(new Font("Arial",Font.PLAIN,40));
        colon.setContentAreaFilled(false);
        colon.setBorderPainted(false);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(colon,c);

        JButton MinUp = new JButton("+1");
        c.gridx = 0;
        c.gridy = 0;

        MinUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Minutes++;
                clockMin.setText(Integer.toString(Minutes));
            }
        } );

        pane.add(MinUp,c);

        JButton MinDown = new JButton("-1");
        c.gridx = 0;
        c.gridy = 2;
        pane.add(MinDown,c);
        MinDown.addActionListener(e -> {
            if(Minutes>0) {
                Minutes--;
            }
            clockMin.setText(Integer.toString(Minutes));
        });


        JButton SecUp = new JButton("+1");
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(SecUp,c);
        SecUp.addActionListener(e -> {

            Seconds++;
            clockSec.setText(Integer.toString(Seconds));
        });

        JButton SecDown = new JButton("-1");
        c.gridx = 2;
        c.gridy = 2;
        pane.add(SecDown,c);
        SecDown.addActionListener(e -> {
            if(Seconds>0) {
                Seconds--;
            }
            clockSec.setText(Integer.toString(Seconds));
        });

        c.anchor = GridBagConstraints.SOUTH;
        JButton Start = new JButton("Start");
        c.gridy = 4;
        c.gridx = 0;
        pane.add(Start,c);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Thread start begin");
                TimerStart();
            }
        });

        JButton Stop = new JButton("Stop");
        c.gridy = 4;
        c.gridx = 1;
        pane.add(Stop,c);

        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Thread stop");
                TimerStop();
            }
        });

        JButton Reset = new JButton("Reset");
        c.gridy = 4;
        c.gridx = 2;
        pane.add(Reset,c);

        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer reset");
                Minutes = 0;
                Seconds = 0;
                clockSec.setText(Integer.toString(Seconds));
                clockMin.setText(Integer.toString(Minutes));
            }
        });
    }

    static SwingWorker sw1 = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            int secondsall = Minutes*60 + Seconds;
            for(int i = secondsall;i!=0;i--){
                Thread.sleep(1000);
                if(Seconds==0){
                    Seconds = 59;
                    Minutes--;
                }
                else Seconds--;
                publish();

            }
            String res = "Timer done";
            System.out.println("Thread start end");

            JFrame frame = new JFrame("Alarm Clock");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //Set up the content pane.
            Container p = frame.getContentPane();
            JButton ar = new JButton("Timer Is Out!!!");
            ar.setFont(new Font("Arial",Font.BOLD,60));
            ar.setContentAreaFilled(false);
            ar.setBorderPainted(false);
            p.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            p.add(ar,c);

            //Display the window.
            frame.setSize(800,200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            return res;
        }
        @Override
        protected void process(List chunks){
            clockSec.setText(Integer.toString(Seconds));
            clockMin.setText(Integer.toString(Minutes));
        }

    };

    private static void TimerStart(){

        sw1.execute();
    }

    private static void TimerStop(){
        sw1.cancel(true);
    }




    /** Handle the key typed event from the text field. */
/*    public void keyTyped(KeyEvent e) {
        displayInfo(e, "KEY TYPED: ");
    }

    *//** Handle the key pressed event from the text field. *//*
    public void keyPressed(KeyEvent e) {
        displayInfo(e, "KEY PRESSED: ");
    }

    *//** Handle the key released event from the text field. *//*
    public void keyReleased(KeyEvent e) {
        displayInfo(e, "KEY RELEASED: ");
    }*/

    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");

        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }

    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    /*private void displayInfo(KeyEvent e, String keyStatus){

        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }

        int modifiersEx = e.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }

        String actionString = "action key? ";
        if (e.isActionKey()) {
            actionString += "YES";
        } else {
            actionString += "NO";
        }

        String locationString = "key location: ";
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            locationString += "standard";
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
            locationString += "left";
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
            locationString += "right";
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
            locationString += "numpad";
        } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
            locationString += "unknown";
        }

        displayArea.append(keyStatus + newline
                + "    " + keyString + newline
                + "    " + modString + newline
                + "    " + actionString + newline
                + "    " + locationString + newline);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }*/



}

