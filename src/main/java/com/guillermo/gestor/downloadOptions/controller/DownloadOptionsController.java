package com.guillermo.gestor.downloadOptions.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.principal.view.PrincipalView;
import com.guillermo.gestor.util.Notifications;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


public class DownloadOptionsController {

    private final FileToDownload fileToDownload;
    private final Notifications notifications;
    private final String path;
    public TextField tfURL, tfDelayTime;
    public Button btSelectPath, btAccept, btCancel;
    public Label lbPath;
    private String url;
    private String fileName;

    public DownloadOptionsController(FileToDownload fileToDownload, String path) {
        this.fileToDownload = fileToDownload;
        this.notifications = new Notifications();
        this.path = path;
    }

    @FXML
    public void accept() {

        try {
            if (!validations()) {
                return;
            }
            build();
            closeWindow();
        } catch (URISyntaxException e) {
            notifications.errorAlert("URL not supported");
        }
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) btCancel.
                getParent().
                getScene().
                getWindow();
        stage.close();
    }

    @FXML
    private void selectPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.
                showDialog(PrincipalView.stage);
        fileToDownload.setPath(file.getPath());
    }

    private Boolean validations() throws URISyntaxException {
        if (!isURLValid()) {
            return false;
        }
        return isDelayValid();
    }

    private Boolean isDelayValid() {
        String delayString = tfDelayTime.
                getText();
        Boolean isDelayValid = StringUtils.
                isNumeric(delayString);
        if (isDelayValid) {
            return true;
        } else {
            notifications.errorAlert("The delay time should be a number");
            return false;
        }
    }

    private Boolean isURLValid() throws URISyntaxException {
        this.url = tfURL.getText();
        URI uri = new URI(url);
        UrlValidator urlValidator = new UrlValidator();
        Boolean isValidURL = urlValidator.
                isValid(url);
        this.fileName = new File(uri.
                getPath()).
                getName();

        if (isValidURL && !fileName.isEmpty()) {
            return true;
        } else {
            notifications.
                    errorAlert("URL is incorrect");
            return false;
        }
    }

    private void build() {
        String delayString = tfDelayTime.getText();
        int delayInt = Integer.
                parseInt(delayString);
        fileToDownload.setDelay(delayInt);
        fileToDownload.setUrl(url);
        fileToDownload.setName(fileName);
        fileToDownload.setPath(path);
        fileToDownload.setReady(true);

    }
}
