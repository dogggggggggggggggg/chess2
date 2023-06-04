package org.example;

import java.awt.*;
import java.util.ArrayList;

public class None implements Pieces{
    private char color;
    public None(char color){
        this.color = 'N';
    }

    public ArrayList<Point> move(String location) {
        return null;
    }

    public char getColor() {
        return color;
    }


}
