package com.guillermo.gestor.principal.view;

import com.guillermo.gestor.principal.controller.PrincipalController;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalView {
    public static Stage stage;

    public PrincipalView(Stage stage) {
        PrincipalView.stage = stage;
    }

    /**
     * Create the main user interface
     *
     * @throws IOException
     */
    public void principalUi() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("uiPrincipal.fxml"));
        PrincipalController controller = new PrincipalController();
        loader.setController(controller);
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

}
