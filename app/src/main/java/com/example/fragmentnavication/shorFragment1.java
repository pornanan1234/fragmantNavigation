package com.example.fragmentnavication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class shorFragment1 extends Fragment {

    Context context;

    int q, r, k, p, newq, key1, key2;

    Log log;

    //the variables for the preparation step
    TextView selectPrimeNumber1, NumberPicker1, selectPrimeNumber2, NumberPicker2, Composite;
    TextView ShorStep1, ShorStep1Explanation, SelectRandomK;
    TextView Shor2point1, ShorStep2, ShorStep2Explanation, ShorStep2Formula, warning1, Shor2point2, warning2, Shor2point3;
    TextView Step3point1, ShorStep3, ShorStep3Explanation, Shor3point2, warning3, Shor3point3;
    TextView Shor4point1, ShorStep4, ShorStep4Explanation, Shor4point2, Shor4point3, Shor4point4, ShorStepFinal;
    Button ShorToHome1, ShorToHome2;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shor1, container, false);
        ShorToHome1 = view.findViewById(R.id.ShorToHome1);
        ShorToHome2 = view.findViewById(R.id.ShorToHome2);
        isAllFabsVisible=false;
        setHideFab();

        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        isAllFabsVisible=!isAllFabsVisible;

                        if(isAllFabsVisible) {
                            setShowFab();
                        }else {
                            setHideFab();
                        }

                    }
                });

        goToAttack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchAttackFragment();
                    }
                });

        goToDefense.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchDefendFragment();
                    }
                });
        backToHome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchIntroFragment();
                    }
                });

        goToShor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchShorFragment();

                    }
                });

        ShorToHome1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchIntroFragment();
                    }
                });

        ShorToHome2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LaunchIntroFragment();
                    }
                });


        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                if(isAllFabsVisible) {
                    setHideFab();
                    isAllFabsVisible=!isAllFabsVisible;
                }


            }
        });

        return view;

    }

    View view;
    Button nextBtn;

    FloatingActionButton backToHome, goToAttack, goToDefense,goToShor;
    ExtendedFloatingActionButton mAddFab;
    TextView directToHomeActionText, DirectToAttackActionText, DirectToDefenseActionText,DirectToShorActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void setHideFab(){
        mAddFab = view.findViewById(R.id.add_fab);
        backToHome = view.findViewById(R.id.direct_to_home_fab);
        goToAttack = view.findViewById(R.id.direct_to_attack_fab);
        goToDefense = view.findViewById(R.id.direct_to_defense_fab);
        goToShor = view.findViewById(R.id.direct_to_shor_fab);
        directToHomeActionText =
                view.findViewById(R.id.direct_to_home_action_text);
        DirectToAttackActionText =
                view.findViewById(R.id.direct_to_attack_action_text);
        DirectToDefenseActionText =
                view.findViewById(R.id.direct_to_defense_action_text);
        DirectToShorActionText = view.findViewById(R.id.direct_to_shor_text);
        // Now set all the FABs and all the action name
        // texts as GONE
        backToHome.setVisibility(View.GONE);
        goToAttack.setVisibility(View.GONE);
        goToDefense.setVisibility(View.GONE);
        goToShor.setVisibility(View.GONE);

        directToHomeActionText.setVisibility(View.GONE);
        DirectToAttackActionText.setVisibility(View.GONE);
        DirectToDefenseActionText.setVisibility(View.GONE);
        DirectToShorActionText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        //isAllFabsVisible = false;
        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();

    }

    private void setShowFab(){

        // when isAllFabsVisible becomes
        // true make all the action name
        // texts and FABs VISIBLE.
        backToHome.show();
        goToAttack.show();
        goToDefense.show();
        goToShor.show();
        directToHomeActionText
                .setVisibility(View.VISIBLE);
        DirectToAttackActionText
                .setVisibility(View.VISIBLE);
        DirectToDefenseActionText
                .setVisibility(View.VISIBLE);
        DirectToShorActionText.setVisibility(View.VISIBLE);
        // Now extend the parent FAB, as
        // user clicks on the shrinked
        // parent FAB
        mAddFab.extend();

        // make the boolean variable true as
        // we have set the sub FABs
        // visibility to GONE

    }

    public void LaunchShorFragment() {
        replaceFragment(new shorFragment1());
    }
    public void LaunchIntroFragment() {
        replaceFragment(new fragment1());
    }
    public void LaunchAttackFragment() {
        replaceFragment(new AttackFragment1());
    }
    public void LaunchDefendFragment() {
        replaceFragment(new DefendFragment1());
    }
}