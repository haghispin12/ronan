package com.example.ronan;

import android.content.Intent;

import java.util.Random;

public class exercise {
    private int num1;
    private int num2;
    private int result;


    public exercise(){

    }


   public void r1 (){
       Random r = new Random();
       num1 = r.nextInt(9)+1;
       num2 = r.nextInt(9)+1;
       result = num1*num2;
   }
   public void r2(){
       Random r = new Random();

       num1 = r.nextInt(9)+1;
       num2 = r.nextInt(19)+1;
       result = num1*num2;
   }
   public void r3(){
       Random r = new Random();
       num1 = r.nextInt(9)+1;
       num2 = r.nextInt(99)+1;
       result = num1*num2;
   }
   public boolean GoodAnswer(String s){
       int ans = Integer.valueOf(s);
       if(ans == result){
           return true;
       }return false;
   }
   public int getNum1(){
        return this.num1;
   }
   public int getNum2(){
        return this.num2;
   }
   public int getResult(){

        return num1*num2;
   }
   public void setNum1(int n){
        this.num1 = n;
   }
   public void setNum2(int n){
        this.num2 = n;
   }
   public void setResult(int r){
        this.result= r;
   }

}
