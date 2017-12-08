package com.fredrikmeyer.picturelanguage;

public class Segment {
    private Vector start;
    private Vector end;

    public Segment(Vector start, Vector end) {
        this.start = start;
        this.end = end;
    }
    public Vector getStart() {
        return start;
    }

    public Vector getEnd() {
        return end;
    }
}
