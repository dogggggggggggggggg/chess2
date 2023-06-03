package org.example;

public class Board {
    public int colToNum(String col){
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