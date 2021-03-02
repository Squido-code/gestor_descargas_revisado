package com.guillermo.gestor.downloadOptions.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.view.DownloadView;
import com.guillermo.gestor.downloadOptions.model.DownloadOptionsModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class DownloadOptionsController {


    private final VBox vbDownloads;
    private String path;
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
        this.downloadOptionsModel = new DownloadOptionsModel(tfDelayTime, tfURL, path);
    }

    /**
     * Method that launch the download panel.
     * First check the data validation and create a new FileToDownload
     */
    @FXML
    public void accept() {
        try {
            if (!downloadOptionsModel.
                    validations()) {
                return;
            }
            FileToDownload fileToDownload = downloadOptionsModel.build();
            DownloadView downloadView = new DownloadView(fileToDownload);
            vbDownloads.getChildren().
                    add(downloadView.downloadViewUi());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
