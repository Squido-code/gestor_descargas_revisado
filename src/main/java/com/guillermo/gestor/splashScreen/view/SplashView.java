package com.guillermo.gestor.splashScreen.view;

import com.guillermo.gestor.principal.view.PrincipalView;
import com.guillermo.gestor.splashScreen.controller.SplashController;
import com.guillermo.gestor.util.Resources;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SplashView {
    public static Logger logger = LogManager.getLogger(SplashView.class);


    public void startScreen(Stage stageTwo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Resources.getUI("splashScreen.fxml"));
            SplashController controller = new SplashController();
            loader.setController(controller);
            VBox pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                stage.hide();
                new PrincipalView(stageTwo).
                        principalUi();
            });


        } catch (IOException e) {
            e.printStackTrace();
            logger.trace(e.getMessage());
        }
    }
//    private void loadSplashScreen() {
//        try {
//            //Load splash screen view FXML
//            VBox vBox = FXMLLoader.load(Resources.getUI("splashScreen.fxml"));
//            //Add it to root container (Can be StackPane, AnchorPane etc)
//            root.getChildren().setAll(vBox);
//
//            //Load splash screen with fade in effect
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), vBox);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(1);
//            fadeIn.setCycleCount(1);
//
//            //Finish splash with fade out effect
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), vBox);
//            fadeOut.setFromValue(1);
//            fadeOut.setToValue(0);
//            fadeOut.setCycleCount(1);
//
//            fadeIn.play();
//
//            //After fade in, start fade out
//            fadeIn.setOnFinished((e) -> {
//                fadeOut.play();
//            });
//
//            //After fade out, load actual content
//            fadeOut.setOnFinished((e) -> {
//                try {
//                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/main.fxml")));
//                    root.getChildren().setAll(parentContent);
//                } catch (IOException ex) {
//                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
//        } catch (IOException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
