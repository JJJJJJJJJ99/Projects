package edu.brandeis.jjwang95.hellolistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WangJingjing on 10/20/16.
 */

public class ExpenseTracker extends BaseAdapter {
    private ArrayList<ExpenseLog> expenseArray;
    private Context context;

    public ExpenseTracker(Context context, ArrayList<ExpenseLog> arr){
        this.expenseArray = arr;
        this.context = context;
    }


    @Override
    public int getCount() {
        return expenseArray.size();
    }

    @Override
    public ExpenseLog getItem(int position) {
        return expenseArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpenseLog log = getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.expense_entry, null);

        }
        ExpenseLog item = getItem(position);
        if (item != null){

            TextView time = (TextView) convertView.findViewById(R.id.textView_time);
            TextView amount = (TextView) convertView.findViewById(R.id.textView_amount);
            TextView note = (TextView) convertView.findViewById(R.id.textView_note);

            if (time != null){
                time.setText(item.getTime());
            }
            if (amount != null){
                amount.setText(item.getAmount());
            }
            if (note != null){
                note.setText(item.getNote());
            }

            /*time.setText(log.getTime());
            amount.setText(log.getAmount());
            note.setText(log.getTime());*/
        }



        return convertView;
    }
}
