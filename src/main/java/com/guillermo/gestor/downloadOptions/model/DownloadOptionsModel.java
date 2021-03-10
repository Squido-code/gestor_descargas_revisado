package com.guillermo.gestor.downloadOptions.model;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.util.Common;
import com.guillermo.gestor.util.Notifications;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import static com.guillermo.gestor.util.Common.globalPath;


public class DownloadOptionsModel {
    public static Logger logger = LogManager.getLogger(DownloadOptionsModel.class);
    private final Notifications notifications;
    private final TextField tfDelayTime;
    private final TextField tfURL;
    private String fileName, path, url;
    private final Common common;

    public DownloadOptionsModel(TextField tfDelayTime, TextField tfURL) {
        this.tfDelayTime = tfDelayTime;
        this.tfURL = tfURL;
        this.path = globalPath;
        notifications = new Notifications();
        common = new Common();
    }

    /**
     * Method that close the window where a button is actioned
     *
     * @param button object to obtain the current stage.
     */
    public void closeWindow(Button button) {
        logger.trace("close windows");
        Stage stage = (Stage) button.
                getParent().
                getScene().
                getWindow();
        stage.close();
    }

    /**
     * Check if all the input data is correct
     *
     * @return Boolean
     */
    public Boolean validations() {
        if (!isURLValid()) {
            return false;
        }
        return isDelayValid();
    }

    /**
     * Check if the delay field contains a number and if this number is positive or Zero.
     *
     * @return Boolean
     */
    private Boolean isDelayValid() {
        String errorTxt = "The delay time should be a number";
        String delayString = tfDelayTime.
                getText();

        Boolean isDelayValid = StringUtils.
                isNumeric(delayString);

        if (isDelayValid) {
            int delayNumber = Integer.
                    parseInt(delayString);
            if (delayNumber >= 0) {
                return true;
            }
        }
        notifications.errorAlert(errorTxt);
        return false;

    }

    /**
     * Check if the URL is valid, this means contains a valid HTTP path and file name
     *
     * @return Boolean
     */
    private Boolean isURLValid() {
        url = tfURL.getText();
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            notifications.errorAlert("URL not supported");
        }
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

    /**
     * The method builds a new FileToDownload.
     * The object is based on the parameters shows at the download options screen.
     *
     * @return Object
     */
    public FileToDownload build() {
        FileToDownload fileToDownload = new FileToDownload();
        int delay = Integer.
                parseInt(tfDelayTime.getText());
        fileToDownload.setDelay(delay);
        fileToDownload.setUrl(url);
        fileToDownload.setName(fileName);
        fileToDownload.setPath(path);
        if (delay > 0) fileToDownload.setDelayed(true);
        logger.trace("download built");
        return fileToDownload;
    }

    /**
     * The method change the default path through a dialog.
     * The local variable path gets updated.
     */
    public void selectPath() {
        path = common.selectPath();
        logger.trace("new path: " + path);
    }
}
