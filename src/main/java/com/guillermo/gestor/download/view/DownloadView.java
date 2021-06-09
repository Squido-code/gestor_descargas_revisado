package com.guillermo.gestor.download.view;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.controller.DownloadController;
import com.guillermo.gestor.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class DownloadView {
    public static Logger logger = LogManager.getLogger(DownloadView.class);
    private final FileToDownload fileToDownload;

    public DownloadView(FileToDownload fileToDownload) {
        this.fileToDownload = fileToDownload;
    }

    public AnchorPane downloadViewUi() {
        AnchorPane anchorPane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Resources.getUI("uiDownload.fxml"));
            DownloadController controller = new DownloadController(fileToDownload);
            loader.setController(controller);
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.trace(e.getMessage());
        } finally {
            logger.trace("Download window initiated");
        }
        return anchorPane;
    }
}
