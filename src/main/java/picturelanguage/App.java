package picturelanguage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {

    public static void main(String[] args) throws Exception {

        //ip.setColor(Color.BLUE);
        //ip.setLineWidth(4);
        //ip.drawRect(10, 10, imp.getWidth() - 20, imp.getHeight() - 20);
        //imp.show();
        //System.out.println(imp.getBitDepth());
        //ImagePlus imp2 = IJ.createImage("Tittel", 400, 400, 1, 24);
        //imp2.show();

        File file = fromResourceFolder("smiley.txt");
        List<Segment> segments = Segment.segmentsFromFile(file);
        Painter p =  Painter.fromSegments(segments); //fromFile("./src/main/java/resources/moss.png");
        Painter p2 = Painter.fromFile(fromResourceFolder("picturelanguage/moss.png"));
        //p2.show();

        p.beside(p).show();

    }

    private static File fromResourceFolder(String filename) {
        System.out.println(System.getProperty("user.dir"));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
    }
}