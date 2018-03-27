package picturelanguage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class App {

    public static void main(String[] args) throws Exception {

        File file = fileFromResourceFolder("picturelanguage/smiley.txt");
        List<Segment> segments = Segment.segmentsFromFile(file);
        //Painter p =  Painter.fromSegments(segments); //fromFile("./src/main/java/resources/moss.png");
        Painter p2 = Painter.fromFile(fileFromResourceFolder("picturelanguage/moss.png"));
        Painter hFlipped = p2.horizontalFlip();
        Painter vFlipped = p2.verticalFlip();

        Painter besides = p2.beside(hFlipped);
        besides.verticalFlip().above(besides).displayImage(":)");
    }

    private static File fileFromResourceFolder(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
    }
}