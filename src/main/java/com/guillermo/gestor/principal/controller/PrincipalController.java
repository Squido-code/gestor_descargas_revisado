package com.guillermo.gestor.principal.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.view.DownloadView;
import com.guillermo.gestor.principal.model.PrincipalModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class PrincipalController {
    public Button btNewDownload;

    public VBox vbDownloads;

    private final PrincipalModel principalModel;


    public PrincipalController() {
        principalModel = new PrincipalModel();
    }

    @FXML
    public void newDownload(Event event) {

        try {
            FileToDownload fileToDownload = principalModel.
                    buildDownload();
            DownloadView downloadView = new DownloadView(fileToDownload);
            vbDownloads.getChildren().
                    add(downloadView.downloadViewUi());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
