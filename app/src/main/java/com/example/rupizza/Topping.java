package com.example.rupizza;

/**
 * Enum for toppings for pizza.
 * @author Abhijeet Singh, Khizar Saud
 */

public enum Topping {
    Sausage, pepperoni, BBQ_chicken, green_pepper,beef, ham, pineapple,provolone,cheadder, red_pepper, onion,mushrooms,spinach, feta_cheese;

    double price=1.49;

    /**
     * Sets price of each topping added.
     */
    private Topping(){
        this.price=1.49;
    }


}
