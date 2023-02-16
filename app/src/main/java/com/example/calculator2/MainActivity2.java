package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    TextView editText;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(editText.getText().toString() != null) {
            outState.putString("calculateData", editText.getText().toString());
        }

    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.get("calculateData") != null) {
            editText.setText(savedInstanceState.get("calculateData").toString());
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.dataCalcHis);
        Intent intent = getIntent();

        String data = intent.getStringExtra("calculateDataStr");
        editText.setText(data);
    }
}