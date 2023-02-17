package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> hist_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.lichsu);
        Intent intent = getIntent();
        hist_calc = intent.getStringArrayListExtra("LS");
        String [] hist_calc2 = hist_calc.toArray(new String[hist_calc.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_main3, hist_calc2);
        listView.setAdapter(adapter);
    }
}