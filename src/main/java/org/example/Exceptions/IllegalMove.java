package org.example.Exceptions;

public class IllegalMove extends Exception{

    public IllegalMove(){
        super();
    }

    public IllegalMove(String str){
        super(str);
    }
}
