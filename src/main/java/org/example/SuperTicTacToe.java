package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.Exceptions.IllegalMove;
import org.example.Exceptions.IllegalPlayer;

//TODO:MEZOK MEGJELENITESE GRAFIKUSAN (KIVALASZTAS, FRISSITES, JATEKOS CSERE KEZELESE, ANIMACIO, GYOZELEM SZAMLALO JATEKOSOKRA LEBONTVA)
//TODO:HA -1 AKKOR O-jatekos, ha 1 akkor X-jatekos
//TODO:AZ X kezd mindig (kiveve, ha be mas van beallitva)
//TODO:A BOX-okat 3x3-kent kellene kezelni, az indexeles miatt
//TODO:HA EGY BOX LEZARULT AKKOR ATHUZVA JELENIK MEG (AZ AI NEM FOG RAJTA MAR ITERALNI, EZERT EZ BRANCHING FAKTOR)

@Getter @Setter
public class SuperTicTacToe {
    private final SmallField[][] board;
    private int currentPlayer;
    private int winCountPlayer1;
    private int winCountPlayer2;
    private boolean finished;
    private boolean started;
    private int winner; // 1 if player 1 -1 if player 2 has won
    private int lastMoveI = -1;
    private int lastMoveJ = -1;
    private boolean firstMoveMade;

    public SuperTicTacToe() {
        board = new SmallField[3][3]; // Initialize the board with 9x9 cells
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = new SmallField();
            }
        }
        currentPlayer = 1; // Player 1 starts the game AKA X
        winCountPlayer1 = 0;
        winCountPlayer2 = 0;
        finished = false;
        winner = 0;
        firstMoveMade = false;
        started = false;
    }

    /**
     * Changes one field of the board
     *
     * @param boxi Az adott box-nak az i indexe
     * @param boxj Az adott box-nak a j indexe
     * @param row Az adott sor
     * @param col Az adott oszlop
     * @throws IllegalMove Nem szabalyos lepes
     * @throws IllegalPlayer Nem szabalyos jatekos
     */
    public void makeMove(int boxi, int boxj , int row, int col) throws IllegalMove, IllegalPlayer {
        // Check if the move is valid
        if (board[boxi][boxj].getSmallBoardField(row, col) != 0) {
            throw new IllegalMove("This field was already filled!");
        }

        if(board[boxi][boxj].isClosed()){
            String str = String.format("Invalid move! This field is closed! box[%s][%s]", boxi, boxj);
            throw new IllegalMove(str);
        }
        //Ha a jatekos nem azt a box-ot valasztja aminek az indexe az utolso lepes volt, akkor IllegalMove, hacsak nem az elso lepes volt
        //Ha mar csak az egyik index nem egyezik meg, akkor mar invalid
        if(firstMoveMade){
            if(boxi != lastMoveI || boxj != lastMoveJ){
                String str = String.format("Invalid move! With index: %s, %s", boxi, boxj);
                throw new IllegalMove(str);
            }
        }
        // Make the move
        board[boxi][boxj].setField(row, col, currentPlayer);
        lastMoveI = row;
        lastMoveJ = col;
        firstMoveMade = true;

        if (checkIfWon(boxi, boxj)) {
            //Ha nyertek az adott box-ban akkor azt lezarjuk, hogy ne kelljen azon is vegig iteralni
            board[boxi][boxj].setClosed(true);
            if (currentPlayer == 1) {
                addWinToPlayer1();
            } else {
                addWinToPlayer2();
            }
        }

        currentPlayer = -currentPlayer; // Alternate between player 1 and -1
    }

    public void printBoard() throws IllegalMove {
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    for(int l = 0; l < 3; l++){
                        System.out.print(board[i][j].getSmallBoardField(k, l));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    public boolean checkIfWon(int boxi, int boxj){
        int[][] smallBoard = board[boxi][boxj].getSmallBoard(); // Get the small board for the specified box

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

    /**
     *  Ha megadja azt a beallitast, hogy az elso jatekos mas legyen akkor ezzel lehet beallitani
     *  De csak akkor ha meg nem kezdodott el a jatek
     * @param currentPlayer
     */
    public void setCurrentPlayer(int currentPlayer) {
        if(!isStarted()){
            this.currentPlayer = currentPlayer;
        }
    }

    @Override
    public String toString(){
        return "Player 1 winnings: " + getWinCountPlayer1() + "\nPlayer 2 winnings: " + getWinCountPlayer2() + "\nFinished: " + isFinished();
    }
}
