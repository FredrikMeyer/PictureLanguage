package picturelanguage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Painter {
    private static Logger log = LoggerFactory.getLogger(Painter.class);
    private static final int SIZE = 400;

    private BufferedImage bufferedImage;

    public Painter(BufferedImage bufferedImage) {
        this.bufferedImage = copyImage(bufferedImage);
    }

    public static Painter fromFile(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        return new Painter(bufferedImage);
    }

    public static Painter fromNumber(int n) {
        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        img.createGraphics();
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(Color.YELLOW);
        g.drawString("www.tutorialspoint.com", 20, 20);
        g.fillRect(0, 0, SIZE / 2, SIZE);

        g.dispose();

        return new Painter(copyImage(img));
    }

    public static Painter fromSegments(List<Segment> segments) {
        BufferedImage bImage = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        bImage.createGraphics();
        Graphics2D g = (Graphics2D) bImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setStroke(new BasicStroke(8));
        g.setColor(Color.BLACK);

        segments.forEach(segment -> {
            Vector start = segment.getStart();
            Vector end = segment.getEnd();
            log.info("Drawing vector from {} to {}.", start, end);
            g.draw(new Line2D.Double(start.xCoordinate() * SIZE, SIZE-start.yCoordinate() * SIZE,
                    end.xCoordinate() * SIZE, SIZE- end.yCoordinate() * SIZE));
        });

        return new Painter(bImage);
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
        g.drawImage(copyImage(bufferedImage), 0, 0, width / 2, height, null);
        g.drawImage(copyImage(otherPainter.getImage()), width / 2, 0, width / 2, height, null);
        return new Painter(newImg);
    }

    public Painter above(Painter otherPainter) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(copyImage(bufferedImage), 0, 0, width, height / 2, null);
        g.drawImage(copyImage(otherPainter.getImage()), 0, height / 2, width, height / 2, null);
        return new Painter(newImg);
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }

    public void displayImage(String title) {
        JLabel jLabel = new JLabel(new ImageIcon(bufferedImage));
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
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
