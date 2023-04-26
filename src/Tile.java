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

    public void setEmpty() {
        isEmpty = true;
    }
}
