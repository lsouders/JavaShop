package com.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Handle all actions related to coffee orders.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class CoffeeOrderController {

    private static final double ADDIN = 0.30;
    private static final double SHORT = 1.89;
    private static final double TALL = 2.29;
    private static final double GRANDE = 2.69;
    private static final double VENTI = 3.09;

    private Stage stage;
    private ArrayList<String> addins = new ArrayList<String>();
    @FXML
    private MenuButton CupSizeButton;
    @FXML
    private MenuButton CupQuantity;
    @FXML
    private CheckBox SweetCream;
    @FXML
    private CheckBox FrenchVanilla;
    @FXML
    private CheckBox IrishCream;
    @FXML
    private CheckBox Caramel;
    @FXML
    private CheckBox Mocha;
    @FXML
    private TextArea CoffeeSubtotal;
    @FXML
    private BasketController basket;
    private Alert alert;
    private double subtotal = 0;
    private int quantity = 1;

    //=======================================================


    /**
     * Add the current order to the basket.
     * @param event add order to basket.
     */
    @FXML
    protected void addToBasket(ActionEvent event) {
        if (CupQuantity.getText().equals("Quantity")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Must select a quantity of at least 1!");
            alert.show();
        } else if (CupSizeButton.getText().equals("Cup Size")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Must select a size!");
            alert.show();
        } else {
            Coffee add = new Coffee(CupSizeButton.getText(), addins, quantity);
            Coffee.addOrder(add);
            addins = new ArrayList<>();
            // reset all fields
            CoffeeSubtotal.setText("$0.00");
            CoffeeSubtotal.setText("Subtotal:");
            CupSizeButton.setText("Cup Size");
            CupQuantity.setText("Quantity");
            FrenchVanilla.setSelected(false);
            IrishCream.setSelected(false);
            Caramel.setSelected(false);
            SweetCream.setSelected(false);
            Mocha.setSelected(false);
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Selection successfully added to the basket!");
            alert.show();
        }
    }

    /**
     * Allow user to reset current order and start over.
     * @param event click reset order button.
     */
    @FXML
    protected void resetOrder(ActionEvent event) {
        subtotal = 0;
        quantity = 1;
        addins = new ArrayList<>();
        CoffeeSubtotal.setText("Subtotal:");
        CupSizeButton.setText("Cup Size");
        CupQuantity.setText("Quantity");
        FrenchVanilla.setSelected(false);
        IrishCream.setSelected(false);
        Caramel.setSelected(false);
        SweetCream.setSelected(false);
        Mocha.setSelected(false);
    }

    /**
     * Update the running subtotal for the current coffee order.
     */
    @FXML
    protected void updateSubtotal() {
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        CoffeeSubtotal.setText("Subtotal: " + d.format(subtotal));
    }

    /**
     * Update arraylist and change addin count in coffee class.
     * @param event click sweet cream check box.
     */
    @FXML
    protected void updateSweetCream(ActionEvent event){
        if (SweetCream.isSelected()) {
            addins.add("Sweet Cream");
            subtotal += (ADDIN * quantity);
        } else {
            subtotal -= (ADDIN * quantity);
            addins.remove("Sweet Cream");
        }
        updateSubtotal();
    }

    /**
     * Update arraylist and change addin count in coffee class.
     * @param event click sweet cream check box.
     */
    @FXML
    protected void updateFrenchVanilla(ActionEvent event){
        if (FrenchVanilla.isSelected()) {
            addins.add("French Vanilla");
            subtotal += (ADDIN * quantity);
        } else {
            subtotal -= (ADDIN * quantity);
            addins.remove("French Vanilla");
        }
        updateSubtotal();
    }

    /**
     * Update arraylist and change addin count in coffee class.
     * @param event click sweet cream check box.
     */
    @FXML
    protected void updateIrishCream(ActionEvent event){
        if (IrishCream.isSelected()) {
            subtotal += (ADDIN * quantity);
            addins.add("Irish Cream");
        } else {
            subtotal -= (ADDIN * quantity);
            addins.remove("Irish Cream");
        }
        updateSubtotal();
    }

    /**
     * Update arraylist and change addin count in coffee class.
     * @param event click sweet cream check box.
     */
    @FXML
    protected void updateCaramel(ActionEvent event){
        if (Caramel.isSelected()) {
            subtotal += (ADDIN * quantity);
            addins.add("Caramel");
        } else {
            subtotal -= (ADDIN * quantity);
            addins.remove("Caramel");
        }
        updateSubtotal();
    }

    /**
     * Update arraylist and change addin count in coffee class.
     * @param event click sweet cream check box.
     */
    @FXML
    protected void updateMocha(ActionEvent event){
        if (Mocha.isSelected()) {
            subtotal += (ADDIN * quantity);
            addins.add("Mocha");
        } else {
            subtotal -= (ADDIN * quantity);
            addins.remove("Mocha");
        }
        updateSubtotal();
    }

    /**
     * Set the cup quantity to 1.
     * @param event click 1 in cup quantity menu.
     */
    @FXML
    protected void setCupQuantity1(ActionEvent event) {
        subtotal /= quantity;
        quantity = 1;
        subtotal *= quantity;
        CupQuantity.setText("Quantity: 1");
        updateSubtotal();
    }

    /**
     * Set the cup quantity to 2.
     * @param event click 2 in cup quantity menu.
     */
    @FXML
    protected void setCupQuantity2(ActionEvent event) {
        subtotal /= quantity;
        quantity = 2;
        subtotal *= quantity;
        CupQuantity.setText("Quantity: 2");
        updateSubtotal();
    }

    /**
     * Set the cup quantity to 3.
     * @param event click 3 in cup quantity menu.
     */
    @FXML
    protected void setCupQuantity3(ActionEvent event) {
        subtotal /= quantity;
        quantity = 3;
        subtotal *= quantity;
        CupQuantity.setText("Quantity: 3");
        updateSubtotal();
    }

    /**
     * Set the cup quantity to 4.
     * @param event click 4 in cup quantity menu.
     */
    @FXML
    protected void setCupQuantity4(ActionEvent event) {
        subtotal /= quantity;
        quantity = 4;
        subtotal *= quantity;
        CupQuantity.setText("Quantity: 4");
        updateSubtotal();
    }

    /**
     * Set the cup quantity to 5.
     * @param event click 5 in cup quantity menu.
     */
    @FXML
    protected void setCupQuantity5(ActionEvent event) {
        subtotal /= quantity;
        quantity = 5;
        subtotal *= quantity;
        CupQuantity.setText("Quantity: 5");
        updateSubtotal();
    }

    /**
     * Set the Menubutton text field to venti.
     * @param event click venti menu item.
     */
    @FXML
    protected void setSizeVenti(ActionEvent event) {
        subtotal = quantity * ((addins.size() * ADDIN) + VENTI);
        CupSizeButton.setText("Venti");
        updateSubtotal();
    }

    /**
     * Set the Menubutton text field to grande.
     * @param event click grande menu item.
     */
    @FXML
    protected void setSizeGrande(ActionEvent event) {
        subtotal = quantity * ((addins.size() * ADDIN) + GRANDE);
        CupSizeButton.setText("Grande");
        updateSubtotal();
    }

    /**
     * Set the Menubutton text field to Tall.
     * @param event click Tall menu item.
     */
    @FXML
    protected void setSizeTall(ActionEvent event) {
        subtotal = quantity * ((addins.size() * ADDIN) + TALL);
        CupSizeButton.setText("Tall");
        updateSubtotal();
    }

    /**
     * Set the Menubutton text field to Small.
     * @param event click small menu item.
     */
    @FXML
    protected void setSizeShort(ActionEvent event) {
        subtotal = quantity * ((addins.size() * ADDIN) + SHORT);
        CupSizeButton.setText("Short");
        updateSubtotal();
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
