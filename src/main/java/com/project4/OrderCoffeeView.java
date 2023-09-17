package com.project4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Coffee View class
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class OrderCoffeeView extends Application {

    /**
     * Start the Coffee view from here.
     * @param stage the stage.
     * @throws IOException on error.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("order-coffee-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
    }
}
