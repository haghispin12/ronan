package com.example.ronan;

import android.graphics.drawable.Drawable;

public class Fruit {
    private String name;
    private int drawable;

    public Fruit(String name, int banana) {
        this.name = name;
        this.drawable = banana;

    }

    public Fruit(int drawable) {
        this.drawable = drawable;
    }

    public int getDrawable() {

        return drawable ;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
