package main;

import java.awt.Graphics2D;
import java.awt.*;

public class Board {
    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int SQUARE_HALF = SQUARE_SIZE / 2;

    public void draw(Graphics2D g2){
        int clr = 0;

        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                    if (clr == 0) {
                        g2.setColor(new Color(165, 165, 255));
                        clr = 1;
                    }
                    else {
                        g2.setColor(new Color(250, 255, 165));
                        clr = 0;
                    }

                g2.fillRect (col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
            if (clr == 0) {
                clr = 1;
            }
            else {
                clr = 0;
            }

        }
    }
}
