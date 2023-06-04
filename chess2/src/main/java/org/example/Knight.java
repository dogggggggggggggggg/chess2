package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Pieces {
    private char color;
    Knight(char color){
        this.color = color;
    }
    public ArrayList<Point> move(String location){
        int row = Integer.parseInt(location.substring(0,1));
        int col = Integer.parseInt(location.substring(1,2));
        
        ArrayList<Point> moveset = new ArrayList<>();
        if(((row + 1) <= 7) && ((col + 2) <= 7)){moveset.add( new Point(row+1,col+2));}
        if(((row - 1) >= 0) && ((col + 2) <= 7)){moveset.add( new Point(row-1,col+2));}
        if(((row + 1) <= 7) && ((col - 2) >= 0)){moveset.add( new Point(row+1,col-2));}
        if(((row - 1) >= 0) && ((col - 2) >= 0)){moveset.add( new Point(row-1,col-2));}
        if(((row + 2) <= 7) && ((col + 1) <= 7)){moveset.add( new Point(row+2,col+1));}
        if(((row + 2) <= 7) && ((col - 1) >= 0)){moveset.add( new Point(row+2,col-1));}
        if(((row - 2) >= 0) && ((col + 1) <= 7)){moveset.add( new Point(row-2,col+1));}
        if(((row - 2) >= 0) && ((col - 1) >= 0)){moveset.add( new Point(row-2,col-1));}
        return moveset;
    }
    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
