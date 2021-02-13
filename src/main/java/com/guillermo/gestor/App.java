package com.guillermo.gestor;

import com.guillermo.gestor.principal.view.PrincipalView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class App  extends Application {
    @Override
    public void start(Stage stage) throws Exception {

     new PrincipalView(stage).
             principalUi();
    }

    public static void main(String[] args){
        launch();
    }
}
