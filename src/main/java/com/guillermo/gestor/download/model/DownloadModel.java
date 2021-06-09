package com.guillermo.gestor.download.model;

import com.guillermo.gestor.beans.FileToDownload;
import com.guillermo.gestor.util.Notifications;
import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class DownloadModel extends Task<Void> {
    public static Logger logger = LogManager.getLogger(DownloadModel.class);
    private final URL url;
    private final File file;
    private final Notifications notifications;
    private final Boolean isDelayed;
    private final int delay;

    public DownloadModel(FileToDownload fileToDownload) throws MalformedURLException {
        url = new URL(fileToDownload.getUrl());
        file = new File(fileToDownload.getPath(), fileToDownload.getName());
        notifications = new Notifications();
        isDelayed = fileToDownload.isDelayed();
        delay = fileToDownload.getDelay();
    }

    @Override
    protected Void call() {
        if (isDelayed) {
            try {
                int milliDelay = delay * 1000;
                Thread.sleep(milliDelay);
            } catch (InterruptedException e) {
                logger.trace(e.getMessage());
            }
        }
        logger.trace("download: " + file.getName() + " Initialized");
        downloadBody();
        return null;
    }

    private void downloadBody() {
        try {
            updateMessage("Connecting . . .");
            URLConnection urlConnection = url.openConnection();
            double fileSize = urlConnection.getContentLength();
            System.out.println(fileSize);
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] dataBuffer = new byte[1024];
            int totalRead = 0;
            double downloadProgress = 0;
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadProgress = ((double) totalRead / fileSize);
                updateProgress(downloadProgress, 1);
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                updateMessage(decimalFormat.format(downloadProgress * 100) + " %");
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalRead += bytesRead;
                if (isCancelled()) {
                    fileOutputStream.close();
                    updateMessage("download stopped");
                    updateProgress(0, 0);
                    logger.trace("Download cancelled");
                    return;
                }
            }
            fileOutputStream.close();
            updateProgress(1, 1);
            logger.trace("download complete");
        } catch (IOException e) {
            notifications.errorAlert("error en la conexion");
            logger.trace(e.getMessage());
        }
    }
}


