package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Resources r;
    EditText low;
    EditText high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = getResources();
        low = findViewById(R.id.low);
        high = findViewById(R.id.high);
    }

    public void onClick(View view) {
        String l = low.getText().toString();
        String h = high.getText().toString();
        if (l.equals("") || h.equals("")) {
            Toast.makeText(getBaseContext(), "Задайте дипазон для угадывания", Toast.LENGTH_SHORT).show();
        } else {
            int low = Integer.parseInt(l);
            int high = Integer.parseInt(h);

            Intent intent = new Intent(this, GuessTheNumber.class);
            intent.putExtra("low", low);
            intent.putExtra("high", high);
            startActivity(intent);
        }
    }


}
