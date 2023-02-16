package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView resultTV, solutionTV;
    MaterialButton buttonAC, buttonOpen, buttonClose;
    MaterialButton buttonDivide, buttonMultiply, buttonAdd, buttonSubtract, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonDot, buttonHistory;

    String calculateDataStr;
    String calculateData = "";

    protected void onSaveInstanceState (@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        if(solutionTV.getText().toString()!= null) {
            outState.putString("solution", solutionTV.getText().toString());
        }
        if(resultTV.getText().toString()!= null) {
            outState.putString("result", resultTV.getText().toString());
        }

    }


    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.get("solution")!= null) {
            solutionTV.setText(savedInstanceState.get("solution").toString());
        }
        if (savedInstanceState.get("result")!= null) {
            resultTV.setText(savedInstanceState.get("result").toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);
        buttonHistory = this.findViewById(R.id.button_history);

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calculateData == "") {
                    System.out.println("There's no calculated history");
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    if(calculateDataStr.contains("null")) {
                        calculateDataStr = calculateDataStr.substring(4);
                    }
                    intent.putExtra("calculateDataStr", calculateDataStr);
                    startActivity(intent);
                }
                }
        });

        assignId(buttonAC, R.id.button_ac);
        assignId(buttonOpen, R.id.button_open);
        assignId(buttonClose, R.id.button_close);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonSubtract, R.id.button_subtract);
        assignId(buttonEquals, R.id.button_equal);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot, R.id.button_dot);
    }


    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        } else if(buttonText.equals("=")){
            calculateData = dataToCalculate;
            String finalResult = getResult(dataToCalculate);
            calculateData += " = " + finalResult;
            calculateDataStr += calculateData + '\n';
            if(!finalResult.equals("Error")){
                resultTV.setText(finalResult);
            }
            return;
        } else {
            dataToCalculate += buttonText;
        }

        solutionTV.setText(dataToCalculate);
    }

    String getResult (String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}