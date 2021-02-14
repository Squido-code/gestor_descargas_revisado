package com.guillermo.gestor.principal.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.principal.model.PrincipalModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class PrincipalController {
    public Button btNewDownload;

    public VBox vbDownloads;

    private PrincipalModel principalModel;


    public PrincipalController() {

    }

    @FXML
    public void newDownload(Event event) {
        FileToDownload fileToDownload = principalModel.
                buildDownload();

    }
}
