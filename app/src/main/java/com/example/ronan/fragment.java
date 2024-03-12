package com.example.ronan;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.lifecycle.Observer;
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
    MenuItem delete;
    MenuItem edit;
    private RecyclerView rcShowAllUsers;
    User selectUser;
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
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu, menu);// חיבור המניו לאקס.מ.ל

        delete = menu.findItem(R.id.Delete); // יצירת אובייקט של המחיקה

        delete.setVisible(false); // להסתיר אותו אם רוצים בטעינה הראשונה

        edit = menu.findItem(R.id.Edit);

        edit.setVisible(false);

        super.onCreateOptionsMenu(menu,inflater);}

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Delete:
                delete.setVisible(true);
                AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
                alert.setTitle("Delete user");
                alert.setMessage("Are you sure you want to delete this user?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        vm1.vDeleteUser(requireActivity(),selectUser.getId());

                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
                return true;
            case R.id.Edit:
                edt.setText(selectUser.getName());
                tv.setText("Rate"+selectUser.getRate());
                tv2.setText("Score"+selectUser.getScore());
                img.setImageBitmap(selectUser.getBitmap());
                btn.setText("update");

                return true;
        }
        return false;

    }



    @SuppressLint("FragmentLiveDataObserve")
    @Nullable


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
    View view= inflater.inflate(R.layout.fragment_showusers,container,false);
    initView(view);



        vm1 = new ViewModelProvider(requireActivity()).get(ModelView.class);
        vm1.getAll(requireActivity());
        vm1.myUsers.observe(this, new Observer<ArrayList<User>>() {



            @Override
            public void onChanged(ArrayList<User> Users) {
                MyUsersAdapter myUsersAdapter = new MyUsersAdapter(Users, new MyUsersAdapter.OnItemClickListener1() {
                    @Override
                    public void onItemClick(User item) {
                        Toast.makeText(requireActivity(),item.getName(),Toast.LENGTH_SHORT).show();
                        selectUser = item;
                        edit.setVisible(true);
                        delete.setVisible(true);

                    }
                });
                rcShowAllUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
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
                String str = btn.getText().toString();
                if(str.equals("update")){
                    selectUser.setName(edt.getText().toString());
                    vm1.vUpdate(requireActivity(),selectUser);
                    edt.setText("");
                    tv.setText("Score:");
                    tv2.setText("Rate");
                    btn.setText("add user");
                    img.setImageBitmap(null);
                }else{
                long id = vm1.VInsert(requireActivity());
                if(id>0){
                    showToast("success");
                }else{showToast("failed");}}

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