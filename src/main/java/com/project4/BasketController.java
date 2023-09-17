package com.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Handle all actions related to using the basket.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class BasketController {

    private Stage stage;

    private ObservableList<Coffee> coffeeList;
    private ObservableList<Donut> donutList;
    private ObservableList<MenuItem> basketItems = FXCollections.observableArrayList();
    @FXML
    private ListView<MenuItem> BasketList;
    @FXML
    private TextField BasketTotal;
    @FXML
    private TextField BasketSubtotal;
    @FXML
    private TextField BasketSalesTax;


    private static final double SALES_TAX = 0.06625;
    private double subtotal, tax, total;

    /**
     * This method will be automatically performed when the controller object is
     * first created. Therefore, use this method to set up the default values.
     */
    public void initialize() {
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        // get list coffee and donut lists
        coffeeList = Coffee.getCoffeeList();
        donutList = Donut.getDonutList();
        // add these lists to the list of menu items
        basketItems.addAll(coffeeList);
        basketItems.addAll(donutList);
        // get a subtotal for the donuts and coffee
        subtotal = Coffee.getSubtotal();
        subtotal += Donut.getSubtotal();
        // set the items to be displayed in the list
        BasketList.setItems(basketItems);
        // calculate the various costs
        BasketSubtotal.setText(d.format(subtotal));
        tax = subtotal * SALES_TAX;
        BasketSalesTax.setText(d.format(tax));
        total = subtotal + tax;
        BasketTotal.setText(d.format(total));
    }

    /**
     * Place an order.
     * @param event click place order button.
     */
    @FXML
    protected void placeOrder(ActionEvent event) {
        if (basketItems.size() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("There are currently no items in the basket!");
            a.show();
            return;
        }
        String t = BasketTotal.getText();
        t = t.substring(1);
        double total = Double.parseDouble(t);
        Order.increaseOrderNumber();
        int numOfOrders = Order.getTotalOrders(); // will be used to assign an order number to the order
        Order add = new Order(basketItems, numOfOrders, total);
        Order.addOrder(add);
        // now we will clear all the lists.
        basketItems = FXCollections.observableArrayList();
        Coffee.clearList();
        Donut.clearList();
        BasketList.setItems(basketItems);
        BasketSubtotal.setText("$0.00");
        BasketSalesTax.setText("$0.00");
        BasketTotal.setText("$0.00");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Items successfully added to an order!");
        a.show();
    }

    /**
     * Update price fields when an item is removed from the basket.
     */
    private void updatePrices() {
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        // get the lists
        coffeeList = Coffee.getCoffeeList();
        donutList = Donut.getDonutList();
        // calculate the subtotal
        subtotal = Coffee.getSubtotal();
        subtotal += Donut.getSubtotal();
        // set the various costs
        BasketSubtotal.setText(d.format(subtotal));
        tax = subtotal * SALES_TAX;
        BasketSalesTax.setText(d.format(tax));
        total = subtotal + tax;
        BasketTotal.setText(d.format(total));
    }

    /**
     * Remove the selected item from the list.
     * @param event click remove selected item button.
     */
    @FXML
    protected void removeItem(ActionEvent event) {
        MenuItem selected = BasketList.getSelectionModel().getSelectedItem();
        if (selected instanceof Donut d) {
            Donut.removeHelper(d);
        } else if (selected instanceof Coffee c) {
            Coffee.removeHelper(c);
        } else {
            return; // something went terribly wrong.
        }
        coffeeList = Coffee.getCoffeeList();
        donutList = Donut.getDonutList();
        basketItems = FXCollections.observableArrayList();
        basketItems.addAll(coffeeList);
        basketItems.addAll(donutList);
        BasketList.setItems(basketItems);
        updatePrices();
    }

    /**
     * Return to the home window.
     * @param event click return home button.
     * @throws IOException if fxml file cannot be loaded.
     */
    @FXML
    protected void returnHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
