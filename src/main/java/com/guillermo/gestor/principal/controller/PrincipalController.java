package com.guillermo.gestor.principal.controller;

import com.guillermo.gestor.principal.model.PrincipalModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.guillermo.gestor.util.Common.globalPath;


public class PrincipalController {
    public static Logger logger = LogManager.getLogger(PrincipalController.class);

    public VBox vbDownloads;
    @FXML
    public Label lbDefaultPath;

    private final PrincipalModel principalModel;


    public PrincipalController() {
        principalModel = new PrincipalModel();
    }

    @FXML
    public void initialize() {
        lbDefaultPath.setText(globalPath);
    }

    @FXML
    public void newDownload(Event event) {
        logger.trace("Starting download options");
        principalModel.buildDownload(vbDownloads);
    }

    @FXML
    public void changePath() {
        principalModel.selectPath();
        lbDefaultPath.setText(globalPath);
    }
}
