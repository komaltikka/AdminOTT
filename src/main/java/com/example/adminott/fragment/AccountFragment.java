package com.example.adminott.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.adminott.R;
import com.example.adminott.activities.MainActivity;


public class AccountFragment extends Fragment {
TextView logout;
FrameLayout container_fragment;
    public AccountFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_account,container,false);
     logout=view.findViewById(R.id.logout);
     logout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });
        return view;
    }
}