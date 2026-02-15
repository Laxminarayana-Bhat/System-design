package org.example.tictactoe;

public class Board {

    private int size;
    private CellDto[][] cellDtos;
    private int movesCount;

    public Board(int size) {
        this.size = size;
        cellDtos = new CellDto[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellDtos[i][j] = new CellDto(i, j);
            }
        }
    }

    public boolean placeMove(int row, int col, Symbol symbol) {
        if (row < 0 || col < 0 || row >= size || col >= size)
            return false;

        if (!cellDtos[row][col].isEmpty())
            return false;

        cellDtos[row][col].setSymbol(symbol);
        movesCount++;
        return true;
    }

    public boolean checkWin(int row, int col, Symbol symbol) {

        // Check row
        boolean win = true;
        for (int j = 0; j < size; j++) {
            if (cellDtos[row][j].getSymbol() != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check column
        win = true;
        for (int i = 0; i < size; i++) {
            if (cellDtos[i][col].getSymbol() != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check main diagonal
        if (row == col) {
            win = true;
            for (int i = 0; i < size; i++) {
                if (cellDtos[i][i].getSymbol() != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check anti-diagonal
        if (row + col == size - 1) {
            win = true;
            for (int i = 0; i < size; i++) {
                if (cellDtos[i][size - i - 1].getSymbol() != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        return false;
    }
    //Strategy Pattern for Win Checking:

    //WinningStrategy interface
    //RowWinningStrategy
    //ColumnWinningStrategy
    //DiagonalWinningStrategy

    public boolean isFull() {
        return movesCount == size * size;
    }
}

