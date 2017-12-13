package com.fredrikmeyer.picturelanguage;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.util.List;


public class Painter {
    public static final int SIZE = 400;

    private ImagePlus image;

    Painter(ImagePlus image) {
        this.image = image;
    }

    public static Painter fromFile(String filename) {
        ImagePlus image = IJ.openImage(filename);
        return new Painter(image);
    }

    public static Painter fromNumber(int n) {
        ImagePlus image = IJ.createImage("Tittel", 400, 400, 1, 24);
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setColor(new Color(n, n, n));
        imageProcessor.fill();
        return new Painter(image);
    }

    public static Painter fromSegments(List<Segment> segments) {
        ImagePlus image = IJ.createImage("Tittel", 400, 400, 1, 24);
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setColor(Color.WHITE);
        imageProcessor.fill();
        imageProcessor.setColor(Color.BLACK);

        segments.forEach( seg -> {
            int startX = resizeToFrameSize(seg.getStart().xCoordinate());
            int startY = 400-resizeToFrameSize(seg.getStart().yCoordinate());
            int endX = resizeToFrameSize(seg.getEnd().xCoordinate());
            int endY = 400-resizeToFrameSize(seg.getEnd().yCoordinate());
            imageProcessor.drawLine(startX, startY, endX, endY);
        });

        return new Painter(image);
    }

    public void show() {
        image.show();
    }

    private static int resizeToFrameSize(double number) {
        return (int) Math.floor(SIZE * number);
    }
}
