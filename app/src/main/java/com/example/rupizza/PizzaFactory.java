package com.example.rupizza;

/**
 * Interface for creating the pizza types.
 * @author Abhijeet Singh, Khizar Saud
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
