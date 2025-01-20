package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static final int PANEL_WIDTH = 1000;
    public static final int PANEL_HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    //PIECES
    public static ArrayList<piece> pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();
    piece activePiece;

    //COLOR
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    //BOOLEANS
    boolean canMove;
    boolean validSquare;

    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.GRAY);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void LaunchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
        //WHITE TEAM
        //pawn
        pieces.add(new pawns(WHITE, 0,6));
        pieces.add(new pawns(WHITE, 1,6));
        pieces.add(new pawns(WHITE, 2,6));
        pieces.add(new pawns(WHITE, 3,6));
        pieces.add(new pawns(WHITE, 4,6));
        pieces.add(new pawns(WHITE, 5,6));
        pieces.add(new pawns(WHITE, 6,6));
        pieces.add(new pawns(WHITE, 7,6));

        //rook, horse, bishop, quuen, king
        pieces.add(new horses(WHITE, 1,7));
        pieces.add(new horses(WHITE, 6,7));
        pieces.add(new rooks(WHITE, 7,7));
        pieces.add(new rooks(WHITE, 0,7));
        pieces.add(new bishops(WHITE, 2,7));
        pieces.add(new bishops(WHITE, 5,7));
        pieces.add(new queens(WHITE, 3,7));
        pieces.add(new kings(WHITE, 4,7));

        //BLACK TEAM
        //pawn
        pieces.add(new pawns(BLACK, 0,1));
        pieces.add(new pawns(BLACK, 1,1));
        pieces.add(new pawns(BLACK, 2,1));
        pieces.add(new pawns(BLACK, 3,1));
        pieces.add(new pawns(BLACK, 4,1));
        pieces.add(new pawns(BLACK, 5,1));
        pieces.add(new pawns(BLACK, 6,1));
        pieces.add(new pawns(BLACK, 7,1));

        //rook, horse, bishop, quuen, king
        pieces.add(new horses(BLACK, 1,0));
        pieces.add(new horses(BLACK, 6,0));
        pieces.add(new rooks(BLACK, 7,0));
        pieces.add(new rooks(BLACK, 0,0));
        pieces.add(new bishops(BLACK, 2,0));
        pieces.add(new bishops(BLACK, 5,0));
        pieces.add(new queens(BLACK, 3,0));
        pieces.add(new kings(BLACK, 4,0));
    }

    private void copyPieces (ArrayList<piece> source, ArrayList<piece> target) {
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));

        }
    }

    @Override
    public void run() {

        // loopa igenom hela spelet och uppdatera och repaint varje fps
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }
        // mouse button pressed
    private void update() {
        if (mouse.pressed) {
            if (activePiece == null) {
                // if active pieace null, check if can pick up piece
                for (piece p : pieces) {
                    if (p.color == currentColor &&
                            p.col == mouse.x / Board.SQUARE_SIZE &&
                            p.row == mouse.y / Board.SQUARE_SIZE) {
                        activePiece = p;

                    }
                }
            } else {
                // if holding a piece, update pos
                simulate();
            }
        }
        if (mouse.pressed == false) {
            if (activePiece != null) {

                if (validSquare) {
                    activePiece.updatePos();
                } else {
                    activePiece.resetPosition();
                    activePiece = null;
                }
            }

        }
    }

    private void simulate() {

        canMove = false;
        validSquare = false;

            // if piece is being held, upd pos
        activePiece.x = mouse.x - Board.SQUARE_HALF;
        activePiece.y = mouse.y - Board.SQUARE_HALF;
        activePiece.col = activePiece.getCol(activePiece.x);
        activePiece.row = activePiece.getRow(activePiece.y);

        if (activePiece.canMove(activePiece.col, activePiece.row)) {
            canMove = true;
            validSquare = true;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw board
        board.draw(g2);

        //draw pieces
        for (piece p : simPieces) {
            p.draw(g2);

        }
        if (activePiece != null) {

            if (canMove) {

                g2.setColor(Color.WHITE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE,
                        Board.SQUARE_SIZE, Board.SQUARE_SIZE);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            // draw active piece, so not hidden behind new grey square
            activePiece.draw(g2);
        }

    }
}