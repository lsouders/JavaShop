package com.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Handle all actions related to store orders.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class StoreOrderController {

    private Stage stage;

    @FXML
    private ComboBox<Integer> OrderNumberCBox;
    @FXML
    private ListView<String> OrderList;
    @FXML
    private TextField TotalCost;

    ObservableList<String> orderItemsAsStrings = FXCollections.observableArrayList();
    ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();

    //======================================================

    /**
     * This method will be automatically performed when the controller object is
     * first created. Therefore, use this method to set up the default values.
     */
    public void initialize() {
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        // set the numbers in the cbox
        if (Order.getOrders() != null) {
            for (Order o : Order.getOrders()) {
                orderNumbers.add(o.getOrderNumber());
            }
        }
        OrderNumberCBox.setItems(orderNumbers);
    }

    /**
     * Export the orders to the file 'StoreOrders.txt'
     * @param event
     */
    @FXML
    protected void exportOrders(ActionEvent event) {
        File file = new File("StoreOrders.txt");
        PrintWriter pw;
        try { pw = new PrintWriter(file); } // file will automatically be truncated if previously written to
        catch (FileNotFoundException f) {
            Alert a = new Alert(Alert.AlertType.ERROR, "File not found!");
            a.show();
            return;
        }
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        // get orders list, then begin to write orders to the file.
        ArrayList<Order> orders = Order.getOrders();
        for (Order o : orders) {
            pw.println("Order number: " + o.getOrderNumber() + ",\tTotal: " + d.format(o.getTotalCost()));
            for (MenuItem m : o.getItems()) {
                pw.println(m.toString());
            }
        }
        pw.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Orders successfully exported to the file\n" +
                "StoreOrders.txt in the project4 directory.");
        alert.show();
    }

    /**
     * Display the items in the chosen order number.
     * @param event click one of the order number buttons.
     */
    @FXML
    protected void displayOrderNumber(ActionEvent event) {
        // get the item selected
        int selected;
        try { selected = OrderNumberCBox.getSelectionModel().getSelectedItem(); }
        catch (Exception e) { return; }
        orderItemsAsStrings = FXCollections.observableArrayList(); // reset the order items list
        Order chosen = null;
        // find the chosen order number
        for (Order o : Order.getOrders()) {
            if (o.getOrderNumber() == selected) chosen = o;
        }
        // error checking, although this should never happen.
        if (chosen == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Order number does not exist!");
            a.show();
            return;
        }
        // get strings of the orders.
        for (MenuItem m : chosen.getItems()) {
            orderItemsAsStrings.add(m.toString());
        }
        // set items to the listview
        OrderList.setItems(orderItemsAsStrings);
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        TotalCost.setText(d.format(chosen.getTotalCost()));
    }

    /**
     * Cancel the order that is currently selected in the combo box.
     * @param event click cancel order button.
     */
    @FXML
    protected void cancelOrder(ActionEvent event) {
        int selected;
        try { selected = OrderNumberCBox.getSelectionModel().getSelectedItem(); }
        catch (NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Must select an order number from the drop down!");
            a.show();
            return;
        }
        Order chosen = null;
        for (Order o : Order.getOrders()) {
            if (o.getOrderNumber() == selected) chosen = o;
        }
        if (chosen == null) return; // unknown error occurred. do nothing
        // now we must delete the order, update the cbox, and clear the list view.
        ArrayList<Order> orders = Order.getOrders();
        orders.remove(chosen); // order is now gone.
        orderNumbers = FXCollections.observableArrayList();
        for (Order o : Order.getOrders()) orderNumbers.add(o.getOrderNumber());
        OrderNumberCBox.setItems(orderNumbers);
        orderItemsAsStrings = FXCollections.observableArrayList();
        OrderList.setItems(orderItemsAsStrings);
        TotalCost.setText("$0.00");
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
