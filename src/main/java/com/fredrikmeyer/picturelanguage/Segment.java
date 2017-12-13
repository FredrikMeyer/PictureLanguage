package com.fredrikmeyer.picturelanguage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Segment {
    private Vector start;
    private Vector end;

    public Segment(Vector start, Vector end) {
        this.start = start;
        this.end = end;
    }
    public Vector getStart() {
        return start;
    }

    public Vector getEnd() {
        return end;
    }

    public static ArrayList<Segment> segmentsFromFile(File file) throws FileNotFoundException {
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
