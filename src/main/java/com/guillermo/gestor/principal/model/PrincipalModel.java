package com.guillermo.gestor.principal.model;


import com.guillermo.gestor.downloadOptions.view.DownloadOptionsView;
import com.guillermo.gestor.util.Common;
import com.guillermo.gestor.util.Notifications;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.guillermo.gestor.util.Common.globalPath;


public class PrincipalModel {
    public static Logger logger = LogManager.getLogger(PrincipalModel.class);
    private final Notifications notification;
    private final Common common;


    public PrincipalModel() {
        this.notification = new Notifications();
        common = new Common();
    }

    public void buildDownload(VBox vbDownloads) {
        try {
            logger.trace("Building DownloadOptionsView");
            DownloadOptionsView downloadOptionsView = new DownloadOptionsView(vbDownloads);
            downloadOptionsView.downloadOptionsUi();
        } catch (IOException e) {
            logger.trace(e.getMessage());
            notification.errorAlert("Error in new download");
        }
    }

    public void selectPath() {

        globalPath = common.selectPath();
        logger.trace("new  global path: " + globalPath);
    }


}
