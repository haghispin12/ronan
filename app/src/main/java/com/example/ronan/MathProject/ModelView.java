//package com.example.ronan.MathProject;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.net.Uri;
//
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.ArrayList;
//
//public class ModelView<users> extends  ViewModel{
//    LogIn ln;
//    User us1;
//    exercise exs;
//    MyUsersAdapter mua;
//    RateActivity ra;
//    MutableLiveData<Integer> VScore;
//    MutableLiveData<Integer> Vnum1;
//    MutableLiveData<Integer> Vnum2;
//    private String name;
//    private int res;
//    MutableLiveData<Integer> rate;
//
//    MutableLiveData<ArrayList<User>> myUsers;
//    SQLiteDatabase database;
//
//
//    public ModelView(){
//        exs=new exercise();
//        Vnum1 = new MutableLiveData<>();
//        Vnum2 = new MutableLiveData<>();
//        us1 = new User();
//        VScore = new MutableLiveData<>();
//        rate = new MutableLiveData<>();
//        myUsers = new MutableLiveData<>();
//    }
//
//    public void Vtimetable(){
//        exs.r1();
//        Vnum1.setValue(exs.getNum1());
//        Vnum2.setValue(exs.getNum2());
//        res = exs.getResult();
//
//        us1.setScore(5);
//
//
//    }
//    public void Vmultiply20(){
//        exs.r2();
//        Vnum1.setValue(exs.getNum1());
//        Vnum2.setValue(exs.getNum2());
//        res = exs.getResult();
//
//            us1.setScore(10);
//        }
//
//    public void VEtgar(){
//        exs.r3();
//        Vnum1.setValue(exs.getNum1());
//        Vnum2.setValue(exs.getNum2());
//        res = exs.getResult();
//        us1.setScore(15);
//    }
//    public void updateName(String s){
//        name = s;
//        us1.setName(s);
//    }
//    public int VgetScore() {
//        return us1.getScore();
//
//    }
//    public void setUri(Uri uri){
//        us1.setUri(uri);
//    }
//
//
//    public int getVnum1(){
//        return Vnum1.getValue();
//    }
//    public int getVnum2(){
//        return Vnum2.getValue();
//    }
//    public  String getName(){
//
//            return us1.getName();
//    }
//    public int getRes() {
//        int res = Vnum1.getValue() * Vnum2.getValue();
//        return res;
//    }
//
//    public int getRate(){
//        return us1.getRate();
//    }
//    ArrayList<User> Users = new ArrayList<>();
//
//    public long VInsert(Context context){
//        DBHelper dbHelper = new DBHelper(context);
//        long id = dbHelper.insert(us1, context);
//        getAll(context);
//        return id;
//    }
//    public void vDeleteUser(Context context, long id){
//        DBHelper db = new DBHelper(context);
//        db.deleteById(id);
//        getAll(context);
//    }
//    public void getAll(Context context){
//        DBHelper dbHelper = new DBHelper(context);
//        Users = dbHelper.selectAll();
//        myUsers.setValue(Users);
//    }
//    public void vUpdate(Context context, User user){
//        DBHelper db = new DBHelper(context);
//        db.update(user);
//        getAll(context);
//    }
//
//    public void setRate(int n) {
//        us1.setRate(n);
//    }
//
//    public void addUser(){
//
//    }
//}
//
//
