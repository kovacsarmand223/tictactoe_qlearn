package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.Exceptions.IllegalMove;
import org.example.Exceptions.IllegalPlayer;

@Getter @Setter
public class SmallField {
    private final int[][] smallBoard;

    private boolean isClosed;

    public SmallField(){
        smallBoard = new int[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                smallBoard[i][j] = 0;
            }
        }
        isClosed = false;
    }

    public void setField(int row, int col, int player) throws IllegalMove, IllegalPlayer {
        if(row > 2 || row < 0){
            throw new IllegalMove("This row is out of bound: " + Integer.toString(row));
        }
        if(col > 2 || col < 0){
            throw new IllegalMove("This column is out of bound: " + Integer.toString(col));
        }
        if(player > 1 || player < -1){
            throw new IllegalPlayer("The player does not exist: " + Integer.toString(player));
        }
        smallBoard[row][col] = player;
    }

    /**
     * @param row
     * @param col
     * @return Visszaadja az egyik mezot az adott 3x3-as box-ban
     * @throws IllegalMove
     */
    public int getSmallBoardField(int row, int col) throws IllegalMove {
        if(row > 2 || row < 0){
            throw new IllegalMove("This row index is out of bound: " + Integer.toString(row));
        }
        if(col > 2 || col < 0){
            throw new IllegalMove("This column index is out of bound: " + Integer.toString(col));
        }
        return smallBoard[row][col];
    }
}
