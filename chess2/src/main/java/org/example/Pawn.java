package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Pawn implements Pieces  {
    private char color;

    Pawn(char color){
    this.color = color;

    }
    public ArrayList<Point> move(String location){
        int col = Integer.parseInt(location.substring(0,1));
        int row = Integer.parseInt(location.substring(1,2));
        if (color == 'W'){
            col += 2;

        } else {
            col -= 2;
        }
        return null;
    }
    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
