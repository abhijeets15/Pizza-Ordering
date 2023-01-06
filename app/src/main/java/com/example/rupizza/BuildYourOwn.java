package com.example.rupizza;

/**
 This class is for Build your own pizza type.
 This type of Pizza has up to 7 toppings.
 This is a child class of Pizza class.
 @author Abhijeet Singh, Khizar Saud
 */

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    public static final double small = 8.99;
    public static final double medium = 10.99;
    public static final double large = 12.99;
    public static final double pricePerTopping=1.59;
    private  Size size;
    private static double numOfTopping;
    //  private Size size;
    private Crust crust;
    private Topping toppings;


    //private int topping;
    double price=0;

    /**
     * Constructor for Build your own Pizza object which takes in crust type and if the pizza is chicago style.
     * @param crust
     * @param chicago
     */
    public BuildYourOwn(Crust crust, boolean chicago) {
        super(crust, chicago);
    }

    public static double toppingsize(double toppings){
        return numOfTopping=toppings;
    }

    /**
     * Returns the price of the pizza of Build your own type.
     * @return double price of the pizza.
     */
    @Override
    public double price() {
        size = getSize();
        numOfTopping=getToppings().size();
        double priceIn=0;
        double topping_price = numOfTopping * pricePerTopping;
        if (size == Size.small) {
            priceIn=small + topping_price;
        } else if (size == Size.medium) {
            priceIn= medium + topping_price;
        } else if (size == Size.large) {
            priceIn= large + topping_price;
        }
        return priceIn;
    }

    /**
     * Returns a string description of the pizza to display later.
     * Describes size and type of pizza with toppings.
     * @return String of pizza description
     */
    @Override
    public String toString() {
        if (chicago) {
            StringBuilder str = new StringBuilder("Type : Build Your own(");
            str.append(super.toString());
            return str.toString();

        } else {
            StringBuilder str = new StringBuilder("Type: BuildYourOwn (NY Style- Hand tossed) ");
            str.append(super.toString2());
            str.append(")");
            return str.toString();
        }
    }

    /**
     * Returns the price of the pizza.
     * @return double price of the pizza.
     */
    public double getPrice() {
        return price;
    }







}
