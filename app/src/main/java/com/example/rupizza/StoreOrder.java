package com.example.rupizza;

/**
 * This is the storeOrder class with the current order.
 * Pizzas and its details are stored in the order.
 * Store Order contains details of each order.
 * @author Abhijeet Singh, Khizar Saud
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreOrder implements Customizable {
    private static Order curOrder;
    private static ArrayList<Order> orders;

    public StoreOrder(){
        this.orders = new ArrayList<>();
    }

    public StoreOrder(ArrayList<Order> orders){
        this.orders = orders;
    }
    /**
     * Makes the ID of the order by adding another to the size of the arraylist so that the order numbers can stay unique.
     */
    public static int makeID(){
        if(orders==null){
            return 0;
        }
        int increase = 1;
        return orders.size()+increase;
    }

    /**
     * Gets the order list.
     * @return orders list of type ArrayList order.
     */
    public static ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     * Finishes Order and adds the current order to the orders list officially.
     * Also creates a new order to keep the order cycle running.
     */
    public static void finish(){
        orders.add(curOrder);
        curOrder.completeOrder();
        curOrder = new Order();
    }

    /**
     * Gets the current order that is being placed at the momment.
     * @return Order of type order.
     */
    public static Order getCurOrder(){
        if (orders == null) orders = new ArrayList<>();
        if (curOrder == null) curOrder = new Order();
        return curOrder;
    }

    /**
     * Gets the order ids of each order and returns it in an array list.
     * @return Array list of order ids.
     */
    public static ArrayList<Integer> getOrderIDs(){
        ArrayList<Integer> ids = new ArrayList<>();
        int empty = 0;
        if(orders == null){
            orders = new ArrayList<>();
        }
        if (orders.size() == empty)
        {
            return ids;
        }

        for(Order order: orders){
            if(order.isExisting()){
                ids.add(order.getOrderID());
            }
        }
        return ids;
    }

    /**
     * Gets a specific order based on the order id.
     * @param id Order id of order that is to be received.
     * @return returns the Order based on order id.
     */
    public static Order getOrder(int id){
        int empty = 1;
        if(id < empty){
            return null;
        }
        for(Order order: orders){
            if(order.getOrderID() == id){
                return order;
            }
        }
        return null;
    }

    /**
     * Cancels an order that has already been placed.
     * @param id id of order that needs to be canceled.
     * @return true if cancelled.
     */
    public static boolean cancel(int id){
        Order order = getOrder(id);
        return orders.remove(order);

    }

    /**
     * Sets the ID of a new order.
     * @return integer of ID number
     */
    public int setID(){
        int increase = 1;
        return orders.size() + increase;
    }


    /**
     * Add method overrriding the customizable class.
     * Adds order from orders array list.
     * @param obj order that needs to be added.
     * @return returns true if added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            return this.orders.add((Order) obj);
        } else {
            return false;
        }
    }

    /**
     * Remove method overrriding the customizable class.
     * Removes order from orders array list.
     * @param obj order that needs to be removed.
     * @return returns true if removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        Order order = (Order) obj;
        if(order.isExisting()){
            order.setNotExisting();
            return true;
        }

        return false;
    }
}