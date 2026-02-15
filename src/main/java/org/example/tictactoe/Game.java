package org.example.tictactoe;

import lombok.Getter;

public class Game {

    private Board board;
    private PlayerDto[] playerDtos;
    private int currentPlayerIndex;
    @Getter
    private GameStatus status;

    public Game(int size, PlayerDto p1, PlayerDto p2) {
        board = new Board(size);
        playerDtos = new PlayerDto[]{p1, p2};
        currentPlayerIndex = 0;
        status = GameStatus.IN_PROGRESS;
    }

    public void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            System.out.println("Game Over!");
            return;
        }

        PlayerDto currentPlayerDto = playerDtos[currentPlayerIndex];

        boolean placed = board.placeMove(row, col, currentPlayerDto.getSymbol());

        if (!placed) {
            System.out.println("Invalid move!");
            return;
        }

        // check win here
        if (board.checkWin(row, col, currentPlayerDto.getSymbol())) {
            status = GameStatus.WIN;
            System.out.println(currentPlayerDto.getName() + " wins!");
            return;
        }

        if (board.isFull()) {
            status = GameStatus.DRAW;
            System.out.println("Game is Draw!");
            return;
        }

        switchTurn();
    }

    private void switchTurn() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }

}

