package com.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Store order view class.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class StoreOrderView extends Application {

    /**
     * Start the Order view from here.
     * @param stage the stage.
     * @throws IOException on error.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("store-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
    }
}
