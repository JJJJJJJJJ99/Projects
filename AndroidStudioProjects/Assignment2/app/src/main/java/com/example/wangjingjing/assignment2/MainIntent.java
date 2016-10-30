package com.example.wangjingjing.assignment2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;



public class MainIntent extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText(R.string.someText);
        final Button button1 = (Button) findViewById(R.id.button1);
        //Log.d("Create Button", "CREATED");

        // Next Page button
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.d("onClick", "GET IN");
                Intent myIntent = new Intent(MainIntent.this, Conversion.class);
                //Log.d("onClick", "CREATED NEW INTENT");
                startActivity(myIntent);


            }
        });

        // Update the last conversion
        Runnable run = new Runnable() {
            @Override
          public void run() {
               handler.post(new Runnable() {
                   public void run() {
                       TextView textView2 = (TextView) findViewById(R.id.textView2);
                       String result = SharedData.getInstance().getSharedData();
                       //There is always no whitespace at the end when using getResources().getString(R.string.lastConversion)
                       // Suppose to be "The last conversion is "
                       // Turned out to be "The last conversion is"
                       // Really annoying
                       textView2.setText("The last conversion is " + result);
                   }
               });
           }
        };
        Thread thr = new Thread(run);
        thr.start();
    }
}
