package com.guillermo.gestor.util;

import javafx.scene.control.Alert;

public class Notifications {
    public void errorAlert(String string) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(string);
        alert.showAndWait();
    }
}
