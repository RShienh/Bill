/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author rajbi
 */
public class Bill extends Application {

    protected BorderPane getPane() {

        BorderPane borderPane = new BorderPane();

        //HBox for button
        HBox box = new HBox(20);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(5, 5, 5, 5));
        Button gen = new Button("Generate");
        //Button exit = new Button("Exit");
        box.getChildren().addAll(gen);

        //HBox for product
        HBox pBox = new HBox(20);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setPadding(new Insets(5, 5, 5, 5));
        Label product = new Label("Product Code");
        TextField ptf = new TextField();
        pBox.getChildren().addAll(product, ptf);

        //HBox for qunatity
        Label q = new Label("Quantity");
        TextField qtf = new TextField("0");
        HBox qBox = new HBox(20);
        qBox.setAlignment(Pos.CENTER_LEFT);
        qBox.setPadding(new Insets(5, 5, 5, 5));
        qBox.getChildren().addAll(q, qtf);

        /**
         * //HBox for result HBox rBox = new HBox(20); Label r = new
         * Label("Result"); TextArea displayArea = new TextArea();
         * displayArea.setPrefSize(150, 150);
         * rBox.setAlignment(Pos.CENTER_LEFT); rBox.setPadding(new Insets(5, 5,
         * 5, 5)); rBox.getChildren().addAll(r, displayArea);*
         */
        //declaring HBoxes in grid pane
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(5, 5, 5, 5));
        gp.add(pBox, 0, 0);
        gp.add(qBox, 0, 1);
        // gp.add(rBox, 0, 2);

       

        gen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String productCode = "";
                String pQuantity = "";
                if (ptf.getText() != null) {
                    productCode = ptf.getText();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error !");
                    alert.setHeaderText(null);
                    alert.setContentText("Product code cannot be empty!");
                    alert.showAndWait();
                }
                if (Integer.parseInt(qtf.getText()) > 1) {
                    pQuantity = qtf.getText();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error !");
                    alert.setHeaderText(null);
                    alert.setContentText("Quantity should be greater than 1 !");
                    alert.showAndWait();
                }

                if (Product.compareInFile(productCode)) {
                     boolean e = false;
                    do {
                        Product.getInfo(productCode, pQuantity);

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Successfully added");
                        alert.setHeaderText("Do you want to add more items?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                          Product.getInfo(productCode, pQuantity);
                        } else {
                           e = true;
                        }
                    } while (e != true);

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error !");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Product Code");
                    alert.showAndWait();
                }
            }
        });

        //declaring gridpane to border pane
        borderPane.setCenter(gp);
        borderPane.setBottom(box);
        return borderPane;

    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(getPane(), 280, 150);

        primaryStage.setTitle("Product");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
