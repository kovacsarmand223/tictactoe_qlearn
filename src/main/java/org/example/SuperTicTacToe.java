package org.example;

import org.example.Exceptions.IllegalField;
import org.example.Exceptions.IllegalPlayer;

//TODO:MEZOK MEGJELENITESE GRAFIKUSAN (KIVALASZTAS, FRISSITES, JATEKOS CSERE KEZELESE, ANIMACIO, GYOZELEM SZAMLALO JATEKOSOKRA LEBONTVA)
//TODO:HA -1 AKKOR O-jatekos, ha 1 akkor X-jatekos
//TODO:AZ X kezd mindig (kiveve, ha be mas van beallitva)
//TODO:

public class SuperTicTacToe {
    private final SmallField[] board; // 2D array to represent the super tic-tac-toe board
    private int currentPlayer;
    private int winCountPlayer1;
    private int winCountPlayer2;
    private boolean finished;
    private int winner; // 1 if player 1 -1 if player 2 has won

    public SuperTicTacToe() {
        board = new SmallField[9]; // Initialize the board with 9x9 cells
        for(int i = 0; i < 9; i++){
            board[i] = new SmallField();
        }
        currentPlayer = 1; // Player 1 starts the game
        winCountPlayer1 = 0;
        winCountPlayer2 = 0;
        finished = false;
        winner = 0;
    }

    /**
     * Changes one field of the board
     *
     * @param box
     * @param row
     * @param col
     * @throws IllegalField
     * @throws IllegalPlayer
     */
    public void makeMove(int box, int row, int col) throws IllegalField, IllegalPlayer {
        // Check if the move is valid
        if (board[box].getSmallBoardField(row, col) != 0) {
            throw new IllegalField("This field was already filled!");
        }
        // Make the move
        board[box].setField(row, col, currentPlayer);

        if (checkIfWon(box)) {
            if (currentPlayer == 1) {
                addWinToPlayer1();
            } else {
                addWinToPlayer2();
            }
        }

        //TODO:Itt kell majd checkelni a boardot amibe bele szurta az utolso lepest, hogy gyozott-e abban a box-ban
        // Switch player
        currentPlayer = -currentPlayer; // Alternate between player 1 and -1
    }

    public void printBoard() throws IllegalField {
        // Print the super tic-tac-toe board
        for(int i = 0 ; i < 9; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    System.out.print(board[i].getSmallBoardField(j, k));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public boolean checkIfWon(int box){
        int[][] smallBoard = board[box].getSmallBoard(); // Get the small board for the specified box

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (smallBoard[i][0] == smallBoard[i][1] && smallBoard[i][1] == smallBoard[i][2] && smallBoard[i][0] != 0) {
                return true; // Row i is all the same non-zero value, indicating a win
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (smallBoard[0][i] == smallBoard[1][i] && smallBoard[1][i] == smallBoard[2][i] && smallBoard[0][i] != 0) {
                return true; // Column i is all the same non-zero value, indicating a win
            }
        }

        // Check diagonals
        if ((smallBoard[0][0] == smallBoard[1][1] && smallBoard[1][1] == smallBoard[2][2] && smallBoard[0][0] != 0) ||
            (smallBoard[0][2] == smallBoard[1][1] && smallBoard[1][1] == smallBoard[2][0] && smallBoard[0][2] != 0)) {
            return true; // Either diagonal is all the same non-zero value, indicating a win
        }

        return false; // No winning conditions found
    }

    public int getWinCountPlayer1() {
        return winCountPlayer1;
    }

    public int getWinCountPlayer2() {
        return winCountPlayer2;
    }

    public void addWinToPlayer1(){
        this.winCountPlayer1++;
        if(getWinCountPlayer1() == 3){
            setWinner(1);
        }
    }

    public void addWinToPlayer2(){
        this.winCountPlayer2++;
        if(getWinCountPlayer2() == 3){
            setWinner(-1);
        }
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString(){
        return "Player 1 winnings: " + getWinCountPlayer1() + "\nPlayer 2 winnings: " + getWinCountPlayer2() + "\nFinished: " + isFinished();
    }

    public static void main(String[] args) throws IllegalPlayer, IllegalField {
        SuperTicTacToe game = new SuperTicTacToe();
        game.makeMove(0, 0, 1);
        game.makeMove(5, 1, 2);
        game.makeMove(4, 2, 2);
        game.makeMove(4, 0, 1);
        game.makeMove(0, 1, 1);
        game.makeMove(4, 0, 2);
        game.makeMove(0, 2, 1);
        game.printBoard();
        //TODO:If isFinished and Player 1 has 3 winnings -> Print Player 1 has won
        if(game.isFinished() && game.getWinner() == 1){
            System.err.println("Player 1 has won!");
        }
        if(game.isFinished() && game.getWinner() == -1){
            System.err.println("Player 2 has won!");
        }
        //TODO:If isFinished and Player -1 has 3 winnings -> Print Player 2 has won
        System.err.println(game);
    }
}
