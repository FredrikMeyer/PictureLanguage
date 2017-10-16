package com.fredrikmeyer.picturelanguage;


public class Frame {
    private Vector origin;
    private Vector edge1;
    private Vector edge2;

    public static Frame IDENTITY = new Frame(Vector.ORIGIN, Vector.E1, Vector.E2);

    public Frame(Vector origin, Vector edge1, Vector edge2) {
        this.origin = origin;
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    public Vector getOrigin() {
        return origin;
    }

    public Vector getEdge1() {
        return edge1;
    }

    public Vector getEdge2() {
        return edge2;
    }

    public Vector coordinateMap(Vector v) {
        return Vector.add( Vector.scale( v.getXCoordinate(), edge1),
                Vector.scale( v.getYCoordinate(), edge2));
    }
}
