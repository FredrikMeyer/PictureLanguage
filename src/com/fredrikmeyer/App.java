package com.fredrikmeyer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

import com.fredrikmeyer.picturelanguage.*;
import com.fredrikmeyer.picturelanguage.Frame;
import com.fredrikmeyer.picturelanguage.Painter;

public class App extends Component {

    public static void main(String[] args) throws Exception {
        JFrame frame = buildFrame();

        //Painter p = new Painter(null, 255);
        //File imageFile = new File("./src/resources/moss.png");
        Segment s1 = new Segment(new Vector(0, 0), new Vector(1,1));
        Segment s2 = new Segment(new Vector(0, 1), new Vector(1,0));
        ArrayList<Segment> segments = new ArrayList<Segment>();
        segments = segmentListFromFile(new File("./src/resources/smiley.txt"));
        Painter p = new Painter(Frame.IDENTITY,  segments);
        BufferedImage image = p.getImage();

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics g2 = g.create();
                g2.drawImage(image, 0, 0, null);
                g2.dispose();
            }
        };

        frame.add(pane);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }


    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
        return frame;
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


}
