package org.example;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class Board {

    JPanel boardPanel = new JPanel();
    JFrame windowFrame = new JFrame();
    Pieces[][] boardArray = new Pieces[8][8];
    Color brown = new Color(193, 154, 107);
    int temp;
    public Board() {
        boardPanel.setLayout(new GridLayout(8,8));


        for(int col = 0; col < 8; col++, temp++){
            for(int row = 0; row <8; row++, temp++) {
                JButton field = new JButton();
                JLabel label= new JLabel();
                field.setName(row + "" + col);
                if (temp % 2 == 0){
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }

                field.setBorderPainted(false);
                field.add(label);
                field.setPreferredSize(new Dimension(64,64));
                boardPanel.add(field);
               switch (col){
                   case 0:
                       switch (row) {
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('W');
                               label.setText("Rook");
                           }
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('W');
                               field.addActionListener(e -> {
                                   int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                   int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                   ArrayList<Point> selectedMoveset = boardArray[row2][col2].move(field.getName());
                                   for (Point point : selectedMoveset) {
                                       int rowNew = (int) point.getX();
                                       int colNew = (int) point.getY();
                                       refresh();
                                       if (boardArray[rowNew][colNew].getColor() == 'N' || boardArray[rowNew][colNew].getColor() == 'B') {
                                           boardArray[rowNew][colNew] = new Knight('W');
                                       }

                                   }

                               });
                               label.setText("Knight");
                           }
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('W');
                               label.setText("Bishop");
                           }
                           case 3 -> {
                               boardArray[row][col] = new Queen('W');
                               label.setText("Queen");
                           }
                           case 4 -> {
                               boardArray[row][col] = new King('W');
                               label.setText("King");
                           }
                           default -> {
                               boardArray[row][col] = new None('N');
                               label.setText("Empty");
                           }
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
                       switch (row) {
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('B');
                               label.setText("Rook");
                           }
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('B');
                               label.setText("Knight");
                           }
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('B');
                               label.setText("Bishop");
                           }
                           case 3 -> {
                               boardArray[row][col] = new Queen('B');
                               label.setText("Queen");
                           }
                           case 4 -> {
                               boardArray[row][col] = new King('B');
                               label.setText("King");
                           }
                           default -> {
                               boardArray[row][col] = new None('N');
                               label.setText("Empty");
                           }
                       }
                       break;
                   default:
                       boardArray[row][col] = new None('N');
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

    private void refresh(){
        Component[] componentList = boardPanel.getComponents();
        for (Component current : componentList){
            boardPanel.remove(current);
        }
        for(int col = 0; col < 8; col++, temp++) {
            for (int row = 0; row < 8; row++, temp++) {
                JButton field = new JButton();
                field.setBorderPainted(false);
                JLabel label = new JLabel(boardArray[row][col].getClass().getName().substring(12));
                switch (label.getText()){
                    case "Pawn":
                        break;
                    case "Knight":
                      field.addActionListener(e -> {
                          int row2 = Integer.parseInt(field.getName().substring(0,1));
                          int col2 = Integer.parseInt(field.getName().substring(1,2));
                          ArrayList<Point> selectedMoveset = boardArray[row2][col2].move(field.getName());
                          for (Point point : selectedMoveset) {
                              int rowNew = (int) point.getX();
                              int colNew = (int) point.getY();
                              refresh();
                              if (boardArray[rowNew][colNew].getColor() == 'N' || boardArray[rowNew][colNew].getColor() == 'B') {
                                  boardArray[rowNew][colNew] = new Knight('W');
                              }

                          }
                      });
                        break;
                    case "Bishop":
                        break;
                    case "Queen":
                        break;
                    case "King":
                        break;
                    default:
                        break;
                }
                field.setName(row + "" + col);
                if (temp % 2 == 0) {
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }

                field.add(label);
                field.setPreferredSize(new Dimension(64,64));
                boardPanel.add(field);
            }
        }
        SwingUtilities.updateComponentTreeUI(boardPanel);
    }


}


/*
swing 8x8 feld
jedes feld ein button
button hat arrlist aus feld + figur
feld hat ein array wert z.B int[x][y]
array für jede figur  z.B String[farbe][figur]
figuren bewegen sich indem man + oder - im array macht z.b Bauer + 2x

 */
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