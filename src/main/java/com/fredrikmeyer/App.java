package com.fredrikmeyer;

import com.fredrikmeyer.picturelanguage.Painter;
import com.fredrikmeyer.picturelanguage.Segment;
import com.fredrikmeyer.picturelanguage.Vector;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        ImagePlus imp = IJ.openImage("./src/main/java/resources/moss.png");
        ImageProcessor ip = imp.getProcessor();
        ip.fill();
        //ip.setColor(Color.BLUE);
        //ip.setLineWidth(4);
        //ip.drawRect(10, 10, imp.getWidth() - 20, imp.getHeight() - 20);
        //imp.show();
        //System.out.println(imp.getBitDepth());
        //ImagePlus imp2 = IJ.createImage("Tittel", 400, 400, 1, 24);
        //imp2.show();

        List<Segment> segments = Segment.segmentsFromFile(new File("./src/main/java/resources/smiley.txt"));
        Painter p =  Painter.fromSegments(segments);
        p.show();

    }
}