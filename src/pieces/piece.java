package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class piece {

    public BufferedImage image;
    public int x, y;
    public int col, row, prevCol, prevRow;
    public int color;

    public piece (int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        prevCol = col;
        prevRow = row;
    }

    public BufferedImage getImage(String imagePath)  {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col) {
        return col * Board.SQUARE_SIZE;
    }

    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
    }

    public int getCol(int x) {
        return ((x + Board.SQUARE_HALF) / Board.SQUARE_SIZE);
    }

    public int getRow(int y) {
        return ((y + Board.SQUARE_HALF) / Board.SQUARE_SIZE);
    }

    public void updatePos() {
        y = getY(row);
        x = getX(col);
        System.out.println(x + " " + y);
        prevCol = getCol(x);
        prevRow = getRow(y);
    }

    public void resetPosition() {
        col = prevCol;
        row = prevRow;
        x = getX(col);
        y = getY(row);

    }

    public boolean canMove (int targetCol, int targetRow) {
        return false;   
    }


    public boolean isInsideBoard(int targetCol, int targetRow) {
        return (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7);
    }


    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }

}
