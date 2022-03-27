package com.example.calculating;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int first = 0;
    public int second = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstTT = (EditText) findViewById(R.id.persloginput);
        EditText secondTT = (EditText) findViewById(R.id.persloginput);
        //убирание текста перед самим вводом
        firstTT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    firstTT.setText("");
                }
            }
        });
        secondTT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    secondTT.setText("");
                }
            }
        });
    }

    public void plusik(View view){
        TextView firstTT = findViewById(R.id.persloginput);
        TextView secondTT = findViewById(R.id.vtorsloginput);
        //Поиск ошибок в вводе
        try {
            first = Integer.parseInt(String.valueOf(firstTT.getText()));
            second = Integer.parseInt(String.valueOf(secondTT.getText()));
            //запуск активити с ответом
            Intent intent = new Intent(this, otvetik.class);
            intent.putExtra("first", first);
            intent.putExtra("second",second);
            startActivity(intent);
        }
        catch (Exception ex){
            //ошибки с окном предупреждений
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка введены некоректные данные");
            builder.setMessage("попробуйте еще раз");
            builder.setPositiveButton("Ок", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }


    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putIntArray("nums", new int[] {first, second});
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("nums")) {
            int[] nums = savedInstanceState.getIntArray("nums");
            first = nums[0];
            second = nums[1];
            if (!(first == 0 && second == 0)){
                resetUI();
            }

        }
    }
    protected void resetUI(){
        TextView leftScore = findViewById(R.id.persloginput);
        leftScore.setText(String.valueOf(first));
        TextView rightScore = findViewById(R.id.vtorsloginput);
        rightScore.setText(String.valueOf(second));
    }
}