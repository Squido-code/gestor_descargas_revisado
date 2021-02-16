package com.guillermo.gestor.beans;

import com.guillermo.gestor.downloadOptions.view.DownloadOptionsView;

import java.io.IOException;


public class FileToDownload {
    boolean ready;
    private String url;
    private String name;
    private int delay;
    private String path;

    public void buildFileToDownload() throws IOException {
        new DownloadOptionsView().downloadOptionsUi(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
