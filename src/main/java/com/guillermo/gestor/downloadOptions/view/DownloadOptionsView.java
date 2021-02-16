package com.guillermo.gestor.downloadOptions.view;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.downloadOptions.controller.DownloadOptionsController;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DownloadOptionsView {

    public void downloadOptionsUi(FileToDownload fileToDownload) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("uiDownloadOptions.fxml"));
        DownloadOptionsController controller = new DownloadOptionsController(fileToDownload, "");
        loader.setController(controller);
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
