package com.fredrikmeyer.picturelanguage;

import javafx.scene.transform.Affine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Painter {
    private static final int SIZE = 400;

    private Frame frame;
    private BufferedImage bufferedImage;
    private Graphics2D graphics;
    private Dimension dimension;

    private Painter(Frame frame) {
        this.frame = frame;
        dimension = new Dimension(SIZE,SIZE);
    }

    public Painter(Frame frame, int n) {
        this(frame);
        bufferedImage= new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.createGraphics();
        graphics.setColor(new Color(n, n, n));
        graphics.fillRect(0, 0, SIZE, SIZE);
    }

    public Painter(Frame frame, List<Segment> segments) throws Exception {
        this(frame);
        bufferedImage= new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.fillRect(0, 0, SIZE, SIZE);
        graphics.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(1);
        graphics.setStroke(bs);

        graphics.transform(affineTransformationFromFrame(frame));
        segments.forEach( segment -> drawLine(graphics, segment));

    }

    public Painter(Frame frame, File file) throws IOException {
        this(frame);
        bufferedImage = cropImage(ImageIO.read(file));
        graphics = bufferedImage.createGraphics();
    }

    private BufferedImage cropImage(BufferedImage bufferedImage) {
        return bufferedImage.getSubimage(0, 0, SIZE, SIZE);
    }

    private void drawLine(Graphics g, Segment s) {
        int startX = Math.round(SIZE * (float) s.getStart().getXCoordinate());
        int startY = Math.round(SIZE * (float) s.getStart().getYCoordinate());
        int endX = Math.round(SIZE * (float) s.getEnd().getXCoordinate());
        int endY = Math.round(SIZE * (float)  s.getEnd().getYCoordinate());
        g.drawLine(startX, startY, endX, endY);
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }

    public AffineTransform affineTransformationFromFrame(Frame frame) {
        Vector origin = frame.getOrigin();
        Vector edge1 = frame.getEdge1();
        Vector edge2 = frame.getEdge2();

        AffineTransform tform = AffineTransform.getTranslateInstance(0, SIZE);
        tform.scale(1, -1);
        AffineTransform transformation = new AffineTransform(edge1.getXCoordinate(), edge1.getYCoordinate(),
                edge2.getXCoordinate(), edge2.getYCoordinate(),
                origin.getXCoordinate()*SIZE, origin.getYCoordinate()*SIZE);

        tform.concatenate(transformation);
        return tform;

    }

}
