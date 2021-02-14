package com.guillermo.gestor.downloadOptions.controller;

import com.guillermo.gestor.beans.FileToDownload;


public class DownloadOptionsController {
    private final FileToDownload fileToDownload;

    public DownloadOptionsController(FileToDownload fileToDownload) {
        this.fileToDownload = fileToDownload;
    }
}
