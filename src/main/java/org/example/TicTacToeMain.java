package org.example;

import org.example.Exceptions.IllegalMove;
import org.example.Exceptions.IllegalPlayer;
import org.example.Gui.SuperTicTacToeGUI;

public class TicTacToeMain {
    public static void main(String[] args) throws IllegalPlayer, IllegalMove {
        SuperTicTacToe game = new SuperTicTacToe();
        game.makeMove(0, 0, 0, 1);
        game.makeMove(0, 1, 1, 1);
        game.makeMove(1, 1, 1, 1);
        game.makeMove(1, 1, 1, 2);
        //game.makeMove(0, 0, 2, 2); //makes error
        game.printBoard();
        //TODO:If isFinished and Player 1 has 3 winnings -> Print Player 1 has won
        //TODO:If isFinished and Player -1 has 3 winnings -> Print Player 2 has won
        if(game.isFinished() && game.getWinner() == 1){
            System.err.println("Player 1 has won!");
        }
        if(game.isFinished() && game.getWinner() == -1){
            System.err.println("Player 2 has won!");
        }
        System.err.println(game);
        SuperTicTacToeGUI superTicTacToeGUI = new SuperTicTacToeGUI();
    }
}
