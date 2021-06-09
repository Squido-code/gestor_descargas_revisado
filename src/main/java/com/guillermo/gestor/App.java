package com.guillermo.gestor;

import com.guillermo.gestor.principal.view.PrincipalView;
import com.guillermo.gestor.splashScreen.view.SplashView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App extends Application {
    public static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.trace("opening application");
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            logger.trace("Start screen");
            new SplashView(stage).startScreen();
            Thread.sleep(2000);
            logger.trace("principalUi");
            new PrincipalView(stage).
                    principalUi();
        } catch (InterruptedException e) {
            logger.trace(e.getMessage());
            e.printStackTrace();
        }
    }
}
