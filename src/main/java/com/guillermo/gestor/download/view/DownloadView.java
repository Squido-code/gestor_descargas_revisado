package com.guillermo.gestor.download.view;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.controller.DownloadController;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class DownloadView {
    private final FileToDownload fileToDownload;

    public DownloadView(FileToDownload fileToDownload) {
        this.fileToDownload = fileToDownload;
    }

    public AnchorPane downloadViewUi() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("uiDownload.fxml"));
        DownloadController controller = new DownloadController(fileToDownload);
        loader.setController(controller);
        AnchorPane anchorPane = loader.load();
        return anchorPane;
    }
}
