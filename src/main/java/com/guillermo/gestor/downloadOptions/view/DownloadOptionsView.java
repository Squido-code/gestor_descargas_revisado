package com.guillermo.gestor.downloadOptions.view;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.downloadOptions.controller.DownloadOptionsController;
import com.guillermo.gestor.principal.view.PrincipalView;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DownloadOptionsView {

    public void downloadOptionsUi(FileToDownload fileToDownload) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("uiDownloadOptions.fxml"));
        DownloadOptionsController controller = new DownloadOptionsController(fileToDownload);
        loader.setController(controller);
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        PrincipalView.stage.setScene(scene);
        PrincipalView.stage.showAndWait();
    }
}
