/**
 *
 */

package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ToppingsActivityNY extends AppCompatActivity implements ToppingsRecyclerInterfaceNY{
    private ArrayList<ItemNY> items = new ArrayList<>();
    private ArrayList<String> toppings;
    private ToppingAdapterNY adapter;
    Button toppingItems;
    Button applyToppings;
    String size;
    double price;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings_ny);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        size = intent.getStringExtra("size");
        price = intent.getDoubleExtra("price", 99.99);
        type = intent.getStringExtra("type");

        RecyclerView toppingRecycleNY = findViewById(R.id.toppingRecycleNY);
        toppingItems = findViewById(R.id.toppingItemsNY);
        applyToppings = findViewById(R.id.applyToppingsNY);
        List<ItemNY> items = new ArrayList<>();
        items.add(new ItemNY("Sausage", R.drawable.sausage));
        items.add(new ItemNY("pepperoni", R.drawable.pepperoni));
        items.add(new ItemNY("BBQ Chicken", R.drawable.bbqchicken));
        items.add(new ItemNY("green pepper", R.drawable.greenpeppers));
        items.add(new ItemNY("beef", R.drawable.beef));
        items.add(new ItemNY("ham", R.drawable.ham));
        items.add(new ItemNY("pineapple", R.drawable.pineapple));
        items.add(new ItemNY("cheddar", R.drawable.cheadder));
        items.add(new ItemNY("red pepper", R.drawable.redpeppers));
        items.add(new ItemNY("onion", R.drawable.onion));
        items.add(new ItemNY("mushrooms", R.drawable.mushrooms));
        items.add(new ItemNY("spinach", R.drawable.spinach));
        items.add(new ItemNY("feta cheese", R.drawable.fetacheese));

        adapter = new ToppingAdapterNY(getApplicationContext(), items, this);
        toppingRecycleNY.setLayoutManager(new LinearLayoutManager(this));
        toppingRecycleNY.setAdapter(new ToppingAdapterNY(getApplicationContext(), items, this));

        toppingItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected().size()>0){
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 0; i < adapter.getSelected().size(); i++){
                        stringBuilder.append(adapter.getSelected().size());
                        stringBuilder.append("\n");
                    }

                    Toast.makeText(ToppingsActivityNY.this,
                            stringBuilder.toString().trim(),
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ToppingsActivityNY.this,
                            "No Selection",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        applyToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNY();
            }
        });



        System.out.println("test");
    }

    @Override
    public void onItemCLick(int position) {
        toppings = convert(adapter.getSelected());
    }

    public ArrayList<String> convert(List<ItemNY> itemList){
        for(ItemNY toppingItem : itemList){
            toppings.add(toppingItem.getTopping());
        }
        return toppings;
    }

    public ArrayList<String> getToppings(){
        return toppings;
    }
    public void openNY() {
        Intent intent = new Intent(this, NewYorkActivity.class);
        intent.putExtra("size", size);
        intent.putExtra("price", price);
        intent.putExtra("type", type);
        intent.putExtra("toppingSelection", adapter.getSelectedConverted());
        String display = "";

        String [] test = adapter.getSelectedConverted();
        if(test != null){
            for(int i = 0; i < test.length; i++){
                display += test[i] + " ";
            }
            Toast.makeText(ToppingsActivityNY.this,
                    display + "",
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }
}