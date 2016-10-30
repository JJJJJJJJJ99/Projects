package edu.brandeis.jjwang95.hellolistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by WangJingjing on 10/19/16.
 */

public class ExpenseActivity extends AppCompatActivity {
    String toStore;
    EditText amount;
    EditText note;
    final ExpenseLog log = new ExpenseLog();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add);

        amount = (EditText) findViewById(R.id.editText_amount);
        note = (EditText) findViewById(R.id.editText_note);


        final Button button_save = (Button) findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                log.setNote(note.getText().toString());
                //Log.d("Note", note.getText().toString());
                log.setAmount(amount.getText().toString());
                log.setTime();
                //Log.d("Amount", amount.getText().toString());
                Intent myIntent = new Intent(ExpenseActivity.this, MainActivity.class);
                //myIntent.putExtra("Log", log);
                Storage.getInstance().addLog(log);
                ExpenseActivity.this.startActivity(myIntent);

            }
        });

        final Button button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent myIntent = new Intent(ExpenseActivity.this, MainActivity.class);
                ExpenseActivity.this.startActivity(myIntent);

            }
        });
    }
}
