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
        
        ArrayList<Point> moveset = new ArrayList<>();
        if(((row + 1) <= 7) && ((col + 2) <= 7)){moveset.add( new Point(row+1,col+2));}
        if(((row - 1) >= 0) && ((col + 2) <= 7)){moveset.add( new Point(row-1,col+2));}
        if(((row + 1) <= 7) && ((col - 2) >= 0)){moveset.add( new Point(row+1,col-2));}
        if(((row - 1) >= 0) && ((col - 2) >= 0)){moveset.add( new Point(row-1,col-2));}
        if(((row + 2) <= 7) && ((col + 1) <= 7)){moveset.add( new Point(row+2,col+1));}
        if(((row + 2) <= 7) && ((col - 1) >= 0)){moveset.add( new Point(row+2,col-1));}
        if(((row - 2) >= 0) && ((col + 1) <= 7)){moveset.add( new Point(row-2,col+1));}
        if(((row - 2) >= 0) && ((col - 1) >= 0)){moveset.add( new Point(row-2,col-1));}


        return checkMoves(board,moveset,pieceColor);
    }
    public char color() {
        return color;
    }


    public ArrayList<Point> checkMoves(Pieces[][] board, ArrayList<Point> moveset, char pieceColor){
        ArrayList<Point> legalMoves = new ArrayList<>();
        for (Point point : moveset) {
            int rowNew = (int) point.getX();
            int colNew = (int) point.getY();

            if (board[rowNew][colNew].color() != pieceColor) {
                legalMoves.add(point);
            }
        }
        return legalMoves;
    }
}
