/**
 * This class is the main page of the app.
 * @author Abhijeet Singh, Khizar Saud
 */
package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button chicagoButton;
    private Button NYButton;
    private Button CartView;
    private Button StoreView;
    private boolean Chicity;
    private boolean mainAct;

    /**
     * Creation of the main page.
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chicagoButton = (Button) findViewById(R.id.chicago);
        NYButton = (Button) findViewById(R.id.newyork);
        CartView = findViewById(R.id.CartView);

        StoreView = findViewById(R.id.storeView);
        StoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this,StoreOrderActivity.class);
                startActivity(I);


            }
        });
        CartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });


        chicagoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Chicago selected!",
                        Toast.LENGTH_SHORT).show();
                mainAct=true;
                Chicity = true;
                openChicago();
            }
        });

        NYButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"NY selected!",
                        Toast.LENGTH_SHORT).show();
                mainAct=true;
                Chicity = false;
                openNewYork();
            }
        });

    }

    /**
     * Opens the cart activty class.
     */
    public void openVart(){
        Intent intent = new Intent(this,CartActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the Chicago activity class
     */
    public void openChicago() {
        Toast.makeText(MainActivity.this,"Chicago selected!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChicagoPizzaActivity.class);
        intent.putExtra("Chicago", Chicity);
        intent.putExtra("main",mainAct);
        startActivity(intent);
    }

    /**
     * Opens the New York Activity class.
     */
    public void openNewYork() {
        Toast.makeText(MainActivity.this,"NY selected!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NewYorkActivity.class);
        intent.putExtra("Chicago",Chicity);
        intent.putExtra("main",mainAct);

        startActivity(intent);
    }
}