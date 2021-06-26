package com.example.calculater;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity {

    Button num_0,num_1,num_2,num_3,num_4,num_5,num_6,num_7,num_8,num_9;
    Button clear_result,brackets,percent,divide,multiply,plus,minus,equal,into;
    EditText input,output;
    String number;
    double res1;
    boolean cheackBrackets =false;

    void store(){
        res1=Double.parseDouble(input.getText()+"");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        num_0 =  findViewById(R.id.num_0);
        num_1 =  findViewById(R.id.num_1);
        num_2 =  findViewById(R.id.num_2);
        num_3 =  findViewById(R.id.num_3);
        num_4 =  findViewById(R.id.num_4);
        num_5 =  findViewById(R.id.num_5);
        num_6 =  findViewById(R.id.num_6);
        num_7 =  findViewById(R.id.num_7);
        num_8 =  findViewById(R.id.num_8);
        num_9 =  findViewById(R.id.num_9);

        clear_result =  findViewById(R.id.clear_result);
        brackets =  findViewById(R.id.brackets);
        percent =  findViewById(R.id.percent);
        divide =  findViewById(R.id.divide);
        multiply =  findViewById(R.id.multiply);
        plus =  findViewById(R.id.plus);
        minus =  findViewById(R.id.minus);
        equal =  findViewById(R.id.equal);
        into =  findViewById(R.id.into);

        input =  findViewById(R.id.input);
        output=  findViewById(R.id.output);


        num_0.setOnClickListener(v -> input.setText(input.getText() + "0"));

        num_1.setOnClickListener(v -> input.setText(input.getText() + "1"));

        num_2.setOnClickListener(v -> input.setText(input.getText() + "2"));

        num_3.setOnClickListener(v -> input.setText(input.getText() + "3"));

        num_4.setOnClickListener(v -> input.setText(input.getText() + "4"));

        num_5.setOnClickListener(v -> input.setText(input.getText() + "5"));

        num_6.setOnClickListener(v -> input.setText(input.getText() + "6"));

        num_7.setOnClickListener(v -> input.setText(input.getText() + "7"));

        num_8.setOnClickListener(v -> input.setText(input.getText() + "8"));

        num_9.setOnClickListener(v -> input.setText(input.getText() + "9"));

        into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText()+".");
            }
        });

        plus.setOnClickListener(v -> {
            number=input.getText().toString();
            input.setText(number+"+");
        });

        minus.setOnClickListener(v -> {
            number=input.getText().toString();
            input.setText(number+"-");
        });

        multiply.setOnClickListener(v -> {
            number=input.getText().toString();
            input.setText(number+"x");
        });

        divide.setOnClickListener(v -> {
            number=input.getText().toString();
            input.setText(number+"÷");
        });

        percent.setOnClickListener(v -> {
            number=input.getText().toString();
            input.setText(number+"%");
        });

        brackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cheackBrackets){
                    number=input.getText().toString();
                    input.setText(number+")");
                    cheackBrackets=false;
                }else {
                    number=input.getText().toString();
                    input.setText(number+"(");
                    cheackBrackets=true;
                }
            }
        });

        equal.setOnClickListener(v -> {
            number=input.getText().toString();

            number=number.replaceAll("x","*");
            number=number.replaceAll("%","/100");
            number=number.replaceAll("÷","/");

            Context rhino=Context.enter();
            rhino.setOptimizationLevel(-1);

            String finalresult="";

            try{
                Scriptable scriptable=rhino.initStandardObjects();
                finalresult=rhino.evaluateString(scriptable,number,"javascript",1,null).toString();

            }catch (Exception e){
                finalresult="0";
            }
            output.setText(finalresult);
        });

        clear_result.setOnClickListener(v ->{
            input.setText(null);
            output.setText(null);
        });
    }
}
