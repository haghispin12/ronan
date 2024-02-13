package com.example.ronan;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragment extends Fragment {

    private EditText edt;
    private TextView tv;
    private TextView tv2;
    private Button bt;
    private ImageView img;
    private Button btn;
    ModelView vm1;
    Uri uri;

    private RecyclerView rcShowAllUsers;


    Intent shareIntent = new Intent(Intent.ACTION_SEND);


    private void showToast(String s){
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                if(result.getResultCode()==RESULT_OK) {
                    img.setImageURI(uri);
                    vm1.setUri(uri);

                }
                }
            }
    );



    @SuppressLint("FragmentLiveDataObserve")
    @Nullable


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

    View view= inflater.inflate(R.layout.fragment_showusers,container,false);
    initView(view);

        vm1 = new ViewModelProvider(requireActivity()).get(ModelView.class);
        vm1.myUsers.observe(this, new Observer<ArrayList<user>>() {

            @Override
            public void onChanged(ArrayList<user> users) {
                MyUsersAdapter myUsersAdapter = new MyUsersAdapter(, new MyFruitsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Fruit item) {
                        Toast.makeText(,item.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
                rcShowAllUsers.setLayoutManager(new LinearLayoutManager());
                rcShowAllUsers.setAdapter(myUsersAdapter);
                rcShowAllUsers.setHasFixedSize(true);
            }
        });


        edt.setText(vm1.getName());

        Integer integer = vm1.VgetScore();
        tv2.setText(integer+"");

        Integer h = vm1.getRate();
        tv.setText(h+"");



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startCamera.launch(cameraIntent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = vm1.VInsert(requireActivity());
                if(id>0){
                    showToast("success");



                }else{showToast("failed");}

            }
        });


    return view;
    }


    private void initView(View view) {
        edt = view.findViewById(R.id.EditText);
        tv = view.findViewById(R.id.TV1);
        tv2 = view.findViewById(R.id.TV2);
        bt = view.findViewById(R.id.button5);
        img = view.findViewById(R.id.imageView2);
        btn = view.findViewById(R.id.button6);
        rcShowAllUsers = view.findViewById(R.id.rcShowUsers);

    }
}