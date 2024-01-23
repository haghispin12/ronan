package com.example.ronan;

import android.net.Uri;

public class user {
    private String name;
    private int score;
    private Uri uri;
    private long id;
    ModelView vm;


    public user(String n, int s, Uri u,long i) {
        name = n;
        score = s;
        uri = u;
        id = i;
    }
    public user (){
           name = "";
           score = 0;
           uri = null;
           id = 0;
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
    public int getRate(){
         int rate = vm.getRate();
        return rate;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getScore(){
       return score;
    }



}
