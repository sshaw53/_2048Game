import java.awt.*;

public class Tile {
    private int value;
    private int x;
    private int y;
    private boolean isEmpty;

    public Tile () {
        value = 0;
        isEmpty = true;
    }

    public Tile (int value) {
        this.value = value;
        isEmpty = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue (int value) {
        this.value = value;
    }

    public void setX (int changeX) {
        this.x = changeX;
        // or like this.x += changeX
    }

    public void setY (int changeY) {
        this.y = changeY;
        // or like this.y += changeY
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean bool) {
        isEmpty = bool;
    }

    public void draw(Graphics g, GameViewer viewer) {
        // Finding the location for each image based on if it is a player or computer die
        // and based on its index in the array
        int xLoc = 185 + 200 * x;
        int yLoc = 130 + 100 * y;

        // Checking to make sure that the tile isn't empty - if the tile is empty it should not
        // draw anything, but if it isn't, it should draw the tile with its distinct value and color
        // Draw background around the dice with the value guessed by player
        if (!isEmpty) {
            g.setColor(Color.orange);
            g.fillRect(xLoc, yLoc, 120, 120);
        }
        // g.drawImage(number[num - 1],xLoc, yLoc, 150, 150, viewer);
    }
}
