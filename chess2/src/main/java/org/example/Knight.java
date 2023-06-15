package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Pieces {
    private final char color;
    Knight(char color){
        this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        char pieceColor = board[row][col].color();
        
        ArrayList<Point> moveSet = new ArrayList<>();
        if(Main.inBoard(row +1,col +2)){moveSet.add( new Point(row+1,col+2));}
        if(Main.inBoard(row -1,col +2)){moveSet.add( new Point(row-1,col+2));}
        if(Main.inBoard(row +1,col -2)){moveSet.add( new Point(row+1,col-2));}
        if(Main.inBoard(row -1,col -2)){moveSet.add( new Point(row-1,col-2));}
        if(Main.inBoard(row +2,col +1)){moveSet.add( new Point(row+2,col+1));}
        if(Main.inBoard(row +2,col -1)){moveSet.add( new Point(row+2,col-1));}
        if(Main.inBoard(row -2,col +1)){moveSet.add( new Point(row-2,col+1));}
        if(Main.inBoard(row -2,col -1)){moveSet.add( new Point(row-2,col-1));}


        return checkMoves(board,moveSet,pieceColor);
    }
    public char color() {
        return color;
    }


    public ArrayList<Point> checkMoves(Pieces[][] board, ArrayList<Point> moveSet, char pieceColor){
        ArrayList<Point> legalMoves = new ArrayList<>();
        for (Point point : moveSet) {
            int rowNew = (int) point.getX();
            int colNew = (int) point.getY();

            if (board[rowNew][colNew].color() != pieceColor) {
                legalMoves.add(point);
            }
        }
        return legalMoves;
    }
}
