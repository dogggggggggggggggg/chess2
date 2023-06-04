package org.example;

import javax.swing.*;
import java.awt.*;

public class Board {

    JPanel boardPanel = new JPanel();
    JFrame windowFrame = new JFrame();
    JButton field = new JButton();
    Pieces[][] boardArray = new Pieces[8][8];
    Color brown = new Color(193, 154, 107);
    int temp;
    public Board() {
        boardPanel.setLayout(new GridLayout(8,8));


        for(int col = 0; col < 8; col++, temp++){
            for(int row = 0; row <8; row++, temp++) {
                JButton field = new JButton();
                JLabel label= new JLabel();
                field.setName(col + "" + row);
                if (temp % 2 == 0){
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }


                field.add(label);
                field.setPreferredSize(new Dimension(64,64));
                boardPanel.add(field);
               switch (col){
                   case 0:
                        switch (row){
                            case 0,7:
                                boardArray[row][col] = new Rook('W');
                                label.setText("Rook");
                                break;
                            case 1,6:
                                boardArray[row][col] = new Knight('W');
                                label.setText("Knight");
                                break;
                            case 2,5:
                                boardArray[row][col] = new Bishop('W');
                                label.setText("Bishop");
                                break;
                            case 3:
                                boardArray[row][col] = new Queen('W');
                                label.setText("Queen");
                                break;
                            case 4:
                                boardArray[row][col] = new King('W');
                                label.setText("King");
                                break;
                            default:
                                label.setText("Empty");
                                break;

                        }
                       break;
                   case 1:
                       boardArray[row][col] = new Pawn('W');
                       label.setText("Pawn");
                       break;
                   case 6:
                       boardArray[row][col] = new Pawn('B');
                       label.setText("Pawn");
                       break;
                   case 7:
                       switch (row){
                           case 0,7:
                               boardArray[row][col] = new Rook('B');
                               label.setText("Rook");
                               break;
                           case 1,6:
                               boardArray[row][col] = new Knight('B');
                               label.setText("Knight");
                               break;
                           case 2,5:
                               boardArray[row][col] = new Bishop('B');
                               label.setText("Bishop");
                               break;
                           case 3:
                               boardArray[row][col] = new Queen('B');
                               label.setText("Queen");
                               break;
                           case 4:
                               boardArray[row][col] = new King('B');
                               label.setText("King");
                               break;
                           default:
                               label.setText("Empty");
                               break;

                       }
                       break;
                   default:
                       label.setText("Empty");
                       break;
               }
            }
        }



        windowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowFrame.add(boardPanel);
        windowFrame.setSize(1024,1024);
        windowFrame.setVisible(true);
    }
    /*public int colToNum(String col){
        return switch (col) {
            case "a" -> (1);
            case "b" -> (2);
            case "c" -> (3);
            case "d" -> (4);
            case "e" -> (5);
            case "f" -> (6);
            case "g" -> (7);
            case "h" -> (8);
            default -> 0;
        };
    }*/
}


/*
swing 8x8 feld
jedes feld ein button
button hat arrlist aus feld + figur
feld hat ein array wert z.B int[x][y]
array für jede figur  z.B String[farbe][figur]
figuren bewegen sich indem man + oder - im array macht z.b Bauer + 2x

 */