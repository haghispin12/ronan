package com.example.ronan;

import android.view.ViewDebug;

public class user {
    private String name;
    private int score;



    public user() {
        name = "";
        score = 0;
    }

    public void setName(String s) {
        this.name = s;
    }
    public void setScore(int n){
        score+=n;
    }
    public String getName(){
        return name;
    }

    public int getScore(){
       return score;
    }



}
