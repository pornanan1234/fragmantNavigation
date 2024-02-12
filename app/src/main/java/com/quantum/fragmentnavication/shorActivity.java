package com.quantum.fragmentnavication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.quantum.fragmentnavication.R;

public class shorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        replaceFragment(new shorFragment1());


    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.shoreframeLayout,fragment, "shorFragment");
        fragmentTransaction.commit();
    }
}