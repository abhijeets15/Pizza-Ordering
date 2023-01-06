package com.example.rupizza;

/**
 This class is for Deluxe pizza type.
 This type of Pizza has 4 selected toppings.
 This is a child class of Pizza class.
 @author Abhijeet Singh, Khizar Saud
 */

import java.util.ArrayList;

public class Deluxe extends Pizza{
    private Crust crust;
    //private Size size;
    private ArrayList<Topping> toppings2=new ArrayList<Topping>();
    private double price;

    /**
     * Returns crust type of the Pizza
     * @return crust of pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Returns the string description of the pizza
     * @return String description of the pizza.
     */
    public String toString(){
        StringBuilder str = new StringBuilder("Type: Deluxe");
        str.append(super.toStringForDeluxe());
        return str.toString();
    }

    /**
     * Returns a list of the toppings of the pizza.
     * @return list of toppings on pizza.
     */
    public static ArrayList<Topping> getToppings2(){
        ArrayList<Topping> a = new ArrayList<Topping>();

        a.add(Topping.Sausage);
        a.add(Topping.pepperoni);
        a.add(Topping.green_pepper);
        a.add(Topping.onion);
        a.add(Topping.mushrooms);

        return a;
    }

    /**
     * Returns the price of the pizza.
     * @return double price of pizza.
     */
    public double getPrice() {
        return price;
    }


    /**
     * Declares an object of type Deluxe. The constructor for the Deluxe.
     * @param crust
     * @param isChicago
     */
    public Deluxe(Crust crust, boolean isChicago){
        super(crust, isChicago);

    }


    /**
     * Returns the price of the pizza.
     * @return price of the pizza in double.
     */
    @Override
    public double price() {
        Size size = getSize();

        double price=0;
        if(size==Size.small){
            price= 14.99;
        }
        if(size==Size.large){
            price=18.99;
        }
        if(size==Size.medium){
            price=16.99;
        }
        return price;
    }
}
