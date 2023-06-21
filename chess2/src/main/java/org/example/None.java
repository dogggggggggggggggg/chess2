package org.example;

import java.awt.*;
import java.util.ArrayList;

public record None(char color) implements Pieces {
    public ArrayList<Point> move(Point location, Pieces[][] board) {
        return null;
    }
    public char[] onMove(Point location, Pieces[][] board){
        char[] returnChar = new char[1];
        returnChar[0] = 'N' ;
        return returnChar;}
}
