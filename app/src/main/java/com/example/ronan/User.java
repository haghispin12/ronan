package com.example.ronan;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    private String name;
    private int score;
    private Uri uri;
    private int rate;
    private long id;
    ModelView vm;
    private Bitmap bitmap;


//    public user(String n, int s, Uri u,long i) {
//        name = n;
//        score = s;
//        uri = u;
//        id = i;
//    }
    public User(long id, String name, int rate, Bitmap bitmap, int score){
           this.name = name;
           this.score = score;
           this.rate = rate;
           this.bitmap = bitmap;
           this.id = id;
    }
    public User(){

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
