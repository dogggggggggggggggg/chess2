package org.example;

import java.awt.*;
import java.util.ArrayList;

public interface Pieces {
    ArrayList<Point> move(String location);
    char getColor();
}
