package com.example.rupizza;

/**
 This class is for BBQChicken pizza type.
 This type of Pizza has 4 selected toppings.
 This is a child class of Pizza class.
 @author Abhijeet Singh, Khizar Saud
 */


import java.util.ArrayList;
import java.util.List;

public class BBQChicken extends Pizza{
    private  Size size;
    //This is the basic method for pre selected topppings.
    private Crust crust;
    //  private Size size;
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    public static final double small = 13.99;
    public static final double medium = 15.99;
    public static final double large = 17.99;
    private double price;

    /**
     * Declares an object of type BBQ chicken. The constructor for the BBQ chicken.
     * @param crust
     * @param isChicago
     */
    public BBQChicken(Crust crust, boolean isChicago){
        super(crust, isChicago);

    }

    /**
     * This is to calculate the price of the pizza based on size.
     * @return final price of the pizza of type double.
     */
    public double price() {
        Size size = getSize();
        double price=0;

        if(size == Size.small){
            return price=small;
        }
        if(size == Size.medium){
            return price =medium;
        }if(size==Size.large){
            return price=large;
        }
        return price;
    }

    /**
     * Returns a string description of the pizza to display later.
     * Describes size and type of pizza with toppings.
     * @return String of pizza description
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Type : BBQ Chicken ");
        str.append(super.toString());
        return str.toString();
    }


    /**
     * @return what kind of crust the pizza is.
     */
    public Crust getCrust() {
        return crust;
    }


    /**
     * @return the price of the pizza
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return returns the toppings that are on the BBQ chicken pizza in an ArrayList.
     */
    public static ArrayList<Topping> getToppings2()
    {
        ArrayList<Topping> A = new ArrayList<Topping>();
        A.add(Topping.BBQ_chicken);
        A.add(Topping.green_pepper);
        A.add(Topping.provolone);
        A.add(Topping.cheadder);
        return A;
    }









}
