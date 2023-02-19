package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {
    TextView calculate;
    TextView resultDisplay;
    LinearLayout expandedLayout1;

    public void expand(View view){
        expandedLayout1 = findViewById(R.id.expandedLayout1);

        if(expandedLayout1.getVisibility() == view.GONE){
            expandedLayout1.setVisibility(view.VISIBLE);
        }
        else{
            expandedLayout1.setVisibility(view.GONE);
        }
    }

    public void tapped(View view){

        calculate = findViewById(R.id.calculate);
        resultDisplay = findViewById(R.id.resultDisplay);
        String str = calculate.getText().toString();


        if(view.getTag().toString().equals("C")){

            if(str.length() > 0 && !str.equals("")) {
                calculate.setText("");
                resultDisplay.setText("");
            }
            if(resultDisplay.length() > 0 && !resultDisplay.equals("")) {
                resultDisplay.setText("");
            }
        }

        else if(view.getTag().toString().equals("X")){

            if(str.length() > 0 && !str.equals("")) {
                str = str.substring(0, str.length() - 1);
                calculate.setText(str);
            }
        }

        else if(view.getTag().toString().equals("=")){
            String exp = calculate.getText().toString();
            if(exp.indexOf("ln") != -1){
                exp = exp.replaceAll("ln", "log");
            }

                if(!exp.equals("") && !exp.endsWith("+") && !exp.endsWith("/") && !exp.endsWith("*") && !exp.endsWith("-") && !exp.endsWith(".")) {
                    try {
                        Expression expression = new ExpressionBuilder(exp).build();
                        double result = expression.evaluate();
                        calculate.setText("");
                        resultDisplay.setTextSize(48);
                        resultDisplay.setText("=" + Double.toString(result));
                    }
                    catch (Exception e){
                        resultDisplay.setText("Error");
                    }
                }
                else{
                    resultDisplay.setText("Error");
                }

        }

        else{
                calculate.setText(calculate.getText() + view.getTag().toString());

            String exp = calculate.getText().toString();

            if(!view.getTag().toString().equals("/") && !view.getTag().toString().equals("*")
                    && !view.getTag().toString().equals("+") && !view.getTag().toString().equals("-")){
                if (resultDisplay.getText().toString().indexOf("=") == 0) {
                    try {
                        Expression expression = new ExpressionBuilder(exp).build();
                        double result = expression.evaluate();
                        resultDisplay.setText("=" + Double.toString(result));
                    }
                    catch(Exception e){
                        return;
                    }
                }
                else{
                    try {
                        Expression expression = new ExpressionBuilder(exp).build();
                        double result = expression.evaluate();
                        resultDisplay.setText("=" + Double.toString(result));
                    }
                    catch(Exception e) {
                        return;
                    }
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}