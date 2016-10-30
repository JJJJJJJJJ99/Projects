package edu.brandeis.zichenghu.conversion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Conversion2 extends AppCompatActivity {
    private EditText input;
    private TextView result;
    Button clr,abt,rtn,convert;
    //private Double x,y;
    Double inputDouble;
    Double outputDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_2);

        clr = (Button) findViewById(R.id.clr);
        abt = (Button) findViewById(R.id.abt);
        rtn = (Button) findViewById(R.id.rtn);
        convert = (Button) findViewById(R.id.convert);
        input = (EditText) findViewById(R.id.textView_input);
        result = (TextView) findViewById(R.id.result);

        //x = Double.parseDouble(String.valueOf(input.getText()));

        Log.d("Got here", "yes");
        inputDouble = Double.parseDouble(input.getText().toString());

        outputDouble = (inputDouble - 32)/1.8;

        Log.d("Output Double", String.format("%.2f", outputDouble));


        Intent i = getIntent();
        //default string returned if the value is not there.
        String data = i.getExtras().getString("default", "");
        Log.d("Got Data", data);

        // Convert Button
        OnClickListener conOcl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("%.2f", outputDouble));
                Log.d("Converted", "lalalala");
            }
        };

        // Clear button
        OnClickListener clrOcl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                result.setText("");
            }
        };

        // About button
        OnClickListener abtOcl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new AlertDialog.Builder(Conversion2.this).create();
                builder.setMessage(getResources().getString(R.string.dialog_message));
                builder.setTitle(R.string.dialog_title);
                builder.show();
            }
        };

        // Return BUTTON
        /*OnClickListener rtnOcl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.setData(Uri.parse(result.getText().toString()));
                setResult(RESULT_OK,data);
                finish();
            }
        };*/


        clr.setOnClickListener(clrOcl);
        abt.setOnClickListener(abtOcl);
        //rtn.setOnClickListener(rtnOcl);
        convert.setOnClickListener(conOcl);

    }
}
