package pieces;

import main.GamePanel;

public class queens extends piece{
    public queens(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-queen");
        }
        else {
            image = getImage("/pieces/b-queen");
        }



    }
}
