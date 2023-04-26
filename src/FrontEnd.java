import javax.swing.*;
import java.awt.*;

public class FrontEnd extends JFrame {
    // private Image[] tiles;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Game game;
    private Board board;

    public FrontEnd(Game game) {
        // Instantiating Images


        // Passes through the tester to allow for the player and computer
        // dice arrays to pass
        this.game = game;
        board = game.getBoard();

        // Setting up the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    //public Image[] getImages() { return diceImages; }

    public void paint(Graphics g) {
       /* // Draw setup / board
        g.drawImage(board,0, 0, 1000, 800, this);

        // Draw hand over the dice on computer dice until unveils after guess
        // and draw's player's dice
        if (!tester.isRevealComp()) {
            for (int i = 0; i < 5; i++) {
                playersDice[i].draw(g, i, 1, this);
            }
            g.drawImage(hand,0, 50, 1000, 200, this);
        }

        // Draws all dice - for loop for the items in players dice and computers dice arrays
        // (once revealed to show computer's rolls)
        if (tester.isRevealComp()) {
            for (int i = 0; i < 5; i++) {
                playersDice[i].draw(g, i, 1, this);
                computersDice[i].draw(g, i, 2, this);
            }
        }

        // Display winning message
        g.setColor(Color.PINK);
        g.setFont(new Font("Serif", Font.PLAIN, 100));
        if (tester.isGameOver()) {
            if (tester.isHasWon()) {
                g.drawString("YOU WIN!", 280, 400);
            }
            if (tester.isHasLost()) {
                g.drawString("YOU LOSE :(", 260, 400);
            }
        }

        */
    }
}