package com.example.ronan;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ModelView extends  ViewModel{
    LogIn ln;
    user us;
    exercise exs;
    MyUsersAdapter mua;
    RateActivity ra;
    MutableLiveData<Integer> VScore;
    MutableLiveData<Integer> Vnum1;
    MutableLiveData<Integer> Vnum2;
    private String name;
    private int res;
    MutableLiveData<Integer> rate;
    MutableLiveData<ArrayList<user>> myUsers;
    SQLiteDatabase database;


    public ModelView(){
        exs=new exercise();
        Vnum1 = new MutableLiveData<>();
        Vnum2 = new MutableLiveData<>();
        us = new user();
        VScore = new MutableLiveData<>();
        rate = new MutableLiveData<>();
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
    public int VgetScore() {
        return us.getScore();

    }
    public void setUri(Uri uri){
        us.setUri(uri);
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

    public int getRate(){
        return us.getRate();
    }
    ArrayList<user> users = new ArrayList<>();
    public void VInsert(Context context){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.insert(us, context);
        users = dbHelper.selectAll();

    }

    public void setRate(int n) {
        us.setRate(n);
    }
}

