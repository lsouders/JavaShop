package com.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the main view. Handles switching to other views via image buttons.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private OrderCoffeeView view;
    private Stage stage;


    /**
     * Open the coffee order view window.
     * @param event Click coffee order button.
     * @throws IOException if fxml cannot be loaded.
     */
    @FXML
    protected void openCoffeeView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("order-coffee-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Open the donuts view window.
     * @param event click order donuts button.
     * @throws IOException if fxml file cannot be loaded.
     */
    @FXML
    protected void openDonutsView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("donut-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Open Basket view.
     * @param event click open basket button.
     * @throws IOException if fxml file cannot be loaded.
     */
    @FXML
    protected void openBasketView(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("basket-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Open store order window.
     * @param event click open store order button.
     * @throws IOException if fxml file cannot be loaded.
     */
    @FXML
    protected void openStoreOrder(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("store-orders-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}