// 2048 Game
// Created by: Sierra Shaw
// May 11, 2023

import java.util.ArrayList;

public class Game {
    private Board board;
    private boolean hasWon;
    private boolean isGameOver;
    private GameViewer viewer;
    private int score;

    public Game() {
        board = new Board();
        printBoard();
        viewer = new GameViewer(this);
        score = 0;
        hasWon = false;
        isGameOver = false;
    }

    // Prints board into console
    public void printBoard() {
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board.getTile(i, j).getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void move(Tile[] arr) {
        // Sorts the board while the game is not over
       if (!checkGameOver()) {
           combine(arr);
           slide(arr);
           addBoardCopy();
       }
    }

    // Combines values in the 4-element array if possible, otherwise it goes to the next one.
    // If the tiles can combine, the combination moves to the left and the right tile becomes blank and both tiles get
    // skipped over.
    public void combine(Tile[] toCombine) {
        int i = 0;
        while (i < 3) {
            if (toCombine[i].getValue() == toCombine[i + 1].getValue()) {
                score += toCombine[i].getValue() * 2;
                toCombine[i].setValue(toCombine[i].getValue() * 2);
                toCombine[i + 1].setValue(0);
                toCombine[i + 1].setEmpty(true);
                i += 2;
            }
            else
                i += 1;
        }
    }

    // Slides all the tiles to the left once they have been combined.
    // Iterates through to make sure that if a tile is empty and the one on the right is blank,
    // then swap the tiles.
    public void slide(Tile[] toSlide) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (toSlide[j].isEmpty() && toSlide[j + 1].isEmpty() == false) {
                    toSlide[j].setEmpty(false);
                    toSlide[j].setValue(toSlide[j + 1].getValue());
                    toSlide[j + 1].setEmpty(true);
                    toSlide[j + 1].setValue(0);
                }
            }
        }
    }

    // Adds a new tile of value 2 or 4 to the board at a random empty location.
    public void addNewTile() {
        if (!board.isBoardFull()) {
            Tile newTile = board.getTile((int) (Math.random() * 4), (int) (Math.random() * 4));
            while (newTile.isEmpty() == false) {
                newTile = board.getTile((int) (Math.random() * 4), (int) (Math.random() * 4));
            }

            newTile.setEmpty(false);
            newTile.setValue(((int) (Math.random() * 2) + 1) * 2);
        }
    }

    // Each move method forms an array of tiles based on the direction in which the method is called and calls the
    // move method on each array of tiles in order to organize them. Then, it calls a method to add a new tile
    // to the board randomly.
    public void moveLeft() {
        // checking for player key input to see if they push something down to change the board
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            arr = board.getBoard()[i];
            move(arr);
        }
        addNewTile();
    }

    public void moveRight() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(i, 3 - j);
            }
            move(arr);
        }
        addNewTile();
    }

    public void moveUp() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(j, i);
            }
            move(arr);
        }
        addNewTile();
    }

    public void moveDown() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(3 - j, i);
            }
            move(arr);
        }
        addNewTile();
    }

    // Adds a copy of the board to a stack of boards to implement the undo method
    public void addBoardCopy() {
        Tile[][] newBoard = board.makeBoardCopy();
        board.addBoard(newBoard);
    }

    public int getScore() {
        return score;
    }

    // Checks to see if any tile has reached 2048, will return true if so and false otherwise.
    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getTile(i, j).getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkGameOver() {
        // Checks to see if the game has won
        if (checkWin()) {
            hasWon = true;
            isGameOver = true;
            return true;
        }

        // Makes sure that there are no empty tiles left on the board
        if (!board.isBoardFull()) {
            return false;
        }

        // If the board is full of values, it checks every move direction and sees whether the board moves still
        // If it can move in any of the directions, it returns false and the game continues
        if (canMoveLeft() || canMoveRight() || canMoveUp() || canMoveDown())
            return false;

        // Otherwise, the game will have no moves left and return true
        isGameOver = true;
        return true;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Board getBoard() {
        return board;
    }

    // Checking each move to see whether the game can continue by moving in each direction and checking for
    // consecutive numbers.
    public boolean canMoveLeft() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            arr = board.getBoard()[i];
            if(checkMoves(arr)) {
                return true;
            }
        }
        return false;
    }

    public boolean canMoveRight() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(i, 3 - j);
            }
            if (checkMoves(arr))
                return true;
        }
        return false;
    }

    public boolean canMoveUp() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(j, i);
            }
            if (checkMoves(arr))
                return true;
        }
        return false;
    }

    public boolean canMoveDown() {
        Tile[] arr = new Tile[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] = board.getTile(3 - j, i);
            }
            if (checkMoves(arr))
                return true;
        }
        return false;
    }

    // Sees whether there are moves left, returns true if so, and false if not - helper method for canMove methods
    public boolean checkMoves(Tile[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i].getValue() == arr[i + 1].getValue())
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

}