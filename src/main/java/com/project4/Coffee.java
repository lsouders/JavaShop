package com.project4;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;

import java.util.ArrayList;


/**
 * Handle everything related to coffee objects.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class Coffee extends MenuItem {


    private static final double ADDIN_COST = 0.30;
    private static final double SHORT_COST = 1.89;
    private static final double TALL_COST = 2.29;
    private static final double GRANDE_COST = 2.69;
    private static final double VENTI_COST = 3.09;
    private int quantity;
    private int addinsCount;
    private double size;
    private String sizeName;
    private ArrayList<String> addins;
    private static ObservableList<Coffee> coffees = FXCollections.observableArrayList();

    //======================================================
    // Methods below
    //======================================================

    /**
     * Clear the list of donuts and other associated variables.
     */
    public static void clearList() {
        coffees = FXCollections.observableArrayList();
    }

    /**
     * Add an item to the list view.
     * @param coffee item to be added.
     */
    public static void addOrder(Coffee coffee) {
        coffees.add(coffee);
    }

    /**
     * Remove item from items and coffeePrices.
     * @param coffee to be removed.
     */
    public static void removeHelper(Coffee coffee) {
        MenuItem remove = null;
        for (MenuItem c : coffees) {
            if (c.toString().equals(coffee.toString())) remove = c;
        }
        if (remove != null) coffees.remove(remove);
    }

    /**
     * Get the list of coffee prices.
     * @return coffee prices
     */
    public static double getSubtotal() {
        double res = 0, price = 0;

        for (Coffee c : coffees) {
            switch (c.sizeName) {
                case "Venti":
                    price = VENTI_COST;
                    break;
                case "Grande":
                    price = GRANDE_COST;
                    break;
                case "Tall":
                    price = TALL_COST;
                    break;
                case "Short":
                    price = SHORT_COST;
                    break;
                default:
                    break;
            }
            res += (c.quantity * (price + c.addins.size()));
        }

        return res;
    }

    /**
     * return coffee instance.
     */
    public static ObservableList<Coffee> getCoffeeList() {
        return coffees;
    }

    /**
     * Reset the values of the numbers tracking relative order information.
     */
    public Coffee(String size, ArrayList<String> addins, int quantity) {
        this.sizeName = size;
        this.addins = addins;
        this.quantity = quantity;
    }

    /**
     * Set the list of addins passed from coffee view controller.
     * @param a list to be given to coffee object.
     */
    public void setAddins(ArrayList<String> a) {
        this.addins = a;
    }

    /**
     * Add an add-in to the current order.
     */
    public void addAddin() {
        this.addinsCount++;
    }

    /**
     * Remove an add-in from the coffee.
     */
    public void removeAddin() {
        this.addinsCount--;
    }

    /**
     * Set the new quantity of the coffee order.
     * @param quantity of cups per order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Set the value of the size of the coffee.
     * @param sizeName String name of size of coffee.
     */
    public void setSize(String sizeName) {
        switch (sizeName) {
            case "Venti":
                this.size = VENTI_COST;
                this.sizeName = "Venti";
                break;
            case "Grande":
                this.size = GRANDE_COST;
                this.sizeName = "Grande";
                break;
            case "Tall":
                this.size = TALL_COST;
                this.sizeName = "Tall";
                break;
            case "Short":
                this.size = SHORT_COST;
                this.sizeName = "Short";
                break;
            default:
                break;
        }
    }

    /**
     * Check to see if order is valid to add to basket.
     * @return true if it is, false otherwise.
     */
    public boolean isValid() {
        if (this.size == 0.0) return false;
        else if (this.quantity == 0) return false;
        return true;
    }

    /**
     * Return the current coffee order price.
     * @return order price of coffee.
     */
    @Override
    public double itemPrice() {
        return quantity * ((addinsCount * ADDIN_COST) + size);
    }

    /**
     * Override the toString method to print a coffee order.
     * @return string of coffee order.
     */
    @Override
    public String toString() {
        String res = "";
        res += quantity + " " + sizeName;
        if (addins.size() >= 1) {
            res += " with:";
            for (String addin : addins) {
                res += " " + addin;
            }
        }
        return res;
    }
}
