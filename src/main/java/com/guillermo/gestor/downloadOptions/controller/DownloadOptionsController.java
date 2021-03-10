package com.guillermo.gestor.downloadOptions.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.view.DownloadView;
import com.guillermo.gestor.downloadOptions.model.DownloadOptionsModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.guillermo.gestor.util.Common.globalPath;


public class DownloadOptionsController {
    public static Logger logger = LogManager.getLogger(DownloadOptionsModel.class);

    private final VBox vbDownloads;
    private DownloadOptionsModel downloadOptionsModel;
    public TextField tfURL, tfDelayTime;
    public Button btSelectPath, btAccept, btCancel;
    public Label lbPath;

    /**
     * Constructor
     *
     * @param vbDownloads which is the principal Vbox
     */
    public DownloadOptionsController(VBox vbDownloads) {
        this.vbDownloads = vbDownloads;
    }

    /**
     * Method that load the basic components data
     */
    @FXML
    public void initialize() {
        tfDelayTime.setText("0");
        this.downloadOptionsModel = new DownloadOptionsModel(tfDelayTime, tfURL);
        lbPath.setText(globalPath);

    }

    /**
     * Method that launch the download panel.
     * First check the data validation and create a new FileToDownload
     */
    @FXML
    public void accept() {
        logger.trace("Download accepted");
        if (!downloadOptionsModel.
                validations()) {
            return;
        }
        FileToDownload fileToDownload = downloadOptionsModel.build();
        DownloadView downloadView = new DownloadView(fileToDownload);
        vbDownloads.getChildren().
                add(downloadView.downloadViewUi());
        downloadOptionsModel.closeWindow(btAccept);
    }

    /**
     * Cancel the current operation closing the window
     */
    @FXML
    public void cancel() {
        downloadOptionsModel.closeWindow(btCancel);
    }

    /**
     * Let the user choose where the file will be downloaded through a dialog.
     * After the path is chosen the information get send to the model.
     */
    @FXML
    private void selectPath() {
        downloadOptionsModel.selectPath();
    }




}
