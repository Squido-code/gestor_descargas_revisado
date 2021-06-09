package com.guillermo.gestor.splashScreen.view;

import com.guillermo.gestor.splashScreen.controller.SplashController;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashView {
    private final Stage stage;

    public SplashView(Stage stage) {
        this.stage = stage;
    }

    public void startScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Resources.getUI("splashScreen.fxml"));
            SplashController controller = new SplashController();
            loader.setController(controller);
            VBox vBox = null;
            vBox = loader.load();
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
