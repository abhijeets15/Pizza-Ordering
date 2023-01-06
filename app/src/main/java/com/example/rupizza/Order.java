package com.example.rupizza;

/**
 * This is the order class with the current order.
 * Pizzas and its details are stored in the order.
 * @author Abhijeet Singh, Khizar Saud
 */

import java.util.ArrayList;
import static com.example.rupizza.StoreOrder.makeID;
public class Order implements Customizable{
    private ArrayList <Pizza> pizzas;
    private int orderID;
    private double SALES_TAX = 0.06625;
    private boolean existing;
    private boolean completedOrder;

    /**
     * Constructor for Order
     */
    public Order(){
        completedOrder = false;
        orderID = StoreOrder.makeID();
        existing = true;
        pizzas = new ArrayList<>();
    }

    /**
     * Constructor for order
     * @param pizzaArrayList ArrayList for pizzas
     * @param orderID integer for id of order.
     */
    public Order(ArrayList<Pizza> pizzaArrayList, int orderID) {
        this.pizzas = pizzaArrayList;
        this.orderID = orderID;
    }

    /**
     * Returns the Order ID of current order.
     * @return integer of order ID.
     */
    public int getOrderID()
    {
        return orderID;
    }

    /**
     * Returns of order is existing
     * @return true if existing, false otherwise.
     */
    public boolean isExisting()
    {
        return existing;
    }

    /**
     * This sets the existing state to false so that all currently valid orders are passed through.
     */
    public void setNotExisting()
    {
        existing = false;
    }

    /**
     * This method returns the pizzas arraylist from the current order.
     * @return
     */
    public ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    /**
     * This clears out the current order and sets the order fresh.
     */
    public void clear()
    {
        pizzas.clear();
    }

    /**
     * This return the description of all the pizzas that are in the order
     * @return String array list of the description of the pizzas.
     */
    public ArrayList<String> orderDetails()
    {
        ArrayList<String> desc = new ArrayList<>();
        for(Pizza pizza: pizzas)
        {
            desc.add(pizza.toString());
        }
        return desc;
    }

    /**
     * This gets subtotal of the order
     * @return double subtotal of order.
     */
    public double getSubTotal(){
        double total = 0.0;
        if (pizzas.size() == 0){
            return total;
        }
        for (Pizza pizza : pizzas)
        {
            total += pizza.price();
        }
        return total;
    }

    /**
     * Gets Tax of the order.
     * @return returns double of the tax that is returned.
     */
    public double getTax(){
        return getSubTotal() * SALES_TAX;
    }

    /**
     * Returns grand total of the order.
     * @return double of the grand total of the order.
     */
    public double getTotal(){
        return getTax() + getSubTotal();
    }

    /**
     * Finishes off the order.
     */
    public void completeOrder()
    {
        completedOrder = true;
    }

    /**
     * Add method overrriding the customizable class.
     * Adds pizza from pizzas array list.
     * @param obj order that needs to be added.
     * @return returns true if added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            return pizzas.add((Pizza) obj);
        } else {
            return false;
        }
    }

    /**
     * Remove method overrriding the customizable class.
     * Removes pizza from pizzas array list.
     * @param obj order that needs to be removed.
     * @return returns true if removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            return pizzas.remove((Pizza) obj);
        } else {
            return false;
        }
    }
}