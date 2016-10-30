package edu.brandeis.zichenghu.conversion;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.Button;

public class Conversion extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        button = (Button) findViewById(R.id.button);

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Data to pass around";
                Log.d("OnClick", "GET IN");
                Intent i = new Intent(Conversion.this, Conversion2.class);
                Log.d("Intent", "Created");
                i.putExtra("stringData", data);
                Log.d("Put Extra", "Put");
                startActivity(i);
                Log.d("startActivity", "started");
            }

            /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode==123) {
                    if (resultCode == RESULT_OK) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Conversion.this);
                        builder.setMessage(R.string.dialog_message2+data.getData().toString())
                                .setTitle(R.string.dialog_title2);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }*/
        };

        button.setOnClickListener(ocl);
    }


}
