package com.guillermo.gestor.download.controller;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.download.model.DownloadModel;
import com.guillermo.gestor.util.Notifications;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Optional;


public class DownloadController {
    public static Logger logger = LogManager.getLogger(DownloadController.class);
    @FXML
    public Button btStart, btStop, btDeleteDownload, btDeleteFile;
    @FXML
    public Label lbFileName, lbProgress;
    @FXML
    public ProgressBar pgProgress;
    private FileToDownload fileToDownload;
    private DownloadModel downloadModel;
    private Notifications notifications;

    public DownloadController(FileToDownload fileToDownload) {
        try {
            this.notifications = new Notifications();
            this.fileToDownload = fileToDownload;
            this.downloadModel = new DownloadModel(fileToDownload);
        } catch (MalformedURLException e) {
            logger.trace(e.getMessage());
            notifications.errorAlert("URL error");
        }
    }

    @FXML
    public void initialize() {

        changeButtonStatus("start");

        lbFileName.setText(fileToDownload.getName());
        lbProgress.setText("0%");
    }

    @FXML
    public void start() {
        changeButtonStatus("downloading");
        pgProgress.progressProperty().unbind();
        pgProgress.progressProperty().bind(downloadModel.progressProperty());
        downloadModel.messageProperty().
                addListener(((observableValue, oldValue, newValue) -> lbProgress.setText(newValue)));
        downloadModel.stateProperty().
                addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                        if (newValue == Worker.State.SUCCEEDED) {
                            lbProgress.setText("Archivo completado");
                            changeButtonStatus("stop");

                        }
                        if (newValue == Worker.State.CANCELLED) {
                            lbProgress.setText("Descarga cancelada");
                            changeButtonStatus("start");
                        }
                    }
                });
        new Thread(downloadModel).start();
    }

    @FXML
    public void stop() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Cancelar descarga: " + fileToDownload.getName());
        confirmation.setContentText("¿Estás seguro que quieres cancelar la descarga?");
        Optional<ButtonType> respuesta = confirmation.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }
        downloadModel.cancel();
        changeButtonStatus("stop");
    }

    @FXML
    public void delete(Event event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Eliminar la descarga: " + fileToDownload.getName());
        confirmation.setContentText("¿Estás seguro que quieres eliminar la descarga?");
        Optional<ButtonType> respuesta = confirmation.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }
        Node n = (Node) event.getSource();
        Node p = n.getParent();
        ((VBox) p.getParent()).getChildren().remove(p);
        logger.trace("Download deleted");
    }

    @FXML
    public void deleteFile() {
        File file = new File(fileToDownload.getPath(), fileToDownload.getName());
        logger.trace("File: " + file.getName() + " deleted");
        file.delete();
        lbProgress.setText("Archivo eliminado");
        changeButtonStatus("start");
    }

    /**
     * Method that disable the download buttons base on the state of the download file.
     *
     * @param mode String start,downloading,stop,finish
     */
    private void changeButtonStatus(String mode) {
        switch (mode) {
            case "start":
                btStart.setDisable(false);
                btStop.setDisable(true);
                btDeleteDownload.setDisable(false);
                btDeleteFile.setDisable(true);
                break;
            case "downloading":
                btStart.setDisable(true);
                btStop.setDisable(false);
                btDeleteDownload.setDisable(true);
                btDeleteFile.setDisable(true);
                break;
            case "stop":
                btStart.setDisable(true);
                btStop.setDisable(true);
                btDeleteDownload.setDisable(false);
                btDeleteFile.setDisable(false);
                break;
        }
    }
}
