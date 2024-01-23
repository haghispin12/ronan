package com.example.ronan;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
public class ModelView extends  ViewModel{
    LogIn ln;
    user us;
    exercise exs;
    RateActivity ra;
    MutableLiveData<Integer> VScore;
    MutableLiveData<Integer> Vnum1;
    MutableLiveData<Integer> Vnum2;
    private String name;
    private int res;
    private int rate;
    SQLiteDatabase database;

    public ModelView(){
        exs=new exercise();
        Vnum1 = new MutableLiveData<>();
        Vnum2 = new MutableLiveData<>();
        us = new user();
        VScore = new MutableLiveData<>();
    }

    public void Vtimetable(){
        exs.r1();
        Vnum1.setValue(exs.getNum1());
        Vnum2.setValue(exs.getNum2());
        res = exs.getResult();

        us.setScore(5);


    }
    public void Vmultiply20(){
        exs.r2();
        Vnum1.setValue(exs.getNum1());
        Vnum2.setValue(exs.getNum2());
        res = exs.getResult();

            us.setScore(10);
        }

    public void VEtgar(){
        exs.r3();
        Vnum1.setValue(exs.getNum1());
        Vnum2.setValue(exs.getNum2());
        res = exs.getResult();
        us.setScore(15);
    }
    public void updateName(String s){
        name = s;
        us.setName(s);
    }
    public int VgetScore(){
        return us.getScore();

    }


    public int getVnum1(){
        return Vnum1.getValue();
    }
    public int getVnum2(){
        return Vnum2.getValue();
    }
    public  String getName(){

            return us.getName();
    }
    public int getRes() {
        int res = Vnum1.getValue() * Vnum2.getValue();
        return res;
    }
    public void setRate(int n){
        rate = n;
    }
    public int getRate(){
        return rate;
    }

    public void insertUser(Context context){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.insert(us, context);
    }

}

