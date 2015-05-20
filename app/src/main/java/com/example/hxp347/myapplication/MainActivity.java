package com.example.hxp347.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reimaginebanking.api.java.Models.Customer;
import com.reimaginebanking.api.java.NessieClient;
import com.reimaginebanking.api.java.NessieException;
import com.reimaginebanking.api.java.NessieResultsListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.linear);
        String key = "c8401d90ec390300090ef500d40b3ebc";
        NessieClient nessieClient = NessieClient.getInstance();
        nessieClient.setAPIKey(key);
        nessieClient.getCustomers(new NessieResultsListener() {
            @Override
            public void onSuccess(Object o, NessieException e) {

                if(e == null){
                    ArrayList<Customer> customers = (ArrayList<Customer>) o;
                    for(Customer customer:customers){
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(customer.toString());
                        layout.addView(textView);
                    }

                }else {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(e.toString());
                    layout.addView(textView);
                }
            }
        });
//        nessieClient.getCustomers(new NessieResultsListener() {
//            @Override
//            public void onSuccess(Object result, NessieException e) {
//
//                if(e == null){
//                    ArrayList<ATM> atms = (ArrayList<ATM>) result;
//                    //System.out.println(atms.size());
//                    //System.out.println(atms.get(0).get_id());
//                    textView.setText(Integer.toString(atms.size()));
//                    //Log.v("TEST", Integer.toString(atms.size()));
//                }else {
//                    //System.out.println("THERE IS AN ERROR");
//                    //System.out.println(e.toString());
//                    textView.setText(e.toString());
//                    //Log.v("TEST", "WHAT");
//                }
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
