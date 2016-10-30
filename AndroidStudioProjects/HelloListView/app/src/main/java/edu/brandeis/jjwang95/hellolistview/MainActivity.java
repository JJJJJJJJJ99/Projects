package edu.brandeis.jjwang95.hellolistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpenseTracker adapter = new ExpenseTracker(getApplicationContext(), Storage.getInstance().getList());

        ListView expenseList = (ListView) findViewById(R.id.listView_list);
        expenseList.setAdapter(adapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_button:
                startActivity(new Intent(this, ExpenseActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
