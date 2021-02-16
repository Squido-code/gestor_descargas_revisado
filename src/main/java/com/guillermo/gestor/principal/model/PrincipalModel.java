package com.guillermo.gestor.principal.model;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.util.Notifications;

import java.io.IOException;


public class PrincipalModel {


    private Notifications notification;

    public PrincipalModel() {
        this.notification = new Notifications();
    }

    public FileToDownload buildDownload() {
        FileToDownload fileToDownload = new FileToDownload();
        try {
            fileToDownload.buildFileToDownload();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileToDownload;
    }

    public void setNotification(Notifications notification) {
        this.notification = notification;
    }
}
