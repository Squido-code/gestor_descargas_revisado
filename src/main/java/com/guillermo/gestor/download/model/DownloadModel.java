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
    URL url;
    File file;
    Notifications notifications;
    Boolean isDelayed;
    int delay;

    public DownloadModel(FileToDownload fileToDownload) throws MalformedURLException {
        url = new URL(fileToDownload.getUrl());
        file = new File(fileToDownload.getPath() + fileToDownload.getName());
        notifications = new Notifications();

    }

    @Override
    protected Void call() {
        logger.trace("download: " + file.getName() + " Initialized");
        try {
            updateMessage("Connecting . . .");
            URLConnection urlConnection = null;
            urlConnection = url.openConnection();
            double fileSize = urlConnection.getContentLength();
            System.out.println(fileSize);
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int totalRead = 0;
            double downloadProgress = 0;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadProgress = ((double) totalRead / fileSize);
                updateProgress(downloadProgress, 1);
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                updateMessage(decimalFormat.format(downloadProgress * 100) + " %");

                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalRead += bytesRead;

                if (isCancelled()) {
                    fileOutputStream.close();
                    logger.trace("Download cancelled");
                    return null;
                }
            }
            fileOutputStream.close();
            updateProgress(1, 1);
            logger.trace("download complete");
        } catch (IOException e) {
            notifications.errorAlert("error en la conexion");
            logger.trace(e.getMessage());
            return null;
        }
        return null;
    }
}
