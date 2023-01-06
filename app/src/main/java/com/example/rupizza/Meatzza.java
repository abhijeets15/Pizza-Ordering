package com.example.rupizza;

/**
 This class is for BBQChicken pizza type.
 This type of Pizza has 4 selected toppings.
 This is a child class of Pizza class.
 @author Abhijeet Singh, Khizar Saud
 */

import java.util.ArrayList;
import java.util.List;

public class Meatzza extends Pizza{
    private Crust crust;
    private  Size size;
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private ArrayList<Topping> toppings2= new ArrayList<Topping>();
    private double price;
    public static final double small = 15.99;
    public static final double medium = 17.99;
    public static final double large = 19.99;
    /**
     * Returns crust type of the Pizza
     * @return crust of pizza.
     */
    public Crust getCrust() {
        return crust;
    }


    public static ArrayList<Topping> getToppings2()
    {
        ArrayList<Topping> A = new ArrayList<Topping>();
        A.add(Topping.Sausage);
        A.add(Topping.pepperoni);
        A.add(Topping.beef);
        A.add(Topping.ham);
        return A;
    }

    /**
     * Returns a list of the toppings of the pizza.
     * @return list of toppings on pizza.
     */



    /**
     * Returns the price of the pizza.
     * @return price of the pizza in double.
     */
    @Override
    public double price() {
        Size size = getSize();
        double price=0;

        if(size == Size.small){
            return price =small;
        }
        if(size == Size.medium){
            return price= medium;
        }
        return price=19.99;
    }


    /**
     * Declares an object of type Meatzza. The constructor for the Deluxe.
     * @param crust
     * @param isChicago
     */
    public Meatzza(Crust crust, boolean isChicago){
        super(crust, isChicago);

    }

    /**
     * Returns the string description of the pizza
     * @return String description of the pizza.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Type : Meatzza ");
        str.append(super.toStringForMeatzaa());
        return str.toString();
    }







}
