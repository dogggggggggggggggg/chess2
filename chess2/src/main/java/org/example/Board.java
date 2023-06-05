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

    ImageIcon rookBlack = new ImageIcon(ClassLoader.getSystemResource("Rook_B.png"));
    ImageIcon rookWhite = new ImageIcon(ClassLoader.getSystemResource("Rook_W.png"));
    ImageIcon bishopBlack = new ImageIcon(ClassLoader.getSystemResource("Bishop_B.png"));
    ImageIcon bishopWhite = new ImageIcon(ClassLoader.getSystemResource("Bishop_W.png"));
    ImageIcon kingBlack = new ImageIcon(ClassLoader.getSystemResource("King_B.png"));
    ImageIcon kingWhite = new ImageIcon(ClassLoader.getSystemResource("King_W.png"));
    ImageIcon knightBlack = new ImageIcon(ClassLoader.getSystemResource("Knight_B.png"));
    ImageIcon knightWhite = new ImageIcon(ClassLoader.getSystemResource("Knight_W.png"));
    ImageIcon pawnBlack = new ImageIcon(ClassLoader.getSystemResource("Pawn_B.png"));
    ImageIcon pawnWhite = new ImageIcon(ClassLoader.getSystemResource("Pawn_W.png"));
    ImageIcon queenBlack = new ImageIcon(ClassLoader.getSystemResource("Queen_B.png"));
    ImageIcon queenWhite = new ImageIcon(ClassLoader.getSystemResource("Queen_W.png"));
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
                               field.setIcon(rookWhite);
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
                               field.setIcon(knightWhite);
                           }
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('W');
                               field.setIcon(bishopWhite);
                           }
                           case 3 -> {
                               boardArray[row][col] = new Queen('W');
                               field.setIcon(queenWhite);
                           }
                           case 4 -> {
                               boardArray[row][col] = new King('W');
                               field.setIcon(kingWhite);
                           }
                           default -> {
                               boardArray[row][col] = new None('N');
                               label.setText("Empty");
                           }
                       }
                       break;
                   case 1:
                       boardArray[row][col] = new Pawn('W');
                       field.setIcon(pawnWhite);
                       break;
                   case 6:
                       boardArray[row][col] = new Pawn('B');
                       field.setIcon(pawnBlack);
                       break;
                   case 7:
                       switch (row) {
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('B');
                               field.setIcon(rookBlack);
                           }
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('B');
                               field.setIcon(knightBlack);
                           }
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('B');
                               field.setIcon(bishopBlack);
                           }
                           case 3 -> {
                               boardArray[row][col] = new Queen('B');
                               field.setIcon(queenBlack);
                           }
                           case 4 -> {
                               boardArray[row][col] = new King('B');
                               field.setIcon(kingBlack);
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

                if (boardArray[row][col].getColor() == 'W') {
                    switch (boardArray[row][col].getClass().getName().substring(12)) {
                        case "Pawn" -> field.setIcon(pawnWhite);
                        case "Knight" -> {
                            field.setIcon(knightWhite);
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
                        }
                        case "Bishop" -> field.setIcon(bishopWhite);
                        case "Queen" -> field.setIcon(queenWhite);
                        case "King" -> field.setIcon(kingWhite);
                        case "Rook" -> field.setIcon(rookWhite);
                        default -> {
                        }
                    }
                } else if (boardArray[row][col].getColor() == 'B') {
                    switch (boardArray[row][col].getClass().getName().substring(12)) {
                        case "Pawn" -> field.setIcon(pawnBlack);
                        case "Knight" -> {
                            field.setIcon(knightBlack);
                            field.addActionListener(e -> {
                                int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                ArrayList<Point> selectedMoveset = boardArray[row2][col2].move(field.getName());
                                for (Point point : selectedMoveset) {
                                    int rowNew = (int) point.getX();
                                    int colNew = (int) point.getY();
                                    refresh();
                                    if (boardArray[rowNew][colNew].getColor() == 'N' || boardArray[rowNew][colNew].getColor() == 'W') {
                                        boardArray[rowNew][colNew] = new Knight('B');
                                    }

                                }
                            });
                        }
                        case "Bishop" -> field.setIcon(bishopBlack);
                        case "Queen" -> field.setIcon(queenBlack);
                        case "King" -> field.setIcon(kingBlack);
                        case "Rook" -> field.setIcon(rookBlack);
                        default -> {
                        }
                    }
                }


                field.setName(row + "" + col);
                if (temp % 2 == 0) {
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }

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