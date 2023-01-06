/**
 * This is the Toppings Activity class where the topping sget picked.
 * Class uses Recycler View.
 * @author Abhijeet Singh, Khizar Saud.
 */

package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ToppingsActivity extends AppCompatActivity implements ToppingsRecyclerInterface{

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> toppings;
    private ToppingAdapter adapter;
    Button toppingItems;
    Button applyToppings;
    String size;
    double price;
    String type;
    boolean Chicity;

    /**
     * Creates the view of the Toppings Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        size = intent.getStringExtra("size");
        price = intent.getDoubleExtra("price", 99.99);
        type = intent.getStringExtra("type");
        Chicity = intent.getBooleanExtra("Chicago", true);


        RecyclerView toppingRecycle = findViewById(R.id.toppingRecycle);
        toppingItems = findViewById(R.id.toppingItems);
        applyToppings = findViewById(R.id.applyToppings);
        List<Item> items = new ArrayList<>();
        items.add(new Item("Sausage", R.drawable.sausage));
        items.add(new Item("pepperoni", R.drawable.pepperoni));
        items.add(new Item("BBQ Chicken", R.drawable.bbqchicken));
        items.add(new Item("green pepper", R.drawable.greenpeppers));
        items.add(new Item("beef", R.drawable.beef));
        items.add(new Item("ham", R.drawable.ham));
        items.add(new Item("pineapple", R.drawable.pineapple));
        items.add(new Item("cheddar", R.drawable.cheadder));
        items.add(new Item("red pepper", R.drawable.redpeppers));
        items.add(new Item("onion", R.drawable.onion));
        items.add(new Item("mushrooms", R.drawable.mushrooms));
        items.add(new Item("spinach", R.drawable.spinach));
        items.add(new Item("feta cheese", R.drawable.fetacheese));

        adapter = new ToppingAdapter(getApplicationContext(), items, this);
        toppingRecycle.setLayoutManager(new LinearLayoutManager(this));
        toppingRecycle.setAdapter(new ToppingAdapter(getApplicationContext(), items, this));


        toppingItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected().size()>0){
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 0; i < adapter.getSelected().size(); i++){
                        stringBuilder.append(adapter.getSelected().size());
                        stringBuilder.append("\n");
                    }

                    Toast.makeText(ToppingsActivity.this,
                            stringBuilder.toString().trim(),
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ToppingsActivity.this,
                            "No Selection",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        applyToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChicago();
            }
        });



    }

    /**
     * Item click for when the toppings are selected.
     * @param position int of position of which item is clicked.
     */
    @Override
    public void onItemCLick(int position) {
        toppings = convert(adapter.getSelected());
    }

    public ArrayList<String> convert(List<Item> itemList){
        for(Item toppingItem : itemList){
            toppings.add(toppingItem.getTopping());
        }
        return toppings;
    }

    /**
     * Gets the toppings.
     * @return Toppings of type string.
     */
    public ArrayList<String> getToppings(){
        return toppings;
    }

    /**
     * Opens the chicago activity class.
     */
    public void openChicago() {
        Intent intent = new Intent(this, ChicagoPizzaActivity.class);
        intent.putExtra("size", size);
        intent.putExtra("price", price);
        intent.putExtra("type", type);
        intent.putExtra("toppingSelection", adapter.getSelectedConverted());
        intent.putExtra("Chicago", Chicity);
        String display = "";

        String [] test = adapter.getSelectedConverted();
        if(test != null){
            for(int i = 0; i < test.length; i++){
                display += test[i] + " ";
            }
            Toast.makeText(ToppingsActivity.this,
                    display + "",
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }


}