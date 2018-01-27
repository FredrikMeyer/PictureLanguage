package picturelanguage;

import picturelanguage.Painter;
import picturelanguage.Segment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        //ip.setColor(Color.BLUE);
        //ip.setLineWidth(4);
        //ip.drawRect(10, 10, imp.getWidth() - 20, imp.getHeight() - 20);
        //imp.show();
        //System.out.println(imp.getBitDepth());
        //ImagePlus imp2 = IJ.createImage("Tittel", 400, 400, 1, 24);
        //imp2.show();

        List<Segment> segments = Segment.segmentsFromFile(new File("./src/main/java/resources/smiley.txt"));
        Painter p =  Painter.fromSegments(segments); //fromFile("./src/main/java/resources/moss.png");
        Painter p2 = Painter.fromFile("./src/main/java/resources/moss.png");
        //p2.show();

        p.beside(p).show();

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list
                .stream()
                .reduce(0, (acc, s) -> acc + s));


    }
}