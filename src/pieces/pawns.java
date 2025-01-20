package pieces;

import main.GamePanel;

public class pawns extends piece {
    public pawns(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-pawn");
        }
        else {
            image = getImage("/pieces/b-pawn");
        }



    }
}
