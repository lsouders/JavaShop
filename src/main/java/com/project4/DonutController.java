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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Handle the donut view actions.
 *
 * @author Lucas Souders, Gursharan Chahal
 */
public class DonutController {

    private Stage stage;
    private ObservableList<String> cboxOptions = FXCollections.observableArrayList(
            "Yeast Donuts", "Cake Donuts", "Donut Holes"
    );
    private ObservableList<String> yeast = FXCollections.observableArrayList(
            "Sprinkle", "Chocolate_Iced", "Vanilla_Iced",
            "Strawberry_Iced", "Blueberry", "Neapolitan_Iced"
    );
    private ObservableList<String> cake = FXCollections.observableArrayList(
            "Birthday_Cake", "Marbled_Cake", "Red_Velvet_Cake"
    );
    private ObservableList<String> holes = FXCollections.observableArrayList(
            "Cinnamon", "Chocolate", "Glazed"
    );
    private ObservableList<String> yeast_ref = FXCollections.observableArrayList(
            "Sprinkle", "Chocolate_Iced", "Vanilla_Iced",
            "Strawberry_Iced", "Blueberry", "Neapolitan_Iced"
    );
    private ObservableList<String> cake_ref = FXCollections.observableArrayList(
            "Birthday_Cake", "Marbled_Cake", "Red_Velvet_Cake"
    );
    private ObservableList<String> holes_ref = FXCollections.observableArrayList(
            "Cinnamon", "Chocolate", "Glazed"
    );
    private ObservableList<Integer> countOptions = FXCollections.observableArrayList(
            1, 2, 3, 4, 5
    );

    private Image yeastImg = new Image("file:src/main/resources/com/project4/yeastDonut.jpg");
    private Image cakeImg = new Image("file:src/main/resources/com/project4/cakeDonut.jpg");
    private Image holeImg = new Image("file:src/main/resources/com/project4/donutHole.jpg");

    @FXML
    private ComboBox<String> DonutTypeCBox;
    @FXML
    private ImageView DonutChoiceImage;
    @FXML
    private ListView<String> DonutOptionsList;
    @FXML
    private ListView<String> AddedDonuts;
    private ObservableList<String> CurrentAddedDonuts;
    private ObservableList<Donut> added = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> DonutCount;
    @FXML
    private TextField DonutSubTotal;

    private Donut donut;

    //=====================================================
    /**
     * This method will be automatically performed when the controller object is
     * first created. Therefore, use this method to set up the default values.
     */
    public void initialize() {
        DonutTypeCBox.setItems(cboxOptions);
        Image defaultImg = new Image("file:src/main/resources/com/project4/yeastDonut.jpg");
        DonutChoiceImage.setImage(defaultImg);
        DonutCount.setItems(countOptions);
    }

    /**
     * Get the current subtotal for the donut list.
     */
    private double getSubtotal() {
        double res = 0;
        for (Donut d : added) {
            res += d.itemPrice();
        }
        return res;
    }

    /**
     * Add the current order to the basket.
     * @param event add order to basket.
     */
    @FXML
    protected void addToBasket(ActionEvent event) {
        // reset all fields.
        added = FXCollections.observableArrayList();
        DonutTypeCBox.setItems(cboxOptions);
        DonutChoiceImage.setImage(yeastImg);
        DonutCount.setItems(countOptions);
        cake.setAll(cake_ref);
        yeast.setAll(yeast_ref);
        holes.setAll(holes_ref);
        AddedDonuts.setItems(null);
        DonutSubTotal.setText("$0.00");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Order successfully added to the basket!");
        alert.show();
    }

    /**
     * Remove the selected donuts from the list view.
     * @param event click remove donuts '<<<' button.
     */
    @FXML
    protected void removeDonuts(ActionEvent event) {
        if (AddedDonuts.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Must choose one of the added donuts!");
            a.show();
            return;
        }
        String selected = AddedDonuts.getSelectionModel().getSelectedItem();
        String split = selected.split(" +")[2];
        if (yeast_ref.contains(split)) {
            yeast.add(split);
        } else if (cake_ref.contains(split)) {
            cake.add(split);
        } else {
            holes.add(split);
        }
        Donut rem = null;
        for (Donut d : added) {
            if (d.toString().equals(selected)) rem = d;
        }
        if (rem == null) return;
        added.remove(rem);
        AddedDonuts.getItems().remove(selected);
        Donut.removeHelper(rem);
        updateSubtotal();
        updateSubtotal();
    }

    /**
     * Update the subtotal field in the donut view.
     */
    private void updateSubtotal() {
        DecimalFormat d = new DecimalFormat("'$'##,##0.00");
        double subtotal = getSubtotal();
        DonutSubTotal.setText(d.format(subtotal));
    }

    /**
     * Add donuts to the list to be added to the basket.
     * @param event click '>>>' button.
     */
    @FXML
    protected void addDonuts(ActionEvent event) {
        int quantity;
        try {
            quantity = DonutCount.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Must selected a quantity!");
            a.show();
            return;
        }
        Donut add;
        String selected = DonutOptionsList.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        String str = DonutTypeCBox.getSelectionModel().getSelectedItem();
        CurrentAddedDonuts = AddedDonuts.getItems();
        if (CurrentAddedDonuts == null) CurrentAddedDonuts = FXCollections.observableArrayList();
        switch (str) {
            case "Yeast Donuts":
                add = new Donut("Yeast", selected, quantity);
                Donut.addHelper(add);
                added.add(add); // helper list to calculate running subtotal
                CurrentAddedDonuts.add(add.toString());
                break;
            case "Cake Donuts":
                add = new Donut("Cake", selected, quantity);
                Donut.addHelper(add);
                added.add(add); // helper list to calculate running subtotal
                CurrentAddedDonuts.add(add.toString());
                break;
            case "Donut Holes":
                add = new Donut("Hole", selected, quantity);
                Donut.addHelper(add);
                added.add(add); // helper list to calculate running subtotal
                CurrentAddedDonuts.add(add.toString());
                break;
            default:
                break;
        }
        DonutOptionsList.getItems().remove(selected);
        AddedDonuts.setItems(CurrentAddedDonuts);
        updateSubtotal();
    }

    /**
     * Set the image based on the item chosen from the cbox.
     * @param event click item in cbox.
     */
    @FXML
    protected void setImage(ActionEvent event) {
        String selected = DonutTypeCBox.getSelectionModel().getSelectedItem();
        switch (selected) {
            case "Yeast Donuts":
                DonutChoiceImage.setImage(yeastImg);
                DonutOptionsList.setItems(yeast);
                break;
            case "Cake Donuts":
                DonutChoiceImage.setImage(cakeImg);
                DonutOptionsList.setItems(cake);
                break;
            case "Donut Holes":
                DonutChoiceImage.setImage(holeImg);
                DonutOptionsList.setItems(holes);
                break;
            default:
                break;
        }
    }

    /**
     * Return home from the donut view.
     * @param event click return home button.
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
