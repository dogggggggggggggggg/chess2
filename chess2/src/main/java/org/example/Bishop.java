package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Pieces {
    private final char color;
    Bishop(char color){
        this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        return null;
    }
    public char color() {
        return color;
    }


}
