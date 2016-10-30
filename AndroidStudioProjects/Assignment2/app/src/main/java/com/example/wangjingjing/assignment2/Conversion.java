package com.example.wangjingjing.assignment2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.Toast;


public class Conversion extends AppCompatActivity {
    //Handler handler = new Handler();
    // For text in Toast
    String text;

    // For text to store
    String toStore;


    //Context context;


    EditText outputText;
    EditText inputText;


    Button convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_utility);

        inputText = (EditText) findViewById(R.id.input);
        outputText = (EditText) findViewById(R.id.output);
        convert = (Button) findViewById(R.id.button_convert);
        convert.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
                double inputValue = Double.parseDouble(inputText.getText().toString());
                outputText.setText(String.format("%.2f", (inputValue - 32.0)*5.0/9.0));
                toStore = String.format("%.2f", (inputValue - 32.0)*5.0/9.0);
                //Log.d("String to store", toStore);

            }
        }));


        // Button Back
        final Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SharedData.getInstance().storeData(toStore);
                Intent myIntent = new Intent(Conversion.this, MainIntent.class);
                Conversion.this.startActivity(myIntent);

            }
        });


        // Clear Button
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                inputText.setText("");
                outputText.setText("");

            }});


        // About Button
        text = "Celsius = (Fahrenheit - 32)*5/9";
        //context = getApplicationContext();
        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final AlertDialog alertDialog = new AlertDialog.Builder(Conversion.this).create();
                alertDialog.setTitle("About");
                alertDialog.setMessage(text);
                //Log.d("Dialog created", "Createddddddd");
                alertDialog.setButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                });
                alertDialog.show();

                /*int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }
        });
    }
}
