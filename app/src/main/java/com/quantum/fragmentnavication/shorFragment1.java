package com.quantum.fragmentnavication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.res.ColorStateList;
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

import com.quantum.fragmentnavication.R;

import java.math.BigInteger;
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
    int composite_int;
    TextView ShorStep1, ShorStep1Explanation, warning0, GCD, ShorStep1Explanation2;
    TextView Shor2point1, ShorStep2, ShorStep2Explanation, ShorStep2Formula, Shor2point2, warning2, Shor2point3, Step2CounterWarning;
    TextView Shor3point1, ShorStep3, ShorStep3Explanation, Shor3point2, warning3, Shor3point3, Step3CounterWarning;
    TextView Shor4point1, ShorStep4, ShorStep4Explanation, Shor4point2, Shor4point3, Shor4point4, ShorStepFinal;
    Button ShorToHome1, ShorToHome2;
    EditText SelectRandomK;
    TableLayout tableLayout;

    TextView conclusion1,conclusion2, conclusion3, conclusion4, conclusion5, conclusion6;
    TextView rotation1, remainder1, rotation2, remainder2, rotation3, remainder3, rotationBeforeLast, remainderBeforeLast, rotationLast, remainderLast, rotation_mid, remainder_middle, count_mid, countbfl, countlast;
    int int_rotation1, int_remainder1, int_rotation2, int_remainder2, int_rotation3, int_remainder3, int_rotationBeforeLast, int_remainderBeforeLast, int_rotationLast, int_remainderLast, int_remainderBeforeBeforeLast, int_remainder_middle;
    String defaultShor3point2, defaultShor3point3, defaultShor4point3, defaultShor4point4;
    List<Integer> answer = new ArrayList<>();

    int numberInput;


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

        setHideAll();
        setHideStep1();
        setHideStep2();
        setHideStep3();
        setHideStep4();
        setHideConclusion();
        int whiteColor = getResources().getColor((R.color.white));
        SelectRandomK.setBackgroundTintList(ColorStateList.valueOf(whiteColor));

        NumberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hideAll();
                chosenNumber1 = parseInt(primeNumbers[newVal]);
                selectPrimeNumber1.setText("Select Your First Prime Number: " + primeNumbers[newVal]);
                if (chosenNumber1 != 0 && chosenNumber2 != 0){
                    composite_int = chosenNumber1 * chosenNumber2;
                    Composite.setText(String.valueOf(composite_int));
                    ShorStep1Explanation.setText("Randomly choose an integer (k) between 1 and " + composite_int);
                    setHideAll();
                    setShowStep1();
                }
            }
        });

        NumberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hideAll();
                chosenNumber2 = parseInt(primeNumbers[newVal]);
                selectPrimeNumber2.setText("Select Your Second Prime Number: " + primeNumbers[newVal]);
                if (chosenNumber1 != 0 && chosenNumber2 != 0){
                    composite_int = chosenNumber1 * chosenNumber2;
                    Composite.setText(String.valueOf(composite_int));
                    ShorStep1Explanation.setText("Randomly choose an integer (k) between 1 and " + composite_int);
                    setHideAll();
                    setShowStep1();
                }
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
                setHideConclusion();
                warning0.setVisibility(View.GONE);
                if(s.toString().trim().matches("[0-9]+") && s.toString().trim().length() > 1) {
                    numberInput = Integer.parseInt(s.toString().trim());
                }
                else {
                    numberInput = 1;
                }


                if (s.length()>0 && numberInput >= composite_int) {
                    SelectRandomK.setText(String.valueOf(composite_int-1));
                }
                else if (gcd(numberInput, composite_int) != 1) {
                    warning0.setVisibility(View.VISIBLE);
                    GCD.setVisibility(View.VISIBLE);
                    GCD.setText("GCD = " + gcd(numberInput, composite_int));
                }
                else if (gcd(numberInput, composite_int) == 1) {
                    GCD.setVisibility(View.VISIBLE);
                    GCD.setText("GCD = " + gcd(numberInput, composite_int));
                    ShorStep1Explanation2.setVisibility(View.VISIBLE);
                    warning0.setVisibility(View.GONE);
                    setShowStep2();
                    step2();
                }



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
        ShorStep1Explanation2 = (TextView) view.findViewById(R.id.ShorStep1Explanation2);

        warning0 = (TextView) view.findViewById(R.id.warning0);
        GCD = (TextView) view.findViewById(R.id.GCD);

        Shor2point1 = (TextView) view.findViewById(R.id.Shor2point1);
        ShorStep2 = (TextView) view.findViewById(R.id.ShorStep2);
        ShorStep2Explanation = (TextView) view.findViewById(R.id.ShorStep2Explanation);
        ShorStep2Formula = (TextView) view.findViewById(R.id.ShorStep2Formula);
        Shor2point2 = (TextView) view.findViewById(R.id.Shor2point2);
        warning2 = (TextView) view.findViewById(R.id.warning2);
        Shor2point3 = (TextView) view.findViewById(R.id.Shor2point3);
        Step2CounterWarning = (TextView) view.findViewById(R.id.Step2CounterWarning);

        Shor3point1 = (TextView) view.findViewById(R.id.Shor3point1);
        ShorStep3 = (TextView) view.findViewById(R.id.ShorStep3);
        ShorStep3Explanation = (TextView) view.findViewById(R.id.ShorStep3Explanation);
        Shor3point2 = (TextView) view.findViewById(R.id.Shor3point2);
        defaultShor3point2 = Shor3point2.getText().toString();
        warning3 = (TextView) view.findViewById(R.id.warning3);
        Shor3point3 = (TextView) view.findViewById(R.id.Shor3point3);
        defaultShor3point3 = Shor3point3.getText().toString();
        Step3CounterWarning = view.findViewById(R.id.Step3CounterWarning);

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
        rotation_mid = (TextView) view.findViewById(R.id.rotation_mid);
        remainder_middle = (TextView) view.findViewById(R.id.remainder_middle);
        count_mid = (TextView) view.findViewById(R.id.countmid);
        countbfl = (TextView) view.findViewById(R.id.countbfl);
        countlast = (TextView) view.findViewById(R.id.countlast);

        conclusion1 = (TextView) view.findViewById(R.id.conclusion1);
        conclusion2 = (TextView) view.findViewById(R.id.conclusion2);
        conclusion3 = (TextView) view.findViewById(R.id.conclusion3);
        conclusion4 = (TextView) view.findViewById(R.id.conclusion4);
        conclusion5 = (TextView) view.findViewById(R.id.conclusion5);
        conclusion6 = (TextView) view.findViewById(R.id.conclusion6);
    }


    public void setShowStep1(){
        ShorStep1.setVisibility(View.VISIBLE);
        ShorStep1Explanation.setVisibility(View.VISIBLE);
        SelectRandomK.setVisibility(View.VISIBLE);
        warning0.setVisibility(View.GONE);
        GCD.setVisibility(View.GONE);
        ShorStep1Explanation2.setVisibility(View.GONE);
    }
    public void setShowStep2(){
        Shor2point1.setVisibility(View.VISIBLE);
        ShorStep2.setVisibility(View.VISIBLE);
        ShorStep2Explanation.setVisibility(View.VISIBLE);
        ShorStep2Formula.setVisibility(View.VISIBLE);
        Shor2point2.setVisibility(View.VISIBLE);
        warning2.setVisibility(View.GONE);
        Shor2point3.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
        Step2CounterWarning.setVisibility(View.GONE);

    }

    public void setShowStep3(){
        Shor3point1.setVisibility(View.VISIBLE);
        ShorStep3.setVisibility(View.VISIBLE);
        ShorStep3Explanation.setVisibility(View.VISIBLE);
        Shor3point2.setVisibility(View.VISIBLE);
        warning3.setVisibility(View.GONE);
        Shor3point3.setVisibility(View.VISIBLE);
        Step3CounterWarning.setVisibility(View.GONE);

    }

    public void setShowStep4(){
        Shor4point1.setVisibility(View.VISIBLE);
        ShorStep4.setVisibility(View.VISIBLE);
        ShorStep4Explanation.setVisibility(View.VISIBLE);
        Shor4point2.setVisibility(View.VISIBLE);
        Shor4point3.setVisibility(View.VISIBLE);
        Shor4point4.setVisibility(View.VISIBLE);
        ShorStepFinal.setVisibility(View.VISIBLE);

    }

    public void setShowConclusion() {
        conclusion1.setVisibility(View.VISIBLE);
        conclusion2.setVisibility(View.VISIBLE);
        conclusion3.setVisibility(View.VISIBLE);
        conclusion4.setVisibility(View.VISIBLE);
        conclusion5.setVisibility(View.VISIBLE);
        conclusion6.setVisibility(View.VISIBLE);
        ShorToHome2.setVisibility(View.VISIBLE);
    }



    public void setHideAll(){
        ShorStep1.setVisibility(View.GONE);
        ShorStep1Explanation.setVisibility(View.GONE);
        ShorStep1Explanation2.setVisibility(View.GONE);
        SelectRandomK.setVisibility(View.GONE);
        GCD.setVisibility(View.GONE);

        Shor2point1.setVisibility(View.GONE);
        ShorStep2.setVisibility(View.GONE);
        ShorStep2Explanation.setVisibility(View.GONE);
        ShorStep2Formula.setVisibility(View.GONE);
        Shor2point2.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);
        Shor2point3.setVisibility(View.GONE);
        Step2CounterWarning.setVisibility(View.GONE);

        Shor3point1.setVisibility(View.GONE);
        ShorStep3.setVisibility(View.GONE);
        ShorStep3Explanation.setVisibility(View.GONE);
        Shor3point2.setVisibility(View.GONE);
        warning3.setVisibility(View.GONE);
        Shor3point3.setVisibility(View.GONE);
        tableLayout.setVisibility(View.GONE);
        Step3CounterWarning.setVisibility(View.GONE);

        Shor4point1.setVisibility(View.GONE);
        ShorStep4.setVisibility(View.GONE);
        ShorStep4Explanation.setVisibility(View.GONE);
        Shor4point2.setVisibility(View.GONE);
        Shor4point3.setVisibility(View.GONE);
        Shor4point4.setVisibility(View.GONE);
        ShorStepFinal.setVisibility(View.GONE);

        conclusion1.setVisibility(View.GONE);
        conclusion2.setVisibility(View.GONE);
        conclusion3.setVisibility(View.GONE);
        conclusion4.setVisibility(View.GONE);
        conclusion5.setVisibility(View.GONE);
        conclusion6.setVisibility(View.GONE);
        ShorToHome2.setVisibility(View.GONE);
        warning0.setVisibility(View.GONE);

        numberInput = 1;

    }
    public void setHideStep1() {
        ShorStep1.setVisibility(View.GONE);
        ShorStep1Explanation.setVisibility(View.GONE);
        ShorStep1Explanation2.setVisibility(View.GONE);
        SelectRandomK.setVisibility(View.GONE);
        warning0.setVisibility(View.GONE);
    }

    public void setHideStep2() {
        Shor2point1.setVisibility(View.GONE);
        ShorStep2.setVisibility(View.GONE);
        ShorStep2Explanation.setVisibility(View.GONE);
        ShorStep2Formula.setVisibility(View.GONE);
        Shor2point2.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);
        Shor2point3.setVisibility(View.GONE);
        tableLayout.setVisibility(View.GONE);
        Step2CounterWarning.setVisibility(View.GONE);
    }

    public void setHideStep3() {
        Shor3point1.setVisibility(View.GONE);
        ShorStep3.setVisibility(View.GONE);
        ShorStep3Explanation.setVisibility(View.GONE);
        Shor3point2.setVisibility(View.GONE);
        warning3.setVisibility(View.GONE);
        Shor3point3.setVisibility(View.GONE);
        Step3CounterWarning.setVisibility(View.GONE);

    }

    public void setHideStep4() {
        Shor4point1.setVisibility(View.GONE);
        ShorStep4.setVisibility(View.GONE);
        ShorStep4Explanation.setVisibility(View.GONE);
        Shor4point2.setVisibility(View.GONE);
        Shor4point3.setVisibility(View.GONE);
        Shor4point4.setVisibility(View.GONE);
        ShorStepFinal.setVisibility(View.GONE);
    }

    public void setHideConclusion() {
        conclusion1.setVisibility(View.GONE);
        conclusion2.setVisibility(View.GONE);
        conclusion3.setVisibility(View.GONE);
        conclusion4.setVisibility(View.GONE);
        conclusion5.setVisibility(View.GONE);
        conclusion6.setVisibility(View.GONE);
        ShorToHome2.setVisibility(View.GONE);
    }

    private int gcd(int one, int two){
        BigInteger ONE = new BigInteger(String.valueOf(one));
        BigInteger TWO = new BigInteger(String.valueOf(two));

        return ONE.gcd(TWO).intValue();
    }


    int q_value, repeating_rounds, latest_remainder;

    public void step2() {
        calculatingAllValues();
    }

    int p_position, p_repeat, the_remainder;
    public void step3() {
        p_position = repeating_rounds/2;
        p_repeat = 0;
        q_value = 1;
        while (p_repeat < p_position) {
            the_remainder = (q_value*numberInput)%composite_int;
            q_value = the_remainder;
            p_repeat++;
        }
        if (the_remainder + 1 == composite_int) {
            warning3.setVisibility(View.VISIBLE);
            Shor3point3.setText("\np + 1 = N"+"\n"+the_remainder+"+ 1 != "+composite_int );
        }
        else {
            Step3CounterWarning.setVisibility(View.VISIBLE);
            Shor3point2.setText("Position of p = " + p_position + "\n p = "+ the_remainder+ "\n");
            Shor3point3.setText("\np + 1 = N"+"\n"+the_remainder+"+ 1 != "+composite_int+ "\n");
            setShowStep4();
            step4();
        }

    }

    int answer1, answer2;
    public void step4(){
        answer1 = gcd(the_remainder+1, composite_int);
        Shor4point3.setText("f₁ = GCD (p+1, N)\n= GCD ("+(the_remainder+1)+","+composite_int+") \n= " +answer1);
        answer2 = gcd(the_remainder-1, composite_int);
        Shor4point4.setText("f₂ = GCD (p-1, N)\n= GCD ("+(the_remainder-1)+","+composite_int+") \n= "+answer2);
        setShowConclusion();
    }

    int int_beforemiddle;

    public void calculatingAllValues(){
        answer.clear();
        q_value = 1;
        latest_remainder = 0;
        repeating_rounds = 0;

        int_remainder1 = 0;
        int_remainder2 = 0;
        int_remainder3 = 0;
        int_remainderBeforeBeforeLast = 0;
        int_remainderBeforeLast = 0;
        int_remainderLast = 0;
        int_beforemiddle = 0;
        int_remainder_middle = 0;
        warning2.setVisibility(view.GONE);
        Step2CounterWarning.setVisibility(view.GONE);

        while (latest_remainder != 1) {
            latest_remainder = (q_value*numberInput)%composite_int;
            if (repeating_rounds == 0){
                rotation1.setText("("+q_value+"*"+numberInput+") % "+composite_int);
            }
            if (repeating_rounds == 1) {
                rotation2.setText("("+q_value+"*"+numberInput+") % "+composite_int);
            }
            if (repeating_rounds == 2) {
                rotation3.setText("("+q_value+"*"+numberInput+") % "+composite_int);
            }
            q_value = latest_remainder;
            repeating_rounds++;
            answer.add(q_value);


        }
        if (latest_remainder == 1 && repeating_rounds > 1) {

            if (repeating_rounds%2 != 0) {
                warning2.setVisibility(View.VISIBLE);
            }
            else {
                Step2CounterWarning.setVisibility(View.VISIBLE);
                setShowStep3();
                step3();
            }
        }


        if (answer.size() == 1) {
            int_remainder1 = answer.get(0);
        }
        else if (answer.size() == 2) {
            int_remainder1 = answer.get(0);
            int_remainder2 = answer.get(1);
            rotation1.setText("1");
            rotation2.setText("2");
        }
        else if (answer.size() == 3) {
            int_remainder1 = answer.get(0);
            int_remainder2 = answer.get(1);
            int_remainder3 = answer.get(2);
        }
        else if (answer.size() == 4) {
            int_remainder1 = answer.get(0);
            int_remainder2 = answer.get(1);
            int_remainder3 = answer.get(2);
            int_remainderLast = answer.get(3);
            rotationLast.setText("("+int_remainder3+"*"+numberInput+") % "+composite_int);
            countlast.setText("--- "+(answer.size()));
        }
        else if (answer.size() > 4) {
            int_remainder1 = answer.get(0);
            int_remainder2 = answer.get(1);
            int_remainder3 = answer.get(2);
            int_remainderBeforeLast = answer.get(answer.size()-2);
            int_remainderLast = answer.get(answer.size()-1);
            rotationLast.setText("("+int_remainderBeforeLast+"*"+numberInput+") % "+composite_int);
            if (answer.size() > 5) {
                int_remainderBeforeBeforeLast = answer.get(answer.size()-3);
                rotationBeforeLast.setText(("("+int_remainderBeforeBeforeLast+"*"+numberInput+") % "+composite_int));
            }
            else if (answer.size() == 5) {
                rotationBeforeLast.setText(("("+int_remainder3+"*"+numberInput+") % "+composite_int));
            }
            countbfl.setText("--- "+ (answer.size()-1));
            countlast.setText("--- "+ (answer.size()));
        }

        if (int_remainder1 != 0) {
            remainder1.setText(String.valueOf(int_remainder1));
        }
        else {
            remainder1.setText("-");
        }

        if (int_remainder2 != 0) {
            remainder2.setText(String.valueOf(int_remainder2));
        }
        else {
            remainder2.setText("-");
        }

        if (int_remainder3 != 0) {
            remainder3.setText(String.valueOf(int_remainder3));
        }
        else {
            remainder3.setText("-");
        }

        if (int_remainderBeforeLast != 0) {
            remainderBeforeLast.setText(String.valueOf(int_remainderBeforeLast));
        }
        else {
            remainderBeforeLast.setText("-");
        }

        if (int_remainderLast != 0) {
            remainderLast.setText(String.valueOf(int_remainderLast));
        }
        else {
            remainderLast.setText("-");
        }


        if (repeating_rounds%2 == 0){
            int_beforemiddle = answer.get((answer.size()/2)-2);
            rotation_mid.setText("("+int_beforemiddle+"*"+numberInput+") % "+composite_int);
            int_remainder_middle = answer.get((answer.size()/2)-1);
            remainder_middle.setText(String.valueOf(int_remainder_middle));
            count_mid.setText("--- "+((answer.size()/2)));
        }


    }

}