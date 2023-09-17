package com.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Handle all things related to donuts.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class Donut extends MenuItem{

    private static final double YEAST = 1.59;
    private static final double CAKE = 1.79;
    private static final double HOLE = 0.39;
    private static ObservableList<Donut> donuts = FXCollections.observableArrayList();

    private String type;
    private String flavor;
    private int count;


    //========================================================

    /**
     * Clear the list of donuts and other associated variables.
     */
    public static void clearList() {
        donuts = FXCollections.observableArrayList();
    }

    /**
     * Create donut object.
     * @param type yeast, cake, or hole.
     * @param flavor flavor of type.
     * @param count number in order.
     */
    public Donut (String type, String flavor, int count) {
        this.type = type;
        this.flavor = flavor;
        this.count = count;
    }

    /**
     * Get the current subtotal for the donuts.
     */
    public static double getSubtotal() {
        double res = 0;
        for (Donut d : donuts) {
            switch (d.type) {
                case "Yeast":
                    res += (YEAST * d.count);
                    break;
                case "Cake":
                    res += (CAKE * d.count);
                    break;
                case "Hole":
                    res += (HOLE * d.count);
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    /**
     * Get the list of donuts in the order.
     * @return list of donuts.
     */
    public static ObservableList<Donut> getDonutList() {
        return donuts;
    }

    /**
     * Update the lists of items and prices when an item is removed from running order.
     * @param d to be removed.
     */
    public static void removeHelper(Donut d) {
        if (d == null) return;
        donuts.remove(d);
    }

    /**
     * Maintain lists containing items and prices to calculate totals.
     * @param donut to be added.
     */
    public static void addHelper(Donut donut) {
        donuts.add(donut);
    }


    /**
     * Override itemPrice for calculating donut price.
     * @return price of donut.
     */
    @Override
    public double itemPrice() {
        switch (this.type) {
            case "Yeast":
                return this.count * YEAST;
            case "Cake":
                return this.count * CAKE;
            case "Hole":
                return this.count * HOLE;
            default:
                return 0;
        }
    }

    /**
     * Override to string method.
     * @return string representation of donut.
     */
    @Override
    public String toString() {
        String res = "";
        res = this.count + ": " + this.type + " " + this.flavor;
        return res;
    }
}
