package com.example.ronan;

public class user {
    private String name;
    private int score;

    public user(){
        name="";
        score=0;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addScore(int n){
        score+=n;
    }
    public int getScore(){
        return score;
    }

}
