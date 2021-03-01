package com.guillermo.gestor.principal.controller;

import com.guillermo.gestor.downloadOptions.view.DownloadOptionsView;
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
            DownloadOptionsView downloadOptionsView = new DownloadOptionsView(vbDownloads);
            downloadOptionsView.downloadOptionsUi();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
