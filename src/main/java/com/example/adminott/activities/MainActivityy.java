package com.example.adminott.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminott.R;
import com.example.adminott.fragment.AccountFragment;
import com.example.adminott.fragment.HomeFragment;
import com.example.adminott.fragment.SearchFragment;
import com.example.adminott.fragment.StarredFragment;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainActivityy extends AppCompatActivity {

    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        BubbleNavigationLinearView bubbleNavigation = findViewById(R.id.bubbleNavigation);



        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position){
                    case 0:
                        selectedFragment = new HomeFragment();
                        break;
                    case 1:
                        selectedFragment = new SearchFragment();
                        break;
                    case 2:
                        selectedFragment = new StarredFragment();
                        break;
                    case 3:
                        selectedFragment = new AccountFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }
        });

    }



}
