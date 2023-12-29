package com.example.fragmentnavication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment1 extends Fragment {

    View view;
    Button nextBtn;

    FloatingActionButton backToHome, goToAttack, goToDefense,goToShor;
    ExtendedFloatingActionButton mAddFab;
    TextView directToHomeActionText, DirectToAttackActionText, DirectToDefenseActionText,DirectToShorActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        setFab(view);

        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        checkFab();

                    }
                });

        goToAttack.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        goToDefense.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        backToHome.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        nextBtn = view.findViewById(R.id.fragment1toNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new fragment2());
            }
        });

        return view;

    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void setFab(View view){
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
        isAllFabsVisible = false;
        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();

    }

    private void checkFab(){
        if (!isAllFabsVisible) {
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
            isAllFabsVisible = true;
        } else {
            // when isAllFabsVisible becomes
            // true make all the action name
            // texts and FABs GONE.
            backToHome.hide();
            goToAttack.hide();
            goToDefense.hide();
            goToShor.hide();
            directToHomeActionText
                    .setVisibility(View.GONE);
            DirectToAttackActionText
                    .setVisibility(View.GONE);
            DirectToDefenseActionText
                    .setVisibility(View.GONE);
            DirectToShorActionText.setVisibility(View.GONE);
            // Set the FAB to shrink after user
            // closes all the sub FABs
            mAddFab.shrink();
            // make the boolean variable false
            // as we have set the sub FABs
            // visibility to GONE
            isAllFabsVisible = false;
        }
    }

}