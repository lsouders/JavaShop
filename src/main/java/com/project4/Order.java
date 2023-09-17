package com.project4;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Handle all things related to orders.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class Order {

    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total;
    private static int totalOrders = 0;
    private static ArrayList<Order> orders = new ArrayList<>();

    /**
     * Create a new order object.
     * @param menuItems list of items in the order.
     * @param orderNumber the order number.
     */
    public Order(ObservableList<MenuItem> menuItems, int orderNumber, double total) {
        this.menuItems = menuItems;
        this.orderNumber = orderNumber;
        this.total = total;
    }

    /**
     * Add one to the total order numbers.
     */
    public static void increaseOrderNumber() {
        totalOrders++;
    }

    /**
     * Get the total cost of this order.
     * @return total cost.
     */
    public double getTotalCost() {
        return this.total;
    }

    /**
     * Get an order number
     * @return order number of this object.
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Get the items in the order.
     * @return the items list.
     */
    public ObservableList<MenuItem> getItems() {
        return this.menuItems;
    }
    /**
     * Add an order to the list of orders.
     * @param add order to list.
     */
    public static void addOrder(Order add) {
        orders.add(add);
    }

    /**
     * Get the list of orders.
     * @return the list of orders.
     */
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Get the total orders that have been placed. This will be used to set an order number
     * to the new order object that will be created.
     * @return how many total orders have been created.
     */
    public static int getTotalOrders() {
        return totalOrders;
    }
}
