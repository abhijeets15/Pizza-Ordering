/**
 * This is the store order activity controller which is for the store order page.
 * This holds all the orders from the store.
 * @author Abhijeet Singh, Khizar Saud
 */

package com.example.rupizza;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class StoreOrderActivity extends AppCompatActivity {
    private TextView textView5;
    private Spinner orderNumberSpinner;
    private TextView orderTotalTextView;
    private ListView orderViewListView;
    private Order order;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private void update(){
        ArrayAdapter<Pizza> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order.getPizzas());
        orderViewListView.setAdapter(arrayAdapter);
    }


    /**
     * This method is to clear the list.
     */
    private void clearList(){
        order = null;
        ArrayAdapter<Pizza> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        orderViewListView.setAdapter(arrayAdapter);
        orderTotalTextView.setText("0.00");

    }

    /**
     * This is to initialize the method.
     */
    private void init(){
        ArrayAdapter<Integer> orderNumAdapter = new ArrayAdapter<>(StoreOrderActivity.this, android.R.layout.simple_spinner_dropdown_item, StoreOrder.getOrderIDs());
        orderNumAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(orderNumAdapter);

        if(StoreOrder.getOrderIDs().size() > 0){
            orderNumberSpinner.setSelection(0);
            order = StoreOrder.getOrder(Integer.parseInt(orderNumberSpinner.getSelectedItem().toString()));
            update();
        }
        else{
            clearList();
        }
    }


    /**
     * Creates the veiw of the Store Order Activity.
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        ActionBar actionBar = getSupportActionBar();
        textView5=findViewById(R.id.textView5);
        actionBar.setDisplayHomeAsUpEnabled(true);
        textView5.setText("Order Number:");
        orderNumberSpinner = findViewById(R.id.orderNumberSpinner);
        ImageView cancelButtn = findViewById(R.id.btnCancelOrder);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        orderViewListView = findViewById(R.id.orderViewListView);

        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                order = StoreOrder.getOrder(Integer.parseInt(orderNumberSpinner.getSelectedItem().toString()));
                orderTotalTextView.setText(df.format(order.getTotal()));
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                ArrayList<Integer> orderNums = StoreOrder.getOrderIDs();
                if(orderNums.size() > 0){
                    orderNumberSpinner.setSelection(0);
                    order = StoreOrder.getOrder(Integer.parseInt(orderNumberSpinner.getSelectedItem().toString()));
                }
            }
        });

        cancelButtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(orderNumberSpinner.getSelectedItem() == null){
                    android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(StoreOrderActivity.this);
                    alertDialog.setTitle("No orders to delete!");
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                    return;
                }


                int orderNum = Integer.parseInt(orderNumberSpinner.getSelectedItem().toString());
                android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(StoreOrderActivity.this);
                alertDialog.setTitle("Order number:  " + orderNum + " " + "Successfully deleted!");
                alertDialog.setMessage("Order details:" + StoreOrder.getOrder(orderNum).orderDetails());
                StoreOrder.cancel(orderNum);

                alertDialog.setPositiveButton("OK",null);

                alertDialog.show();

               init();
            }
        });
        init();
    }

    /**
     * This returns true or false for if options are selected.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





}