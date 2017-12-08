package com.fredrikmeyer.picturelanguage;

public class Vector {
    private double x;
    private double y;

    public static Vector ORIGIN = new Vector(0,0);
    public static Vector E1 = new Vector(1,0);
    public static Vector E2 = new Vector(0,1);

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double xCoordinate() {
        return x;
    }

    public double yCoordinate() {
        return y;
    }

    public static Vector add(Vector v, Vector w) {
        return new Vector(v.xCoordinate() + w.xCoordinate(),
                v.yCoordinate() + w.yCoordinate());
    }

    public static Vector sub(Vector v, Vector w) {
        return Vector.add(v, Vector.scale(-1,w));
    }

    public static Vector scale(double s, Vector v) {
        return new Vector(s * v.xCoordinate(), s * v.yCoordinate());
    }

}