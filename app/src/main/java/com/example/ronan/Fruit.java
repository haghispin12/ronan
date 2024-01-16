package com.example.ronan;

import android.graphics.drawable.Drawable;

public class Fruit {
    private String name;
    private Drawable drawable;

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {

        return drawable ;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
