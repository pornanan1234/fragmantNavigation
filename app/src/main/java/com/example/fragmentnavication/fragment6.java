package com.example.fragmentnavication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment6 extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment6, container, false);

        previousBtn = view.findViewById(R.id.fragment6toPrevious);
        attackLaunch = view.findViewById(R.id.attackLaunch);
        defenseLaunch = view.findViewById(R.id.defenseLaunch);
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

        attackLaunch.setOnClickListener(
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

        defenseLaunch.setOnClickListener(
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

        goToShor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LaunchShorFragment();
            }
        });




        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new fragment5());
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
    Button nextBtn,previousBtn, attackLaunch, defenseLaunch;

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
        view.findViewById(R.id.ApplicationText).setVisibility(View.VISIBLE);
        view.findViewById(R.id.textView1).setVisibility(View.VISIBLE);
        view.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
        view.findViewById(R.id.textView3).setVisibility(View.VISIBLE);
        view.findViewById(R.id.textView4).setVisibility(View.VISIBLE);
        view.findViewById(R.id.fragment6toPrevious).setVisibility(View.VISIBLE);
        attackLaunch.setVisibility(View.VISIBLE);
        defenseLaunch.setVisibility(View.VISIBLE);
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
        view.findViewById(R.id.ApplicationText).setVisibility(View.GONE);
        view.findViewById(R.id.textView1).setVisibility(View.GONE);
        view.findViewById(R.id.textView2).setVisibility(View.GONE);
        view.findViewById(R.id.textView3).setVisibility(View.GONE);
        view.findViewById(R.id.textView4).setVisibility(View.GONE);
        view.findViewById(R.id.fragment6toPrevious).setVisibility(View.GONE);
        attackLaunch.setVisibility(View.GONE);
        defenseLaunch.setVisibility(View.GONE);
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