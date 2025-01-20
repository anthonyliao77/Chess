package pieces;

import main.GamePanel;

public class kings extends piece{
    public kings(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-king");
        }
        else {
            image = getImage("/pieces/b-king");
        }
    }

    public boolean canMove (int targetCol, int targetRow) {
        if (isInsideBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - prevCol) + Math.abs(targetRow - prevRow) == 1 ||
                    Math.abs(targetCol - prevCol) * Math.abs(targetRow - prevRow) == 1) {
                   return true;
            }


        }
        return false;
    }


}
