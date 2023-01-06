package com.example.rupizza;
/**
 * This class handles the orders cart
 * you are allowed to clear the pizzas in the orders, or place the order.
 * @author Abhijeet Singh, Khizar Saud
 */
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rupizza.Pizza;
import com.example.rupizza.StoreOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;
public class CartActivity extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    ArrayList<Pizza> listOfPizzas;



    RecyclerView cartofPizzas;
    Button clearCartButton, placeOrderButton;
    TextView orderNumber;
    static TextView subtotalP;
    static TextView salesxTax;
    static TextView cartTotalPrice;

    /**
     * This method initalizes the cart and sets the cart to its default state
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Connect dataypes to correct id's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderviewact);

        subtotalP = findViewById(R.id.subtotalP);
        salesxTax = findViewById(R.id.salesxTax);
        cartTotalPrice = findViewById(R.id.cartTotalPrice);
        orderNumber = findViewById(R.id.tvOrderNum);
        cartofPizzas = findViewById(R.id.pizzarecyle);
        clearCartButton = findViewById(R.id.btnClearCart);
        placeOrderButton = findViewById(R.id.btnPlaceOrder);

        listOfPizzas = new ArrayList<>();
        initalizeView();
        PizzaItemAdapter adapter = new PizzaItemAdapter(this, listOfPizzas);
        cartofPizzas.setAdapter(adapter);
        cartofPizzas.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Clear cart button; Clears the order inside the cart
        clearCartButton.setOnClickListener(new View.OnClickListener() {
            //Create Alertdialog to display to user
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);

            @Override
            public void onClick(View v) {

                try {

                    if (StoreOrder.getCurOrder().getPizzas().size() > 0) {
                        for (int i = 0; i <= StoreOrder.getCurOrder().getPizzas().size(); i++) {
                            StoreOrder.getCurOrder().getPizzas().clear();
                            listOfPizzas.clear();

                            adapter.notifyDataSetChanged();

                        }
                        adapter.notifyDataSetChanged();
                        update();
                    } else {
                        //if there are no pizzas in the order, the program will set a alert stating
                        alertDialog.setTitle("Cannot clear when no are in the pizza list!");
                        alertDialog.show();
                        return;

                    }
                } catch (Exception e) {
                    alertDialog.setTitle("Cleared cart!");
                }
            }
        });
        //Place order button creates the order.
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);

            @Override
            public void onClick(View v) {
                if(StoreOrder.getCurOrder().getPizzas().size() < 1){

                    alertDialog.setTitle("No pizzas in order");
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();

                    return;
                }
                StoreOrder.finish();
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Order created");
                alertDialog.setPositiveButton("OK",null);

                alertDialog.show();

                adapter.notifyDataSetChanged();
                initalizeView();
            }
        });

        update();
    }
    //For the back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Create the view
    private void initalizeView(){
       // StoreOrder.getCurOrder().getOrderID();
        orderNumber.setText("Order Number: " +StoreOrder.makeID());


        listOfPizzas.clear();
        listOfPizzas.addAll(StoreOrder.getCurOrder().getPizzas());
        update();
    }

//Update the subtotal,salestax,and cart total
    public static void update(){
        subtotalP.setText(df.format(StoreOrder.getCurOrder().getSubTotal()));
        salesxTax.setText(df.format(StoreOrder.getCurOrder().getTax()));
        cartTotalPrice.setText(df.format(StoreOrder.getCurOrder().getTotal()));
    }
}