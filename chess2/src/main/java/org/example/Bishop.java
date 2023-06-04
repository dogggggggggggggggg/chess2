package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Pieces {
    private char color;
    Bishop(char color){
        this.color = color;
    }
    public ArrayList<Point> move(String location){
        if (color == 'W'){

        } else {

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
