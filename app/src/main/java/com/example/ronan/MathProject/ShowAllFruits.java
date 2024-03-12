//package com.example.ronan.MathProject;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class ShowAllFruits extends AppCompatActivity {

//    private RecyclerView rcShowFruits;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_showusers);
//         //rcShowFruits = findViewById(rcShowUsers);
//
//        ArrayList<Fruit> fruits = new ArrayList<>();
//        fruits.add (new Fruit("banana",R.drawable.banana));
//        fruits.add (new Fruit("apple",R.drawable.apple));
//        fruits.add (new Fruit("orange",R.drawable.orange));
//        fruits.add (new Fruit("lemon",R.drawable.lemon));
//        fruits.add (new Fruit("fru",R.drawable.fru));
//        fruits.add (new Fruit("grapes",R.drawable.grapes));
//        MyFruitsAdapter myFruitsAdapter = new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Fruit item) {
//                Toast.makeText(ShowAllFruits.this,item.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        rcShowFruits.setLayoutManager(new LinearLayoutManager(this));
//        rcShowFruits.setAdapter(myFruitsAdapter);
//        rcShowFruits.setHasFixedSize(true);
//    }
//}