package picturelanguage;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Concatenator;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Painter {
    private static final int SIZE = 400;

    private ImagePlus image;
    private BufferedImage bufferedImage;

    public Painter(ImagePlus image) {
        this.image = image;
    }

    public Painter(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public static Painter fromFile(String filename) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(filename));
        return new Painter(bufferedImage);
    }

    public static Painter fromNumber(int n) {


        ImagePlus image = IJ.createImage("Tittel", SIZE, SIZE, 1, 24);
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setColor(new Color(n, n, n));
        imageProcessor.fill();
        return new Painter(image);
    }

    public static Painter fromSegments(List<Segment> segments) {
        ImagePlus image = IJ.createImage("Tittel", SIZE, SIZE, 1, 24);
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

    public Painter horizontalFlip() {
        ImagePlus tempImage = image.duplicate();
        ImageProcessor imageProcessor = tempImage.getProcessor();
        imageProcessor.flipHorizontal();
        return new Painter(tempImage);
    }

    public Painter verticalFlip() {
        ImagePlus tempImage = image.duplicate();
        ImageProcessor imageProcessor = tempImage.getProcessor();
        imageProcessor.flipVertical();
        return new Painter(tempImage);
    }

    public Painter beside(Painter otherPainter) {
        ImagePlus leftImage = image.duplicate();
        ImageProcessor leftProcessor = image.getProcessor();
        leftProcessor.scale(0.5,1);

        ImagePlus rightImage = otherPainter.getImage().duplicate();
        ImageProcessor rightProcessor = rightImage.getProcessor();
        rightProcessor.scale(0.5,1);

        Concatenator concatenator = new Concatenator();

        ImagePlus res = concatenator.concatenate(leftImage, rightImage, true);
        return new Painter(res);
    }

    public void show() {
        image.show();
    }

    public ImagePlus getImage() {
        return image;
    }

    private static int resizeToFrameSize(double number) {
        return (int) Math.floor(SIZE * number);
    }

    // TODO: make a copy
    private BufferedImage applyAffineTransform(BufferedImage bImage, AffineTransformOp affineTransformOp) {
        return null;
    }
}
