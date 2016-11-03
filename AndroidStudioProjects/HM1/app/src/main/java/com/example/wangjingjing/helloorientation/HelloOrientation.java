package com.example.wangjingjing.helloorientation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.lang.*;


// Never update UI on the main thread, create a new thread
public class HelloOrientation extends AppCompatActivity {

    TextView label;
    TextView hori_Mode;
    TextView verti_Mode;
    Boolean flag;
    EditText myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        if (d.getWidth() > d.getHeight()){
            setContentView(R.layout.horizontal);
            //Log.d("Horiznotal", "Ever goes into this");
            // No need
            //hori_Mode = (TextView) findViewById(R.id.textView_HoriMode);
        } else {
            setContentView(R.layout.activity_hello_orientation);
            // No need
            //verti_Mode = (TextView) findViewById(R.id.textView_VertiMode);
        }
        final Button button = (Button) findViewById(R.id.button1);
        flag = true;
        label = (TextView) findViewById(R.id.textView1);
        label.setText(getResources().getString(R.string.label));
        myTextView = (EditText) findViewById(R.id.editText1);
        myTextView.setText("");
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          if (flag){
                                              myTextView.setText(getResources().getString(R.string.myName));
                                              flag = false;
                                          } else {
                                              myTextView.setText("");
                                              flag = true;
                                          }
                                      }
                                  }
        );
    }

}
