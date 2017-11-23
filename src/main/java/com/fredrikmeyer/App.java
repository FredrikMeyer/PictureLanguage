package com.fredrikmeyer;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fredrikmeyer.picturelanguage.*;

public class App {

    public static void main(String[] args) throws Exception {
        File imageFile = new File("./src/resources/moss.png");
        ArrayList<Segment> segments = new ArrayList<Segment>();
        //segments = segmentListFromFile(new File("./src/resources/smiley.txt"));
        //Painter p = new Painter(Frame.IDENTITY,  segments);
        //Painter p = new Painter(new Frame(Vector.ORIGIN, new Vector(0.5, 0), new Vector(0.5,1)), imageFile);
        //Painter p = new Painter(Frame.IDENTITY, imageFile);
        //BufferedImage image = p.getImage();

        App gui = new App();
        System.out.println( System.getProperty("user.dir"));
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel drawPanel = new DrawPanel("./src/main/java/resources/moss.png");

        frame.getContentPane().add(drawPanel);
        frame.setSize(drawPanel.getWidth(), drawPanel.getHeight());
        frame.setVisible(true);
    }

    public static ArrayList<Segment> segmentListFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Segment> segmentList = new ArrayList<>();
        while (scanner.hasNext()) {
            double x1 = Double.valueOf(scanner.next());
            double y1 = Double.valueOf(scanner.next());
            double x2 = Double.valueOf(scanner.next());
            double y2 = Double.valueOf(scanner.next());
            Vector start = new Vector(x1, y1);
            Vector end = new Vector(x2, y2);
            segmentList.add(new Segment(start, end));
        }
        return segmentList;
    }


    // TODO la painter-klassen returnere et JPanel, som tegnes i hovedklassen
    // også: lær maven, for nå skjønner jeg ikke stort...
    class DrawPanel extends JPanel {
        private BufferedImage bufferedImage;

        public DrawPanel(String filename) {
            loadImageFile(filename);
        }

        private void loadImageFile(String filename) {
            try {
                File imagefile = new File(filename);
                bufferedImage = ImageIO.read(imagefile);
            } catch (IOException e) {
                System.out.println("Could not find file.");
            }
        }

        public void paintComponent(Graphics g) {
            g.drawImage(bufferedImage, 0, 0, this);
        }

        public int getWidth() {
            return bufferedImage.getWidth();
        }

        public int getHeight() {
            return bufferedImage.getHeight();
        }
    }
}
