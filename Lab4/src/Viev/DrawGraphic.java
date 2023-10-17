package Viev;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawGraphic extends JPanel {
    private static final int MAX_SCORE = 5000;
    private static final int PREF_W = 1200;
    private static final int PREF_H = 1000;
    private static final int BORDER_GAP = 30;
    private Color GRAPH_COLOR;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 10;
    private List<Double> scores;

    public DrawGraphic(List<Double> scores,Color color) {
        this.scores = scores;
        this.GRAPH_COLOR = color;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (5);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < 5; i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        List<Point> graphPoints2 = new ArrayList<Point>();
        for (int i = 0; i < 5; i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i+5)) * yScale + BORDER_GAP);
            graphPoints2.add(new Point(x1, y1));
        }

        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawString(Integer.toString(getHeight()-y1),x0-25,y0);
            g2.drawLine(x0, y0, x1, y1);
        }

        g2.drawString(Integer.toString(10), BORDER_GAP,getHeight() - BORDER_GAP+15);
        for (int i = 0; i < 5; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (5) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawString(Integer.toString((int) Math.pow(10,i+2)),x0,y0+15);
            g2.drawLine(x0, y0, x1, y1);
        }

        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size()-1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        for (int i = 0; i < graphPoints2.size()-1; i++) {
            int x1 = graphPoints2.get(i).x;
            int y1 = graphPoints2.get(i).y;
            int x2 = graphPoints2.get(i + 1).x;
            int y2 = graphPoints2.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public static void createAndShowGui(List<Double> scores,Color color,String title) {
        DrawGraphic mainPanel = new DrawGraphic(scores,color);

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

}
