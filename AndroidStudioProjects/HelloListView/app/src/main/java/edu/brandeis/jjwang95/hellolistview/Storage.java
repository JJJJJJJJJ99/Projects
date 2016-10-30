package edu.brandeis.jjwang95.hellolistview;

import java.util.ArrayList;

/**
 * Created by WangJingjing on 10/24/16.
 */

public class Storage {
    private ArrayList<ExpenseLog> expenseArray = new ArrayList<ExpenseLog>();
    private static final Storage holder = new Storage();
    public void addLog(ExpenseLog expenseLog){
        expenseArray.add(expenseLog);
    }

    public ArrayList<ExpenseLog> getList(){
        return expenseArray;
    }
    public static Storage getInstance() {
        return holder;
    }
}
