package com.example.ronan;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
public class ModelView extends  ViewModel{

    user us;
    exercise exs;
    MutableLiveData<Integer> VScore;
    MutableLiveData<Integer> Vnum1;
    MutableLiveData<Integer> Vnum2;
    private int res;

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
    public int getRes(){
        return res;
    }

}

