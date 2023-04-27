import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;             // #1: Required for KeyListener
import java.awt.event.KeyListener;          // #2: Required for KeyListener
import javax.swing.JFrame;

public class GameViewer extends JFrame implements KeyListener {
    // private Image[] tiles;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Game game;
    private Board board;

    public GameViewer(Game game) {
        // Instantiating Images

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
        // By passsing the parameter "this"
        // we are saying that this specific KeyListenerDemo object
        // supplies its own KeyListener functionality (contains the 3 required KeyListener methods).
        addKeyListener(this);               // #4 Required for KeyListener

        setVisible(true);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_LEFT)
        {
            game.moveLeft();
        }
        else if(keyCode == KeyEvent.VK_RIGHT)
        {
            game.moveRight();
        }
        else if(keyCode == KeyEvent.VK_UP)
        {
            game.moveUp();
        }
        else if(keyCode == KeyEvent.VK_DOWN)
        {
            game.moveDown();
        }

        game.printBoard();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}