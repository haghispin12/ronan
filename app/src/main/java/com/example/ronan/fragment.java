package com.example.ronan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class fragment extends Fragment {

    private EditText edt;
    private TextView tv;
    private TextView tv2;
    private Button bt;
    private ImageView img;
    private Button btn;
    ModelView vm1;

    @Nullable


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
    View view= inflater.inflate(R.layout.fragment_showusers,container,false);
    initView(view);
        vm1 = new ViewModelProvider(requireActivity()).get(ModelView.class);

        edt.setText(vm1.getName());

        Integer integer = vm1.VgetScore();
        tv2.setText(integer+"");

        Integer h = vm1.getRate();
        tv.setText(h+"");

    return view;
    }

    private void initView(View view) {
        edt = view.findViewById(R.id.EditText);
        tv = view.findViewById(R.id.TV1);
        tv2 = view.findViewById(R.id.TV2);
        bt = view.findViewById(R.id.button5);
        img = view.findViewById(R.id.imageView2);
        btn = view.findViewById(R.id.button6);

    }
}
