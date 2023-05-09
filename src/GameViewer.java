// 2048 Game
// Created by: Sierra Shaw
// May 11, 2023

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;             // #1: Required for KeyListener
import java.awt.event.KeyListener;          // #2: Required for KeyListener
import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame implements KeyListener {
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Game game;
    private Board board;
    private Image grid;
    private Color[] colors = {new Color(204, 229, 255), new Color(153, 204, 255), new Color(102, 178, 255), new Color(51, 153, 255), new Color(0, 128, 255), new Color(0, 102, 204), new Color(0, 76, 153), new Color(255, 153, 255), new Color(255, 102, 255), new Color(255, 51, 255), new Color(0, 255, 128)};

    public GameViewer(Game game) {
        // Instantiating Images
        grid = new ImageIcon("Resources/grid.png").getImage();

        // Passes through the game to get the board
        this.game = game;
        board = game.getBoard();

        // Setting up the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        // The addKeyListener method attaches to this KeyListener object
        // an object that implements the KeyListener interface (i.e. supplies the keyTyped, keyReleased, and keyPressed methods)
        // By passing the parameter "this"
        // we are saying that this specific KeyListenerDemo object
        // supplies its own KeyListener functionality (contains the 3 required KeyListener methods).
        addKeyListener(this);               // #4 Required for KeyListener

        setVisible(true);
    }

    public void paint(Graphics g) {
        // Clears screen
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw setup / board with game header and score

        g.drawImage(grid,185, 130, 600, 600, this);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("SansSerif", Font.BOLD, 75));
        g.drawString("2048", 190, 120);

        g.setFont(new Font("SansSerif", Font.BOLD, 25));
        g.drawString("SCORE: " + String.valueOf(game.getScore()), 650, 120);

        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("press 'U' key to undo your move", 190, 725);

        // Iterates through all the tiles in a nested for loop so that each tile
        // can individually draw itself.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getTile(i, j).draw(g, this, colors);
            }
        }

        // Display winning message
        g.setColor(Color.PINK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 70));
        if (game.isGameOver()) {
            g.fillRoundRect(170, 350, 625, 100, 50, 50);
            g.setColor(Color.WHITE);
            if (game.hasWon())
                g.drawString("YOU WIN!!!", 275, 425);
            else
                g.drawString("YOU LOSE :(", 275, 425);
        }

        // Minimizes flickering visual
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_LEFT)
        {
            if (!game.isGameOver())
                game.moveLeft();
        }
        else if(keyCode == KeyEvent.VK_RIGHT)
        {
            if (!game.isGameOver())
                game.moveRight();
        }
        else if(keyCode == KeyEvent.VK_UP)
        {
            if (!game.isGameOver())
                game.moveUp();
        }
        else if(keyCode == KeyEvent.VK_DOWN)
        {
            if (!game.isGameOver())
                game.moveDown();
        }
        else if(keyCode == KeyEvent.VK_U)
        {
            if (!game.isGameOver())
                board.undo();
        }

        game.printBoard();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}