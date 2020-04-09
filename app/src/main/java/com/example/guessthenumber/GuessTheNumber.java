package com.example.guessthenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GuessTheNumber extends AppCompatActivity {

    int low, high;
    int num;
    int k = 0;
    TextView suppose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number);

        int l = getIntent().getIntExtra("low", 0);
        int h = getIntent().getIntExtra("high", 0);

        low = Math.min(l, h);
        high = Math.max(l, h);

        num = (low + high) / 2;
        suppose = findViewById(R.id.suppose);
        suppose.setText("Это " + num + "?");

        k += 1;
    }


    public void clickYES(View view) {
        String step;
        if ((k % 10 == 1) && (k % 100 != 11)) {
            step = "шаг";
        } else if ((k % 10 > 1) && (k % 10 < 5) && !((k % 100 > 11) && (k % 100 < 15))) {
            step = "шага";
        } else step = "шагов";

        //диалог. поздравление
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("победа");
        builder.setIcon(R.drawable.prazdnik);
        builder.setMessage("Задуманное число - " + num + ". " + '\n' + "Угадано за " + k + " " + step);
        builder.setPositiveButton("ещё раз", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }

    public void clickNO(View view) {
        k++;
        findViewById(R.id.yesORno).setVisibility(View.GONE);
        findViewById(R.id.moreORless).setVisibility(View.VISIBLE);
    }


    public void clickLESS(View view) {

        if (num == low) {
            //диалог. предложить заново
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Кажется кто-то запутался в своих мыслях!");
            builder.setPositiveButton("заново", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        } else {
            high = num - 1;
            num = (low + high) / 2;

            suppose.setText("Это " + num + "?");
            findViewById(R.id.moreORless).setVisibility(View.GONE);
            findViewById(R.id.yesORno).setVisibility(View.VISIBLE);
        }
    }


    public void clickMORE(View view) {

        if (num == high) {
            //диалог. предложить заново
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("А вы точно помните, что загадывали?");
            builder.setPositiveButton("заново", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        } else {
            low = num + 1;
            num = (low + high) / 2;

            suppose.setText("Это " + num + "?");
            findViewById(R.id.moreORless).setVisibility(View.GONE);
            findViewById(R.id.yesORno).setVisibility(View.VISIBLE);
        }
    }
}
