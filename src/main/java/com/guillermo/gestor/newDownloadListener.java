package com.guillermo.gestor;

import com.guillermo.gestor.beans.FileToDownload;

public interface newDownloadListener {
    void onResolve(FileToDownload fileToDownload);

    void onReject(String error);
}
