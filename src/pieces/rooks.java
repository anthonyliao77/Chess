package pieces;

import main.GamePanel;

public class rooks extends piece{
    public rooks(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-rook");
        }
        else {
            image = getImage("/pieces/b-rook");
        }



    }
}
