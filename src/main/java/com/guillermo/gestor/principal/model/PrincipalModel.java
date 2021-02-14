package com.guillermo.gestor.principal.model;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.util.Notifications;


public class PrincipalModel {


    private Notifications notification;

    public PrincipalModel() {
        this.notification = new Notifications();
    }

    public FileToDownload buildDownload() {

        return null;
    }

    public void setNotification(Notifications notification) {
        this.notification = notification;
    }
}
