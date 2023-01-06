package com.example.rupizza;

/**
 * This is a class to delcare a pizza of Chicago Pizza type.
 * Has different kinds of pizzas for its chicago type.
 * @author Abhijeet Singh, Khizar Saud
 */

public class ChicagoPizza implements PizzaFactory{

    /**
     * Creates a pizza of type Deluxe.
     * @return Deluxe pizza.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe(Crust.deepdish, true);
        return pizza;
    }

    /**
     * Creates a pizza of type Meatzza.
     * @return Meatzza pizza.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(Crust.stuffed, true);
        return pizza;
    }

    /**
     * Creates a pizza of type BBQ Chicken.
     * @return BBQ Chicken pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(Crust.pan, true);
        return pizza;
    }

    /**
     * Creates a pizza of type Build your own.
     * @return Build your own pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(Crust.pan, true);
        return pizza;
    }
}