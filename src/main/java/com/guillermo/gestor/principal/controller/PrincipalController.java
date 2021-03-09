package com.guillermo.gestor.principal.controller;

import com.guillermo.gestor.principal.model.PrincipalModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PrincipalController {
    public static Logger logger = LogManager.getLogger(PrincipalController.class);
    public Button btNewDownload;

    public VBox vbDownloads;

    private final PrincipalModel principalModel;


    public PrincipalController() {
        principalModel = new PrincipalModel();
    }

    @FXML
    public void newDownload(Event event) {
        logger.trace("Starting download options");
        principalModel.buildDownload(vbDownloads);


    }
}
