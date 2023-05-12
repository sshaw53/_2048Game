// 2048 Game
// Created by: Sierra Shaw
// May 11, 2023

import java.util.Stack;

public class Board {
    Tile[][] board;
    Stack<Tile[][]> boards = new Stack<Tile[][]>();

    public Board() {
        // Setting up  board as  4x4 array of blank tiles
        board = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile();
                board[i][j].setX(j);
                board[i][j].setY(i);
            }
        }
        // Set a random tile to a value of 2 and mark it as not empty
        int i = (int)(Math.random() * 4);
        int j = (int)(Math.random() * 4);
        board[i][j].setValue(2);
        board[i][j].setEmpty(false);

        addBoard(makeBoardCopy());
    }

    // Adds board to stack for an undo method, makes sure that the new board is not equivalent to the most recent
    // board on the stack
    public void addBoard(Tile[][] newBoard) {
        if (!isBoardsSame(newBoard))
            boards.push(newBoard);
    }

    public void undo() {
        if (!boards.empty()) {
            Tile[][] newBoard = boards.pop();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    board[i][j].setValue(newBoard[i][j].getValue());
                    board[i][j].setEmpty(newBoard[i][j].isEmpty());
                    board[i][j].setX(newBoard[i][j].getX());
                    board[i][j].setY(newBoard[i][j].getY());
                }
            }
        }
    }

    public Tile getTile(int i, int j) {
        return board[i][j];
    }

    public Tile[][] getBoard() {
        return board;
    }

    // Iterates through board to see if all the tiles are not empty.
    public boolean isBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].isEmpty())
                    return false;
            }
        }
        return true;
    }

    // Iterates through 2 boards to see whether the 2 boards are the same.
    public boolean isBoardsSame(Tile[][] newBoard) {
        if (!boards.empty()) {
            Tile[][] recent = boards.peek();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!recent[i][j].isSameTile(newBoard[i][j]))
                        return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    // Makes a copy of the board with new tiles.
    public Tile[][] makeBoardCopy() {
        Tile[][] newBoard = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j] = new Tile(board[i][j].getValue(), board[i][j].isEmpty());
                newBoard[i][j].setX(board[i][j].getX());
                newBoard[i][j].setY(board[i][j].getY());
            }
        }
        return newBoard;
    }
}
