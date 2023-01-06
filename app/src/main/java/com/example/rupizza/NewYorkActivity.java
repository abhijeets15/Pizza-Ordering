/**
 * This is the class for controlling whatever happens in New York style
 * Helps order pizza of type New York style.
 * @author Abhijeet Singh, Khizar Saud
 */

package com.example.rupizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewYorkActivity extends AppCompatActivity {

    ListView listView;

    ListView listView2;
    ListView listView3;
    TextView priceText;


    TextView crustText;

    TextView selectedItems;

    TextView ChiCityText;
    private ImageView imageView3;

    private Button toppingsButton;

    private Button orderButton;

    private ArrayList <Topping> selectedToppings2=new ArrayList<>();

    String size;

    String type;
    private boolean BYO;

    private boolean mainAct;
    double price;

    String[] toppingChoices;

    int numToppings;

    static double TOPPING_PRICE = 1.59;

    private boolean Chicity;

    /**
     * This is a class that is an instance of creation for New York activity.
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);
        imageView3=findViewById(R.id.imageView);
        listView3=findViewById(R.id.listview3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        price = 0.00;
        size = "";
        type = "crust";
        numToppings = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String size2 = extras.getString("size");
            double price2 = extras.getDouble("price", 0.00);
            String type2 = extras.getString("type");
            toppingChoices = extras.getStringArray("toppingSelection");
            Chicity = extras.getBoolean("Chicago");
            BYO = extras.getBoolean("BYO");
            mainAct = extras.getBoolean("main");
            price = price2;
            size = size2;
            if(mainAct != true){
                type = "Build Your Own";
            }
        }

        ChiCityText = (TextView)findViewById(R.id.ChiCityText);
        if (Chicity == true){
            ChiCityText.setText("Chicago Pizza");
        }

        else{
            ChiCityText.setText("New York Pizza");
        }

        priceText = (TextView)findViewById(R.id.priceField);

        crustText = (TextView)findViewById(R.id.crustText);

        selectedItems = (TextView)findViewById(R.id.selectedFromMenu);



        if(toppingChoices != null){
            numToppings = toppingChoices.length;
            price += (TOPPING_PRICE*numToppings);
            Toast.makeText(NewYorkActivity.this,
                    numToppings + "",
                    Toast.LENGTH_SHORT).show();
        }

        priceText.setText(String.format("%.2f", price));

        crustText.setText(setCrust());

        String display = "";

        if(toppingChoices != null){
            for(int i = 0; i < toppingChoices.length; i++){
                display += toppingChoices[i] + " ";
            }
        }

        selectedItems.setText(display);

        listView = (ListView)findViewById(R.id.listview);

        orderButton = (Button)findViewById(R.id.orderButton);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Small");
        arrayList.add("Medium");
        arrayList.add("Large");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView2 = (ListView)findViewById(R.id.listview2);

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Build Your Own");
        arrayList2.add("BBQ Chicken");
        arrayList2.add("Deluxe");
        arrayList2.add("Meatzza");

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList2);

        listView2.setAdapter(arrayAdapter2);

        toppingsButton = (Button) findViewById(R.id.toppingsButton);
        toppingsButton.setEnabled(false);


        toppingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openToppings();
            }
        });
 ;

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size == null){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewYorkActivity.this);
                    alertDialog.setTitle("Must Select a size");
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                    return;
                }
                if(!size.equals("") && !type.equals("crust")){
                    //System.out.println("bitch: " + type);
                    createPizza();
                    android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewYorkActivity.this);
                    alertDialog.setTitle("Sucessfully created pizza!");
                    alertDialog.setPositiveButton("OK",null);


                    alertDialog.show();
                    //openMain();
                }
                else{
                    if(size == ""){
                        Toast.makeText(NewYorkActivity.this, "Must pick size.",
                                Toast.LENGTH_SHORT).show();
                    }
                    if(type == ""){
                        Toast.makeText(NewYorkActivity.this, "Must pick Type.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                size = arrayAdapter.getItem(position).toString();
                Toast.makeText(NewYorkActivity.this,
                        "Size is: " + size, Toast.LENGTH_SHORT).show();
                if(size != null && type == "Build Your Own"){
                    toppingsButton.setEnabled(true);
                }
                getPrice();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                type = arrayAdapter2.getItem(position).toString();
                Toast.makeText(NewYorkActivity.this,
                        "Type is: " + type, Toast.LENGTH_SHORT).show();
                if(type=="Build Your Own"){
                    imageView3.setImageResource(R.drawable.byo);

                }
                else if(type== "BBQ Chicken"){
                    imageView3.setImageResource(R.drawable.bbqckpza);

                }
                else if(type=="Deluxe"){
                    imageView3.setImageResource(R.drawable.deluxe);

                }
                else if(type == "Meatzza"){
                    imageView3.setImageResource(R.drawable.meatzaa);
                }else{
                    imageView3.setImageResource(R.drawable.pcika);
                }

                if(size != "" && type == "Build Your Own"){
                    toppingsButton.setEnabled(true);
                }
                else{
                    toppingsButton.setEnabled(false);
                }
                getPrice();
                crustText.setText(setCrust());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method sets thew crust of the pizza based on the style.
     * @return returns String crust of pizza.
     */
    public String setCrust(){
        String newCrust = "Crust";
        if(type == "BBQ Chicken"){
            if(Chicity == true){
                newCrust = "Pan";
            }
            else{
                newCrust = "Thin";
            }
        }
        else if(type == "Build Your Own"){
            if(Chicity == true){
                newCrust = "Pan";
            }
            else{
                newCrust = "Hand tossed";
            }
        }
        else if(type == "Meatzza"){
            if(Chicity == true){
                newCrust = "Stuffed";
            }
            else{
                newCrust = "Hand tossed";
            }
        }
        else if(type == "Deluxe"){
            if(Chicity == true){
                newCrust = "Deep Dish";
            }
            else{
                newCrust = "Brooklyn";
            }
        }
        else{
            newCrust = "Crust";
        }
        return newCrust;
    }


    /**
     * This method opens the Toppings Activity class.
     */
    public void openToppings() {
        Intent intent = new Intent(this, ToppingsActivity.class);
        if(toppingChoices != null){
            price -= (toppingChoices.length)*TOPPING_PRICE;
        }
        intent.putExtra("size", size);
        intent.putExtra("price", price);
        intent.putExtra("type", type);
        intent.putExtra("Chicago", Chicity);
        startActivity(intent);
    }

    /**
     * This method serves the purpose of creating a new pizza once the order button is clicked.
     */
    public void createPizza(){
        ChicagoPizza factory1 = new ChicagoPizza();
        NYPizza factory2 = new NYPizza();
        if(Chicity == true){
            String crust = (String) crustText.getText();
            String style = type;
            Pizza pizza = null;
            ArrayList a = null;

            if(size.equalsIgnoreCase("Small")|| size.equalsIgnoreCase("Medium")  || size.equalsIgnoreCase("Large")){
                if(type == "BBQ Chicken"){
                    pizza = factory1.createBBQChicken();
                    a = BBQChicken.getToppings2();
                }
                else if(type == "Deluxe"){
                    pizza = factory1.createDeluxe();
                    a = Deluxe.getToppings2();
                }
                else if(type == "Meatzza"){
                    pizza = factory1.createMeatzza();
                    a = Meatzza.getToppings2();
                }

                else if(type == "Build Your Own"){
                    pizza = factory1.createBuildYourOwn();
                    if(toppingChoices != null){
                        selectedToppings2 = convertToppingStorage(toppingChoices);
                        pizza.add(selectedToppings2);
                    }
                }
                else{
                    Toast.makeText(NewYorkActivity.this,
                            "Must Select Crust", Toast.LENGTH_SHORT).show();
                }
                pizza.add(a);
                pizza.setSize(convertToSize(size));
                Order currentOrder = StoreOrder.getCurOrder();
                currentOrder.add(pizza);
                selectedToppings2.clear();
                resetPrice();
            }
            else{
                Toast.makeText(NewYorkActivity.this,
                        "Must Select Size", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            String crust = (String) crustText.getText();
            String style = type;
            Pizza pizza = null;
            ArrayList a = null;

            if(size.equalsIgnoreCase("Small")|| size.equalsIgnoreCase("Medium")  || size.equalsIgnoreCase("Large")){
                if(type == "BBQ Chicken"){
                    pizza = factory2.createBBQChicken();
                    a = BBQChicken.getToppings2();
                }
                else if(type == "Deluxe"){
                    pizza = factory2.createDeluxe();
                    a = Deluxe.getToppings2();
                }
                else if(type == "Meatzza"){
                    pizza = factory2.createMeatzza();
                    a = Meatzza.getToppings2();
                }

                else if(type == "Build Your Own"){
                    pizza = factory2.createBuildYourOwn();
                    if (toppingChoices != null) {
                        selectedToppings2 = convertToppingStorage(toppingChoices);
                        pizza.add(selectedToppings2);
                    }

                }
                else{
                    Toast.makeText(NewYorkActivity.this,
                            "Must Select Crust", Toast.LENGTH_SHORT).show();
                }
                pizza.add(a);
                pizza.setSize(convertToSize(size));
                Order currentOrder = StoreOrder.getCurOrder();
                currentOrder.add(pizza);
                selectedToppings2.clear();
                resetPrice();
            }
            else{
                Toast.makeText(NewYorkActivity.this,
                        "Must Select Size", Toast.LENGTH_SHORT).show();
            }

        }
        imageView3.setImageResource(R.drawable.pcika);



    }

    /**
     * This method resets the price of the pizza to 0.
     */
    public void resetPrice(){
        price = 0;
    }

    /**
     * This method converts size of type string to type size.
     * @param sizeOption The size option in string
     * @return size of type size.
     */
    public Size convertToSize(String sizeOption){
        Size finalSize = null;
        if(sizeOption.equals("Small")){
            finalSize = Size.small;
        }
        if(sizeOption.equals("Medium")){
            finalSize = Size.medium;
        }
        if(sizeOption.equals("Large")){
            finalSize = Size.large;
        }
        return finalSize;
    }

    /**
     * Returns the price of the pizza depending on what pizza is made
     * @return double price.
     */
    private String getPrice() {

        if (type == "Meatzza") {
            if (size == "Small"){

                price = 15.99;
                priceText.setText(String.valueOf(price));


            }
            else if (size == "Large") {

                price = 19.99;
                priceText.setText(String.valueOf(price));


            } else if (size == "Medium") {

                price = 17.99;
                priceText.setText(String.valueOf(price));


            }

        }
        else if(type == "Deluxe"){
            price=0;
            if (size == "Small"){

                price = 14.99;
                priceText.setText(String.valueOf(price));


            } else if (size == "Large") {

                price = 18.99;
                priceText.setText(String.valueOf(price));


            } else if (size == "Medium") {

                price = 16.99;
                priceText.setText(String.valueOf(price));


            }
        }
        else if(type == "BBQ Chicken"){
            price=0;
            if (size == "Small"){

                price = 13.99;
                priceText.setText(String.valueOf(price));


            } else if (size == "Large") {

                price = 17.99;
                priceText.setText(String.valueOf(price));


            } else if (size == "Medium") {

                price = 15.99;
                priceText.setText(String.valueOf(price));


            }
        }
        else if(type =="Build Your Own"){
            price=0;

            if (size == "Small"){

                price=8.99;
                priceText.setText(String.valueOf(price));

            } else if (size == "Large") {

                price = 12.99;
                priceText.setText(String.valueOf(price));

            } else if (size == "Medium") {

                price = 10.99;
                priceText.setText(String.valueOf(price));

            }
        }
        return String.valueOf(price);
    }

    /**
     * Returns the topping in arraylist.
     * @param toppings Toppings in string
     * @return toppings of type toppings in arraylist.
     */
    public ArrayList<Topping> convertToppingStorage(String[] toppings){
        ArrayList<Topping> finalTopping = new ArrayList<>();
        for(int i = 0; i < toppings.length; i++){
            if (toppings[i].equalsIgnoreCase("Sausage")) {
                finalTopping.add(Topping.Sausage);
            }
            if (toppings[i].equalsIgnoreCase("pepperoni")) {
                finalTopping.add(Topping.pepperoni);
            }
            if (toppings[i].equalsIgnoreCase("BBQ chicken")) {
                finalTopping.add(Topping.BBQ_chicken);
            }
            if (toppings[i].equalsIgnoreCase("green pepper")) {
                finalTopping.add(Topping.green_pepper);
            }
            if (toppings[i].equalsIgnoreCase("beef")) {
                finalTopping.add(Topping.beef);
            }
            if (toppings[i].equalsIgnoreCase("ham")) {
                finalTopping.add(Topping.ham);
            }
            if (toppings[i].equalsIgnoreCase("pineapple")) {
                finalTopping.add(Topping.pineapple);
            }
            if (toppings[i].equalsIgnoreCase("provolone")) {
                finalTopping.add(Topping.provolone);
            }
            if (toppings[i].equalsIgnoreCase("cheddar")) {
                finalTopping.add(Topping.cheadder);
            }
            if (toppings[i].equalsIgnoreCase("red pepper")) {
                finalTopping.add(Topping.red_pepper);
            }
            if (toppings[i].equalsIgnoreCase("onion")) {
                finalTopping.add(Topping.onion);
            }
            if (toppings[i].equalsIgnoreCase("mushrooms")) {
                finalTopping.add(Topping.mushrooms);
            }
            if (toppings[i].equalsIgnoreCase("spinach")) {
                finalTopping.add(Topping.spinach);
            }
            if (toppings[i].equalsIgnoreCase("feta cheese")) {
                finalTopping.add(Topping.feta_cheese);
            }
        }
        return finalTopping;
    }

    /**
     * Opens main activity.
     */
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}