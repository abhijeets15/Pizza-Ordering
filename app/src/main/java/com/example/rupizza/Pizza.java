package com.example.rupizza;
/**
 * Class for the properties of Pizza.
 * Pizza is an abstract class.
 * @author Abhijeet Singh, Khizar Saud
 */

import java.util.ArrayList;

public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    private String[] toppings2;

    private String crust2;
    private String size2;

    public abstract double price();

    protected boolean chicago;

    /**
     * Constructor for class of type pizza.
     *
     * @param crust   type crust to declare the crust of the pizza.
     * @param chicago type boolean to delcare the pizza of type chicago or new york.
     */
    public Pizza(Crust crust, boolean chicago) {
        toppings = new ArrayList<>();
        this.size = Size.medium;
        this.crust = crust;
        this.chicago = chicago;
    }

    /**
     * Returns size of pizza.
     *
     * @return of type size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size of what size to set pizza to.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns the list of toppings for the pizza.
     *
     * @return ArrayList for toppings of pizza.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }


    /**
     * Removes toppings from a pizza.
     *
     * @param obj object of toppings.
     * @return boolean, true if removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            toppings.remove((Topping) obj);
        } else if (obj instanceof ArrayList) {
            toppings.removeAll((ArrayList<Topping>) obj);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Constructor for type pizza.
     *
     * @param style    Crust type of the pizza.
     * @param size     what size the pizza is, large, small, medium.
     * @param toppings Array list of toppings of the pizza.
     */
    public Pizza(Crust style, Size size, ArrayList<Topping> toppings) {
        this.toppings = toppings;
        this.crust = style;
        this.size = size;
    }

    /**
     * Constructor for Pizza.
     *
     * @param style Crust type of the pizza.
     * @param size  what size the pizza is, large, small, medium.
     */
    public Pizza(Crust style, Size size) {
        this.toppings = toppings;
        this.crust = style;
        this.size = size;
    }

    /**
     * Constructor for pizza.
     *
     * @param style Crust type of the pizza.
     * @param size  what size the pizza is, large, small, medium.
     */
    public Pizza(String style, String size) {
        this.crust2 = style;
        this.size2 = size;

    }

    /**
     * Adds toppings to a pizza.
     *
     * @param obj object of toppings.
     * @return true if toppings were added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (toppings.contains(obj)) {
            return false;
        } else {
            if (obj instanceof Topping && toppings.size()!=7) {
                toppings.add((Topping) obj);
            } else if (obj instanceof ArrayList) {
                toppings.addAll((ArrayList<Topping>) obj);
            } else {
                return false;
            }
            return true;
        }
    }

    /**
     * Retrns string description of pizza.
     *
     * @return pizza description of type string.
     */
    public String toStringForMeatzaa(){ StringBuilder str = new StringBuilder("");

        try {
            if (chicago) {
                str.append("(Chicago Style - Stuffed), ");
            } else {
                str.append("(NY Style - Hand Tossed), ");
            }
            ArrayList<Topping> toppings = getToppings();
            for (Topping t : toppings) {
                str.append(t).append(", ");
            }

            str.append(getSize().toString().toLowerCase()).append(", $").append(price());

        } catch (Exception e) {
            //System.out.println("fndsjlfsadfds");

        }return str.toString();
    }


    public String toStringForDeluxe(){
        StringBuilder str = new StringBuilder("");

        try {
            if (chicago) {
                str.append("(Chicago Style - Deep Dish), ");
            } else {
                str.append("(NY Style - Brooklyn), ");
            }
            ArrayList<Topping> toppings = getToppings();
            for (Topping t : toppings) {
                str.append(t).append(", ");
            }

            str.append(getSize().toString().toLowerCase()).append(", $").append(price());

        } catch (Exception e) {
          //  System.out.println("fndsjlfsadfds");

        }return str.toString();
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");

        try {
            if (chicago) {
                str.append("(Chicago Style - Pan), ");
            } else {
                str.append("(NY Style - Thin), ");
            }
            ArrayList<Topping> toppings = getToppings();
            for (Topping t : toppings) {
                str.append(t).append(", ");
            }

            str.append(getSize().toString().toLowerCase()).append(", $").append(price());

        } catch (Exception e) {
       //     System.out.println("fndsjlfsadfds");

        }return str.toString();
    }




    public String toString2(){
        StringBuilder str = new StringBuilder("(");
        if(chicago){
            str.append("Chicago Style - Pan), ");
        }
        else{
        }
        ArrayList<Topping> toppings = getToppings();
        for(Topping t : toppings){
            str.append(t).append(", ");
        }

        System.out.println(getSize().toString());
        System.out.println(price());
        str.append(getSize().toString().toLowerCase()).append(", $").append(String.format("%.2f", price()));

        return str.toString();
    }
}
