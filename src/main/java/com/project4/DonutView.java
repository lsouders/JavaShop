package com.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Donut View class
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class DonutView extends Application {

    /**
     * Start the Donut view from here.
     * @param stage the stage.
     * @throws IOException on error.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("donut-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
    }
}
