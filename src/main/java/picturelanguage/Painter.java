package picturelanguage;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Concatenator;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Painter {
    private static final int SIZE = 400;

    private BufferedImage bufferedImage;

    public Painter(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public static Painter fromFile(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        return new Painter(bufferedImage);
    }

    public static Painter fromNumber(int n) {
        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        img.createGraphics();
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(n, n, n));
        g.fillRect(0, 0, SIZE, SIZE);

        return new Painter(img);
    }

    public static Painter fromSegments(List<Segment> segments) {
        ImagePlus image = IJ.createImage("Tittel", SIZE, SIZE, 1, 24);
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setColor(Color.WHITE);
        imageProcessor.fill();
        imageProcessor.setColor(Color.BLACK);

        segments.forEach(seg -> {
            int startX = resizeToFrameSize(seg.getStart().xCoordinate());
            int startY = 400 - resizeToFrameSize(seg.getStart().yCoordinate());
            int endX = resizeToFrameSize(seg.getEnd().xCoordinate());
            int endY = 400 - resizeToFrameSize(seg.getEnd().yCoordinate());
            imageProcessor.drawLine(startX, startY, endX, endY);
        });

        return null;
    }

    public Painter horizontalFlip() {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(copyImage(bufferedImage), 0, 0, width, height, width, 0, 0, height, null);
        return new Painter(newImg);
    }

    public Painter verticalFlip() {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(copyImage(bufferedImage), 0, 0, width, height, 0, height, width, 0, null);
        return new Painter(newImg);
    }

    public Painter beside(Painter otherPainter) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(copyImage(bufferedImage), 0, 0, width/2, height, null);
        g.drawImage(copyImage(otherPainter.getImage()), width/2, 0, width/2, height, null);
        return new Painter(newImg);
    }

    public Painter above(Painter otherPainter) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(copyImage(bufferedImage), 0, 0, width, height/2, null);
        g.drawImage(copyImage(otherPainter.getImage()), 0, height/2, width, height/2, null);
        return new Painter(newImg);
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }

    public void displayImage(String title) {
        JFrame frame = new JFrame(title);
        frame.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        frame.setVisible(true);
        Graphics2D g = (Graphics2D) frame.getRootPane().getGraphics();
        g.drawImage(bufferedImage, null, 0, 0);
    }

    private static int resizeToFrameSize(double number) {
        return (int) Math.floor(SIZE * number);
    }

    private BufferedImage applyAffineTransform(BufferedImage bImage, AffineTransformOp affineTransformOp) {
        return null;
    }

    private static BufferedImage copyImage(BufferedImage source) {
        ColorModel cm = source.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = source.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
