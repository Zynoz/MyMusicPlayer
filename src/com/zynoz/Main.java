package com.zynoz;

import com.zynoz.views.RootBorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    public static void alert(String cause, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR");
        alert.setTitle(cause);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage)  {
        RootBorderPane rootBorderPane = new RootBorderPane();
        //rootBorderPane.getStylesheets().add(String.valueOf(getClass().getResource("../resources/css/stylesheet.css")));

        Scene scene = new Scene(rootBorderPane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}