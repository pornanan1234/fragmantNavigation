package com.example.fragmentnavication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class shorFragment1 extends Fragment {

    Context context;

    int chosenNumber1, chosenNumber2, q, r, k, p, newq, key1, key2;



    //set variables
    Log log;
    View view;
    NumberPicker NumberPicker1, NumberPicker2;
    TextView selectPrimeNumber1, selectPrimeNumber2, Composite;
    TextView ShorStep1, ShorStep1Explanation;
    TextView Shor2point1, ShorStep2, ShorStep2Explanation, ShorStep2Formula, warning1, Shor2point2, warning2, Shor2point3;
    TextView Shor3point1, ShorStep3, ShorStep3Explanation, Shor3point2, warning3, Shor3point3;
    TextView Shor4point1, ShorStep4, ShorStep4Explanation, Shor4point2, Shor4point3, Shor4point4, ShorStepFinal;
    Button ShorToHome1, ShorToHome2;
    EditText SelectRandomK;
    TableLayout tableLayout;
    TextView rotation1, remainder1, rotation2, remainder2, rotation3, remainder3, rotationBeforeLast, remainderBeforeLast, rotationLast, remainderLast;
    String defaultShor3point2, defaultShor3point3, defaultShor4point3, defaultShor4point4;
    List<Integer> answer = new ArrayList<>();


    //set prime number strings
    final String[] primeNumbers = {"13" ,"17" ,"19","23","29","31","37","41","43","47"
            ,"53","59","61","67","71","73","79","83","89","97","101","103","107"
    };;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.fragment_shor1, container, true);
        loadObject();

        setHideStep2();
        setHideStep3();
        setHideStep4();

        NumberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hideAll();
                chosenNumber1 = Integer.parseInt(primeNumbers[newVal]);
                selectPrimeNumber1.setText("Select Your First Prime Number " + primeNumbers[newVal]);

            }
        });

        NumberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hideAll();
                chosenNumber2 = Integer.parseInt(primeNumbers[newVal]);
                selectPrimeNumber2.setText("Select Your Second Prime Number " + primeNumbers[newVal]);

            }
        });

        SelectRandomK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                setHideStep2();
                setHideStep3();
                setHideStep4();

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


        return null;
    }





    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }



    public void LaunchShorFragment() {
        replaceFragment(new shorFragment1());
    }
    public void LaunchIntroFragment() {
        replaceFragment(new fragment1());
    }

    public void loadObject() {

        ShorToHome1 = view.findViewById(R.id.ShorToHome1);
        ShorToHome2 = view.findViewById(R.id.ShorToHome2);

        NumberPicker1 = (NumberPicker) view.findViewById(R.id.NumberPicker1);
        NumberPicker1.setMinValue(0);
        NumberPicker1.setMaxValue(primeNumbers.length - 1);
        NumberPicker1.setDisplayedValues(primeNumbers);
        NumberPicker2 = (NumberPicker) view.findViewById(R.id.NumberPicker2);
        NumberPicker2.setMinValue(0);
        NumberPicker2.setMaxValue(primeNumbers.length - 1);
        NumberPicker2.setDisplayedValues(primeNumbers);

        selectPrimeNumber1 = (TextView) view.findViewById(R.id.selectPrimeNumber1);
        selectPrimeNumber2 = (TextView) view.findViewById(R.id.selectPrimeNumber2);
        Composite = (TextView) view.findViewById(R.id.Composite);

        ShorStep1 = (TextView) view.findViewById(R.id.ShorStep1);
        ShorStep1Explanation = (TextView) view.findViewById(R.id.ShorStep1Explanation);

        Shor2point1 = (TextView) view.findViewById(R.id.Shor2point1);
        ShorStep2 = (TextView) view.findViewById(R.id.ShorStep2);
        ShorStep2Explanation = (TextView) view.findViewById(R.id.ShorStep2Explanation);
        ShorStep2Formula = (TextView) view.findViewById(R.id.ShorStep2Formula);
        warning1 = (TextView) view.findViewById(R.id.warning1);
        Shor2point2 = (TextView) view.findViewById(R.id.Shor2point2);
        warning2 = (TextView) view.findViewById(R.id.warning2);
        Shor2point3 = (TextView) view.findViewById(R.id.Shor2point3);

        Shor3point1 = (TextView) view.findViewById(R.id.Shor3point1);
        ShorStep3 = (TextView) view.findViewById(R.id.ShorStep3);
        ShorStep3Explanation = (TextView) view.findViewById(R.id.ShorStep3Explanation);
        Shor3point2 = (TextView) view.findViewById(R.id.Shor3point2);
        defaultShor3point2 = Shor3point2.getText().toString();
        warning3 = (TextView) view.findViewById(R.id.warning3);
        Shor3point3 = (TextView) view.findViewById(R.id.Shor3point3);
        defaultShor3point3 = Shor3point3.getText().toString();

        Shor4point1 = (TextView) view.findViewById(R.id.Shor4point1);
        ShorStep4 = (TextView) view.findViewById(R.id.ShorStep4);
        ShorStep4Explanation = (TextView) view.findViewById(R.id.ShorStep4Explanation);
        Shor4point2 = (TextView) view.findViewById(R.id.Shor4point2);
        Shor4point3 = (TextView) view.findViewById(R.id.Shor4point3);
        defaultShor4point3 = Shor4point3.getText().toString();
        Shor4point4 = (TextView) view.findViewById(R.id.Shor4point4);
        defaultShor4point4 = Shor4point4.getText().toString();
        ShorStepFinal = (TextView) view.findViewById(R.id.ShorStepFinal);

        view.findViewById(R.id.ShorToHome1);
        view.findViewById(R.id.ShorToHome2);

        SelectRandomK = view.findViewById(R.id.SelectRandomK);
        tableLayout = view.findViewById(R.id.tableLayout);

        rotation1 = (TextView) view.findViewById(R.id.rotation1);
        remainder1 = (TextView) view.findViewById(R.id.remainder1);
        rotation2 = (TextView) view.findViewById(R.id.rotation2);
        remainder2 = (TextView) view.findViewById(R.id.remainder2);
        rotation3 = (TextView) view.findViewById(R.id.rotation3);
        remainder3 = (TextView) view.findViewById(R.id.remainder3);
        rotationBeforeLast = (TextView) view.findViewById(R.id.rotationBeforeLast);
        remainderBeforeLast = (TextView) view.findViewById(R.id.remainderBeforeLast);
        rotationLast = (TextView) view.findViewById(R.id.rotationLast);
        remainderLast = (TextView) view.findViewById(R.id.remainderLast);


    }

    public void setHideStep2(){

    }

    public void setHideStep3(){

    }

    public void setHideStep4(){

    }

}