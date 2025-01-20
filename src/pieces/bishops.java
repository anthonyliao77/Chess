package pieces;

import main.GamePanel;

public class bishops extends piece{
    public bishops(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/pieces/w-bishop");
        }
        else {
            image = getImage("/pieces/b-bishop");
        }



    }
}
