package org.example.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CellDto {
    private int row;
    private int col;
    private Symbol symbol;

    public CellDto(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isEmpty() {
        return symbol == null;
    }

}
