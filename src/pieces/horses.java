package pieces;

import main.GamePanel;

public class horses extends piece{
    public horses(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-horse");
        }
        else {
            image = getImage("/pieces/b-horse");
        }



    }
}
